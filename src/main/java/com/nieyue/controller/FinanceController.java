package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.AcountDTO;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Profit;
import com.nieyue.service.AcountService;
import com.nieyue.service.ArticleService;
import com.nieyue.service.DataService;
import com.nieyue.service.FinanceService;
import com.nieyue.service.ProfitService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;

import net.sf.json.JSONObject;


/**
 * 财务控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/finance")
public class FinanceController {
	@Resource
	private FinanceService financeService;
	@Resource
	private ProfitService profitService;
	@Resource
	private AcountService acountService;
	@Resource
	private DataService dataService;
	@Resource
	private ArticleService articleService;
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * 收益排行榜
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/listFinanceByAcountId", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingFinanceByAcountId(
			@RequestParam(value="acountId",required=false)Integer acountId,//单个人的排名
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="profitMoney") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<AcountDTO> list = new ArrayList<AcountDTO>();
		list= financeService.browsePagingFinanceByAcountId(acountId,pageNum, pageSize, orderName, orderWay);
		if(list.size()>0){
			return ResultUtil.getSlefSRSuccessList(list);
			
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	/**
	 * 财务分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingFinance(
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="finance_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Finance> list = new ArrayList<Finance>();
			list= financeService.browsePagingFinance(acountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 财务修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateFinance(@ModelAttribute Finance finance,HttpSession session)  {
		boolean um = financeService.updateFinance(finance);
		return ResultUtil.getSR(um);
	}
	/**
	 * 财务增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addFinance(@ModelAttribute Finance finance, HttpSession session) {
		boolean am = financeService.addFinance(finance);
		return ResultUtil.getSR(am);
	}
	/**
	 * 财务删除
	 * @return
	 */
	@RequestMapping(value = "/{financeId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delFinance(@PathVariable("financeId") Integer financeId,HttpSession session)  {
		boolean dm =financeService.delFinance(financeId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 财务浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(@RequestParam(value="acountId",required=false)Integer acountId,HttpSession session)  {
		int count = financeService.countAll(acountId);
		return count;
	}
	/**
	 * 财务单个加载
	 * @return
	 */
	@RequestMapping(value = "/{financeId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loadFinance(@PathVariable("financeId") Integer financeId,HttpSession session)  {
		List<Finance> list = new ArrayList<Finance>();
		Finance finance = financeService.loadFinance(financeId);
			if(finance!=null &&!finance.equals("")){
				list.add(finance);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 今日收益
	 * @return
	 */
	@RequestMapping(value = "/today", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList todayFinance(
			@RequestParam("acountId") Integer acountId,
			HttpSession session)  {
		List<JSONObject> l = new ArrayList<JSONObject>();
		List<Finance> list= financeService.browsePagingFinance(acountId,1, 1, "finance_id","desc");
		if(list.size()<=0){
			return ResultUtil.getSlefSRFailList(l);
		}
		Finance finance = list.get(0);
		Double nowMoney=0.0;
		/*List<Profit> profitlist = profitService.browsePagingProfit(null, acountId, new Date(),null, 1, Integer.MAX_VALUE, "profit_id", "desc");
		Profit profit;
		if(profitlist.size()>0){
		for (int i = 0,profitlength=profitlist.size(); i < profitlength; i++) {
			profit = profitlist.get(i);
			nowMoney+=profit.getMoney();
		}
		}*/
		List<Profit> ps = profitService.browsePagingProfit(null, acountId, new Date(), null, 1, Integer.MAX_VALUE, "profit_id", "desc");
		for (int i = 0,psl=ps.size(); i < psl; i++) {
			nowMoney+=ps.get(i).getMoney();
//			Integer psaid = ps.get(i).getArticleId();
//			if(psaid!=null&&!psaid.equals("")){
//				Article psa = articleService.loadSmallArticle(psaid);
//				if(psa.getUserUnitPrice()==null||psa.getUserUnitPrice().equals("")){
//					nowMoney+=0*ps.get(i).getNumber();
//				}else{
//					nowMoney+=psa.getUserUnitPrice()*ps.get(i).getNumber();
//				}
//			}
		}
		List<AcountDTO> profitMoneyOrder = financeService.browsePagingFinanceByAcountId(acountId, 1, 10, "profitMoney","desc");
		Integer profitMoneyOrderNum=0;
		if(profitMoneyOrder.size()>0){
		 profitMoneyOrderNum=profitMoneyOrder.get(0).getProfitMoneyOrderNum();
		}
		if(profitMoneyOrderNum==null||profitMoneyOrderNum.equals("")){
			profitMoneyOrderNum=0;
		}
		List<AcountDTO> apprenticeOrder = acountService.browsePagingAcountByMasterId(acountId, 1, 10, "apprenticeNum","desc");
		Integer apprenticeOrderNum=0;
		Integer apprenticeNum=0;
		if(apprenticeOrder.size()>0){
		apprenticeOrderNum=apprenticeOrder.get(0).getApprenticeOrderNum();
		apprenticeNum = apprenticeOrder.get(0).getApprenticeNum();
		}
		if(apprenticeOrderNum==null||apprenticeOrderNum.equals("")){
			apprenticeOrderNum=0;
		}
		if(apprenticeNum==null||apprenticeNum.equals("")){
			apprenticeOrderNum=0;
		}
		JSONObject json=new JSONObject();
		json.put("financeId", finance.getFinanceId());//财务ID
		json.put("todayProfit", nowMoney);//今日收益
		json.put("money", finance.getMoney());//余额
		json.put("withdrawals", finance.getWithdrawals());//提现金额
		json.put("profitMoneyOrderNum",profitMoneyOrderNum );//收益排行
		json.put("apprenticeOrderNum",apprenticeOrderNum );//合伙人排行
		json.put("apprenticeNum",apprenticeNum );//合伙人人数
		json.put("partnerProfit",finance.getPartnerProfit() );//合伙人总收益
		json.put("baseProfit",finance.getBaseProfit() );//基准手机
		if(finance!=null &&!finance.equals("")){
			l.add(json);
			return ResultUtil.getSlefSRSuccessList(l);
		}else{
			return ResultUtil.getSlefSRFailList(l);
		}
	}
	
}
