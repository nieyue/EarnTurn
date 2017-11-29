package com.nieyue.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nieyue.bean.Acount;
import com.nieyue.service.AcountService;
import com.nieyue.util.MyDom4jUtil;
import com.nieyue.util.ThirdParty;
import com.nieyue.util.barcode.QRCodeUtil;
import com.nieyue.weixin.UnifiedOrderUtil;
import com.nieyue.weixin.business.Order;
import com.nieyue.weixin.business.WeiXinBusiness;

import net.sf.json.JSONObject;
/**
 * 微信接口
 * @author yy
 *
 */
@Controller("weixinController")
@RequestMapping("/weixin")
public class WeixinController {
	@Resource
	private AcountService acountService;
	
	/**
	 * 微信jssdk 接口
	 *
	 * @return
	 */
	@RequestMapping(value="/js/connection",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String connectionWeiXin(@RequestParam("url")String url,HttpSession session){
		String json = "";
		//json= WeiXinBusiness.WXJSSDK(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_BENZHENCHAHUA_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_BENZHENCHAHUA_SECRET), url);
		//雅耀的公众号
		//String json = PastUtil.getParam(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_SECRET),url,nonce_str,time_stamp);
		//放肆约
		//String json = PastUtil.getParam(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_FANGSIYUE_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_FANGSIYUE_SECRET),url,nonce_str,time_stamp);
		return json;
		
	}
	
	/**
	 * 微信服务器接口
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value="/native",method={RequestMethod.GET,RequestMethod.POST})
	public String connectionWeiXin(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,@RequestParam("echostr")String echostr){
		return WeiXinBusiness.WXServer(signature, timestamp, nonce, echostr);
	}
	/**
	 * 微信服务器接口
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value="/rnative",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String connectionRWeiXin(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,@RequestParam("echostr")String echostr){
		return WeiXinBusiness.WXServer(signature, timestamp, nonce, echostr);
	}
	/**
	 * 微信授权访问
	 * @return
	 */
	@RequestMapping(value="/authorize",method={RequestMethod.GET,RequestMethod.POST})
	public  String connectionWeiXinRedirect(HttpServletRequest request){
		StringBuffer url = request.getRequestURL();  
		String redirect_url = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString(); 
		String state = request.getHeader("Referer");
		//String aurl = WeiXinBusiness.WXAuth(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_APPID), redirect_url+"/weixin/base/login", "snsapi_base", state);
		String aurl = WeiXinBusiness.WXAuth("wxf399f5caa3a1a0b9", redirect_url+"/weixin/test/login", "snsapi_base", state);
		return "redirect:"+aurl;
	}
	/**
	 * 微信登录获取openid
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/test/login",method={RequestMethod.GET,RequestMethod.POST})
	public  void testWeiXinLogin(@RequestParam("code")String code,HttpSession session,HttpServletResponse response) throws Exception{
		
		String openid = WeiXinBusiness.WXOpenid("wxf399f5caa3a1a0b9", "41752fa2bbe6e07830752c6700fa5b78", code);
		System.out.println(openid);
		return ;
	}
	/**
	 * 微信登录获取openid
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/base/login",method={RequestMethod.GET,RequestMethod.POST})
	public  void connectionWeiXinLogin(@RequestParam("code")String code,@RequestParam("state")String state,HttpSession session,HttpServletResponse response) throws Exception{
		//如果存在session则返回
		if(session.getAttribute("openid")!=null&&session.getAttribute("acount")!=null){
			response.sendRedirect(state);
			return ;
		}
		Acount u = new Acount();
		String openid = WeiXinBusiness.WXOpenid(ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_APPID), ThirdParty.GetValueByKey(ThirdParty.WEIXIN_YAYAO_SECRET), code);
		u.setOpenid(openid);
		//无session 登录不存在存储登录
		if(acountService.weixinBaseAcountLogin(openid)==null || acountService.weixinBaseAcountLogin(openid).equals("")){
			acountService.addAcount(u);
    	}else{
    	//无session登录 存在直接登录
    	u=acountService.weixinBaseAcountLogin(openid);
    	}	
    	session.setAttribute("openid", openid);
    	session.setAttribute("user", u);
    	response.sendRedirect(state);
    	return ;
	}
	
	/**
	 * 微信支付 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/paywap/{orderId}",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXiUnifiedOrder(@PathVariable("orderId")String orderId,HttpServletRequest request) throws Exception {
		Order o=new Order();
		o.setOrderId(234);
		//String openid = (String)request.getSession().getAttribute("openid");
		//String openid = "orFtEwbV4pZCgYpU09JrfZavbAjE";//放肆约
		String openid = "oDvosuIH0Lmn9eDN1nTTTQGVww74";//雅耀本真
		String result = WeiXinBusiness.WXUnifiedorder(o,"测试", UnifiedOrderUtil.getIpAddr(request), openid,"NATIVE","http://nieyue.tea18.cn/weixin/notifyUrl");
		Map<String, Object> m = MyDom4jUtil.xmlStrToMap(result);
		String prepay_id = (String) m.get("prepay_id");
		Map<String,String> map=UnifiedOrderUtil.getPaySignMap(prepay_id);
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		String userAgent=request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
		map.put("agent",new String(new char[]{agent}) );
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	/**
	 * 微信支付  统一下单 jsapi
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/test/{orderId}",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXiPayWap(@PathVariable("orderId")String orderId,HttpServletRequest request) throws Exception {
		Order o=new Order();
		o.setOrderId(234);
		//String openid = (String)request.getSession().getAttribute("openid");
		//String openid = "orFtEwbV4pZCgYpU09JrfZavbAjE";//放肆约
		String openid = "oDvosuIH0Lmn9eDN1nTTTQGVww74";//雅耀本真
		String result = WeiXinBusiness.WXUnifiedorder(o,"测试", UnifiedOrderUtil.getIpAddr(request), openid,"JSAPI","http://nieyue.tea18.cn/weixin/notifyUrl");
		System.out.println(result);
		Map<String, Object> m = MyDom4jUtil.xmlStrToMap(result);
		String prepay_id = (String) m.get("prepay_id");
		Map<String,String> map=UnifiedOrderUtil.getPaySignMap(prepay_id);
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		String userAgent=request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
		map.put("agent",new String(new char[]{agent}) );
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	/**
	 * 微信支付 扫码支付模式二
	 * @param order 订单
	 * @param ip 访问ip
	 * @return prepay_id ，code_url（trade_type为NATIVE有返回）
	 * @throws Exception 
	 */
	@RequestMapping(value="/qrcode2/{orderId}",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinQRCodePay(@PathVariable("orderId")String orderId,HttpServletRequest request) throws Exception {
		Order o=new Order();
		o.setOrderId(234);
		//String openid = (String)request.getSession().getAttribute("openid");
		//String openid = "orFtEwbV4pZCgYpU09JrfZavbAjE";//放肆约
		//String openid = "oDvosuIH0Lmn9eDN1nTTTQGVww74";//雅耀本真
		String result = WeiXinBusiness.WXUnifiedorder(o,"测试", UnifiedOrderUtil.getIpAddr(request), null,"NATIVE","http://nieyue.tea18.cn/weixin/notifyUrl");
		System.out.println(result);
		Map<String, Object> m = MyDom4jUtil.xmlStrToMap(result);
		String prepay_id = (String) m.get("prepay_id");
		String code_url = (String) m.get("code_url");
		String merName="黑茶";
		String savepath=request.getServletContext().getRealPath("")+"/resources/payQRCode/"+merName+".jpg";
		System.out.println(savepath);
		QRCodeUtil.encode(code_url, savepath, true);//二维码生成
		//本地访问
		//QRCodeUtil.encode(code_url, "WebRoot/resources/payQRCode/"+merName+".jpg", true);//二维码生成
		
		Map<String,String> map=UnifiedOrderUtil.getPaySignMap(prepay_id);
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		map.put("code_img","/resources/payQRCode/"+merName+".jpg");
		String userAgent=request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
		map.put("agent",new String(new char[]{agent}) );
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	/**
	 * 微信支付 扫码支付模式二(暂时商户没有权限)
	 * @param order 订单
	 * @param ip 访问ip
	 * @return prepay_id ，code_url（trade_type为NATIVE有返回）
	 * @throws Exception 
	 */
	@RequestMapping(value="/h5paywap/{orderId}",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinH5Pay(@PathVariable("orderId")String orderId,HttpServletRequest request) throws Exception {
		Order o=new Order();
		o.setOrderId(234);
		String result = WeiXinBusiness.WXUnifiedorder(o,"测试", UnifiedOrderUtil.getIpAddr(request), null,"MWEB","http://nieyue.tea18.cn/weixin/notifyUrl");
		System.out.println(result);
		Map<String, Object> m = MyDom4jUtil.xmlStrToMap(result);
		String prepay_id = (String) m.get("prepay_id");
		
		Map<String,String> map=UnifiedOrderUtil.getPaySignMap(prepay_id);
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		map.put("pay_sign",UnifiedOrderUtil.getPaySign(map));
		String userAgent=request.getHeader("user-agent");
		char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
		map.put("agent",new String(new char[]{agent}) );
		JSONObject json = JSONObject.fromObject(map);
		return json.toString();
	}
	/**
	 * 微信统一下单notify_url
	 */
	@RequestMapping(value="/notifyUrl",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinNotifyUrl(@RequestBody String rxml, HttpServletRequest request) throws Exception {
		return WeiXinBusiness.WXNotifyUrl(rxml);
	}
	/**
	 * 微信订单查询
	 */
	@RequestMapping(value="/order/query",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinOrderQuery(HttpServletRequest request) throws Exception {
        	String out_trade_no="56390728112700";
			//String out_trade_no=(String) request.getSession().getAttribute("out_trade_no");
        	//String out_trade_no="4009762001201607269799315041";
        	String order = WeiXinBusiness.WXOrderQuery(out_trade_no);
         return order;
	}
	/**
	 * 关闭微信订单
	 */
	@RequestMapping(value="/order/close",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinOrderClose(HttpServletRequest request) throws Exception {
        	//String out_trade_no="23134A0726100835";
        	String out_trade_no="56390728112700";
			//String out_trade_no=(String) request.getSession().getAttribute("out_trade_no");
        	//String out_trade_no="4009762001201607269799315041";
        	String order = WeiXinBusiness.WXOrderClose(out_trade_no);
         return order;
	}
	/**
	 * 微信申请退款
	 */
	@RequestMapping(value="/refund",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinRefund(HttpServletRequest request) throws Exception {
		//String out_trade_no="23134A0726100835";
		String out_trade_no="56390728112700";
		//String out_trade_no=(String) request.getSession().getAttribute("out_trade_no");
		String refund = WeiXinBusiness.WXRefund(out_trade_no, 1, 1);
		return refund;
	}
	/**
	 *查询微信退款订单
	 */
	@RequestMapping(value="/refund/query",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String WeiXinRefundQuery(HttpServletRequest request) throws Exception {
        	//String out_trade_no="23134A0726100835";
        	String out_trade_no="56390728112700";
			//String out_trade_no=(String) request.getSession().getAttribute("out_trade_no");
        	//String out_trade_no="4009762001201607269799315041";
        	String refundquery = WeiXinBusiness.WXRefundQuery(out_trade_no);
         return refundquery;
	}
	/**
	 * 消息的接收与响应
	 */
	@RequestMapping(value="/msg",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String msgWeiXin(@ModelAttribute Acount acount){
		/*try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
				}else if("2".equals(content)){
					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
				}else if("3".equals(content)){
					//message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
				}else if("?".equals(content) || "？".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(content.startsWith("翻译")){
					String word = content.replaceAll("^翻译", "").trim();
					if("".equals(word)){
						//message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
					}else{
						//message = MessageUtil.initText(toUserName, fromUserName, WeixinUtil.translate(word));
					}
				}
			}else if (MessageUtil.MESSAGE_EVNET.equals(msgType)){
				String eventType=map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
			}
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}*/
		return null;
	}
}
