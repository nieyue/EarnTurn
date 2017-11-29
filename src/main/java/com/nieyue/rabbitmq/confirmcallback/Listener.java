package com.nieyue.rabbitmq.confirmcallback;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.nieyue.bean.Acount;
import com.nieyue.bean.Article;
import com.nieyue.bean.Data;
import com.nieyue.bean.DataRabbitmqDTO;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Profit;
import com.nieyue.service.AcountService;
import com.nieyue.service.ArticleService;
import com.nieyue.service.DataService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.ProfitService;
import com.nieyue.util.DateUtil;
import com.rabbitmq.client.Channel;

/**
 * 消息监听者
 * @author 聂跃
 * @date 2017年5月31日
 */
@Configuration  
public class Listener {
	@Resource
	private ArticleService articleService;
	@Resource
	private FinanceService financeService;
	@Resource
	private DataService dataService;
	@Resource
	private ProfitService profitService;
	@Resource
	private AcountService acountService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Value("${myPugin.projectName}")
	String projectName;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);  
	    @RabbitListener(queues="${myPugin.rabbitmq.DIRECT_QUEUE}") 
	    public void process(Channel channel, DataRabbitmqDTO dataRabbitmqDTO,Message message) throws IOException   {
	           try {
	        	  // LOGGER.info("消费端接收到消息: " + dataRabbitmqDTO.toString());
	        	  // LOGGER.info("message.getBody: " + new String (message.getBody()));
	        	  /**
	        	   * 判断是否存在
	        	   */
	        	   Acount inacount = acountService.loadAcount(dataRabbitmqDTO.getAcountId());
	       			Article inarticle = articleService.loadSmallArticle(dataRabbitmqDTO.getArticleId());
	       		//如果文章或用户不存在则不予统计
	       		if(inacount==null||inacount.equals("")||inarticle==null||inarticle.equals("")){
	       		 channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	       		 return;
	       		}
	       		//如果账号被锁定，不统计
	       		if(inacount.getStatus().equals(1)){
	       			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	       			return;
	       		}
	       		//当前用户的ip与热度ip相同，则不统计
	       		BoundValueOperations<String, String> bvobia=stringRedisTemplate.boundValueOps(projectName+"BlackIp"+dataRabbitmqDTO.getAcountId());
	       		if(bvobia.get()!=null&&!bvobia.get().equals("")){
	       			if(dataRabbitmqDTO.getIp().equals(bvobia.get())){
	       				channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	       				return;
	       			}
	       		}
	        	   /**
	        	    * 统计data
	        	    */
	        	   //统计当日当前文章文章每日ip(总统计，做区别ip,保证不同acountId同一IP)
	        	   BoundSetOperations<String, String> bsodatips = stringRedisTemplate.boundSetOps(projectName+"ArticleId"+dataRabbitmqDTO.getArticleId()+"Data"+DateUtil.getImgDir()+"Ips");
	        	   boolean isAddIp=false;//默认没增加
	        	   int oldIPSize = bsodatips.members().size();
	        	   bsodatips.add(dataRabbitmqDTO.getIp());//总ip
	        	   int nowIPSize = bsodatips.members().size();
	        	   if(nowIPSize>oldIPSize){
	        		   isAddIp=true;//增加了
	        	   }
	        	   //统计当日当前人的当前文章每日ip
	        	   BoundSetOperations<String, String> bsodataips = stringRedisTemplate.boundSetOps(projectName+"AcountId"+dataRabbitmqDTO.getAcountId()+"ArtiticlId"+dataRabbitmqDTO.getArticleId()+"Data"+DateUtil.getImgDir()+"Ips");
			        	   if(isAddIp){
			        	   bsodataips.add(dataRabbitmqDTO.getIp());//ip存入redis数据库
			        	   }
	        	   			Data realdata=new Data();
	        	   			realdata.setCreateDate(new Date());
	        	   			realdata.setArticleId(dataRabbitmqDTO.getArticleId());
	        	   			realdata.setAcountId(dataRabbitmqDTO.getAcountId());
	        	   			//bsodataips.expire(DateUtil.currentToEndTime(), TimeUnit.SECONDS);//按天计算有用
	        	   			dataService.saveOrUpdateData(realdata,dataRabbitmqDTO.getUv(), bsodataips.members().size());
	        	  /**
	        	   * 更新文章
	        	   */
	        	   //当前文章
	        	   Article article = articleService.loadSmallArticle(dataRabbitmqDTO.getArticleId());
	        	   //当前文章的所有数据
	        	   List<Data> articledatalist = dataService.browsePagingData(null, dataRabbitmqDTO.getArticleId(), null, 1, Integer.MAX_VALUE, "data_id", "asc");
	        	   Long pvs=0l;
	        	   Long uvs=0l;
	        	   Long ips=0l;
	        	   for (Data data : articledatalist) {
	        		   pvs+=data.getPvs();
	        		   uvs+=data.getUvs();
	        		   ips+=data.getIps();
	        	   }
	        	   article.setPvs(pvs);
	        	   article.setUvs(uvs);
	        	   article.setIps(ips);
	        	   article.setContent(null);
	        	//如果ip没长，则完成
	        	   if(!isAddIp){
	        		articleService.updateArticleClick(article);
		       		channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		        	return;  
	        	   }
	       		//如果消耗完则返回
	       		if(article.getNowTotalPrice()>=article.getTotalPrice()){
	       			article.setStatus("完成");
	       			articleService.updateArticleClick(article);
	       			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        		return;
	       		}
	        	   //任务完成，只收集数据
	        	   if(!article.getStatus().equals("正常")||article.getStatus().equals("完成")){
	        		   articleService.updateArticleClick(article);//更新文章数据
		        	   channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        		   return;
	        	   }//完成不长阅读和金额
	        	   article.setReadingNumber(ips.intValue());
	        	   article.setNowTotalPrice(article.getReadingNumber()*article.getUnitPrice());
	        	   article.setUserNowTotalPrice(article.getReadingNumber()*article.getUserUnitPrice());
	        	   articleService.updateArticleClick(article);//更新文章数据
	        	   /**
	        	    * 自身财务、收益
	        	    */
	        	   //自身财务
	        	   List<Finance> financelist = financeService.browsePagingFinance(dataRabbitmqDTO.getAcountId(), 1, 1, "finance_id", "asc");
	        	   Finance selfFinance = financelist.get(0);
	        	   //自身账户的当日文章的所有数据
	        	   List<Data> datalist = dataService.browsePagingData(new Date(), dataRabbitmqDTO.getArticleId(), dataRabbitmqDTO.getAcountId(), 1, Integer.MAX_VALUE, "data_id", "asc");
	        	   Data selfData = datalist.get(0);
	        	   //System.out.println(selfData.toString());
	        	   //自身收益
	        	   Profit profit=new Profit();
	        	   profit.setAcountId(dataRabbitmqDTO.getAcountId());
	        	   profit.setArticleId(dataRabbitmqDTO.getArticleId());
	        	   profit.setCreateDate(new Date());
	        	   profit.setType(0);
	        	   Double selfMoney=0.0;
	        	   Long selfNumber=0l;
	        	  for (Data data : datalist) {
	        			selfNumber+=data.getIps();//ip数 
	        	   }
	        	  profit.setNumber(selfNumber);	        	  
	        	  selfMoney=article.getUserUnitPrice()*selfNumber;
	        	  // selfMoney=profit.getMoney()+selfNumber;//放置修改用户收益，userUnitPrice
	        	   
	        	   profit.setMoney(selfMoney);
	        	   profitService.addProfit(profit);//存储或更新自身收益
	        	   //财务,余额增加
	        	   //获取自身账户所有收益
	        	   List<Profit> selfprofitlist = profitService.browsePagingProfit(null,selfData.getAcountId() , null,null, 1,Integer.MAX_VALUE, "profit_id", "asc");
	        	   Double selfAllMoney=0.0;
	        	   for (Profit profit2 : selfprofitlist) {
	        		   selfAllMoney+=profit2.getMoney();
	        	   }
	        	   //余额=总收益+基准收益+充值金额-消费金额-提现金额
	        	   selfFinance.setMoney(selfAllMoney+selfFinance.getBaseProfit()+selfFinance.getRecharge()-selfFinance.getConsume()-selfFinance.getWithdrawals());
	        	   financeService.updateFinance(selfFinance);
	        	   
	        	   /**
	        	    * 上级账户收益、财务
	        	    */
	        	  Acount acount = acountService.loadAcount(profit.getAcountId());//获取自身账户
	        	  Integer masterID = acount.getMasterId();//获取父账户ID
	        	  if(masterID!=null&&!masterID.equals("")){ 
	        		  Acount masterAcount = acountService.loadAcount(masterID);
	        		  if(masterAcount!=null&&!masterAcount.equals("")&&masterAcount.getScale()>0.0){
	        			  //父账户财务
	        			  List<Finance> masterfinancelist = financeService.browsePagingFinance(masterAcount.getAcountId(), 1, 1, "finance_id", "asc");
	        			 if(masterfinancelist.size()>0){//有父账户
	        			Finance masterFinance = masterfinancelist.get(0);
	        				//父账户收益
	      	        	   Profit masterProfit=new Profit();
	      	        	 masterProfit.setAcountId(masterID);
	      	        	masterProfit.setArticleId(selfData.getArticleId());
	      	        	masterProfit.setCreateDate(selfData.getCreateDate());
	      	        	masterProfit.setType(1);
	      	        	masterProfit.setNumber(selfNumber);	
	      	        	 BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ScaleIncrement");//合伙人增量
	      	        	   double masterMoney = article.getUserUnitPrice()*selfNumber*(masterAcount.getScale()+Double.valueOf(bvo.get()));//合伙人比例收益=合伙人单价*数量*（合伙人收益比例+全局收益比例）
	      	        	 masterProfit.setMoney(masterMoney);
	      	        	  boolean bvb = profitService.addProfit(masterProfit);//存储或更新父账户收益
	      	        	  //System.err.println(bvb);
	      	        	   //财务,余额增加, 父账户总收益增加
	    	        	   //获取上级账户所有收益
	    	        	   List<Profit> masterprofitlist = profitService.browsePagingProfit(null,masterID , null,null, 1,Integer.MAX_VALUE, "profit_id", "asc");
	    	        	   Double masterAllMoney=0.0;//上级账户总收益
	    	        	   Double masterPartnerProfit=0.0;//上级账户徒弟总收益
	    	        	   for (Profit profit3 : masterprofitlist) {
	    	        		   masterAllMoney+=profit3.getMoney();
	    	        		   if(profit3.getType().equals(1)){
	    	        			   masterPartnerProfit+=profit3.getMoney();
	    	        		   }
	    	        	   }
	    	        	   //余额=总收益+基准收益+充值金额-消费金额-提现金额
	    	        	   masterFinance.setMoney(masterAllMoney+selfFinance.getBaseProfit()+masterFinance.getRecharge()-masterFinance.getConsume()-masterFinance.getWithdrawals());
	    	        	   masterFinance.setPartnerProfit(masterPartnerProfit);
	    	        	   //初次，父账户奖励
	    	        	   /*if(selfprofitlist.size()<=1){
	    	        		   BoundValueOperations<String, String> etif=stringRedisTemplate.boundValueOps("EarnTurnIsFirst"+profit.getAcountId());//有父账户，第一次收益，父账户增加基准收益		 
	    	        		 masterFinance.setBaseProfit(masterFinance.getBaseProfit()+Double.valueOf(etif.get()));
	    	        	   }*/
	        			   financeService.updateFinance(masterFinance);
	        			 }
	        		  }
	        	  }
	        	   
	        	   //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        	  channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				 try {
					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
				} catch (IOException e1) {
					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
					
					e1.printStackTrace();
				}
				//e.printStackTrace();
			} //确认消息成功消费 
	    }     
}
