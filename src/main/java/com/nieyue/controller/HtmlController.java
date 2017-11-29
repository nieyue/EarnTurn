package com.nieyue.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.bean.Acount;
import com.nieyue.comments.IPCountUtil;
import com.nieyue.comments.RequestToMethdoItemUtils;
import com.nieyue.comments.RequestToMethodItem;
import com.nieyue.limit.RequestLimit;
import com.nieyue.util.MacAddressUtil;
import com.nieyue.util.MyDESutil;
import com.nieyue.util.MyPugin;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;
import com.nieyue.util.barcode.QRCodeUtil;
import com.nieyue.verification.VerificationCode;

import net.sf.json.JSONObject;


/**
 * Html控制类
 * @author yy
 *
 */
@RestController
public class HtmlController {
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Resource
	MyPugin myPugin;
	@Resource
	VerificationCode verificationCode;
	@Value("${myPugin.projectName}")
	String projectName;
	/**
	 * 设置全局合伙人收益比例增量
	 * @param session
	 * @param scaleValue
	 * @return
	 */
	@RequestMapping("/scaleIncrement/update")
	public StateResult updatescaleIncrement(
			HttpSession session,
			@RequestParam("scaleValue")String scaleValue){
		Acount acount = (Acount) session.getAttribute("acount");
		if(!acount.getRoleId().equals(1000)&&!acount.getRoleId().equals(1001)){
			return ResultUtil.getFail();
		}
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ScaleIncrement");
		bvo.getAndSet(scaleValue);
		return ResultUtil.getSuccess();
	}
	/**
	 * 获取全局合伙人收益比例增量
	 * @param session
	 * @return
	 */
	@RequestMapping("/scaleIncrement/get")
	public StateResultList getscaleIncrement(
			HttpSession session){
		List<JSONObject> list = new ArrayList<JSONObject>();
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ScaleIncrement");
		JSONObject jo=new JSONObject();
		jo.put("scaleIncrement", bvo.get());
		list.add(jo);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 设置全分享页面域名 article.html (如：光明网)
	 * @param session
	 * @param scaleValue
	 * @return
	 */
	@RequestMapping("/shareDomain/update")
	public StateResult updateShareDomain(
			HttpSession session,
			@RequestParam("shareDomain")String shareDomain
			){
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		bvo.getAndSet(shareDomain);
		return ResultUtil.getSuccess();
	}
	/**
	 * 获取全分享页面域名 article.html
	 * @param session
	 * @return
	 */
	@RequestMapping("/shareDomain/get")
	public StateResultList getShareDomain(
			HttpSession session){
		List<JSONObject> list = new ArrayList<JSONObject>();
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		JSONObject jo=new JSONObject();
		jo.put("shareDomain", bvo.get());
		list.add(jo);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 设置跨域广告域名 ad.html 
	 * @param session
	 * @param scaleValue
	 * @return
	 */
	@RequestMapping("/adDomain/update")
	public StateResult updateAdDomain(
			HttpSession session,
			@RequestParam("adDomain")String adDomain
			){
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"AdDomain");
		bvo.getAndSet(adDomain);
		return ResultUtil.getSuccess();
	}
	/**
	 * 获取跨域广告域名 ad.html 
	 * @param session
	 * @return
	 */
	@RequestMapping("/adDomain/get")
	public StateResultList getAdDomain(
			HttpSession session){
		List<JSONObject> list = new ArrayList<JSONObject>();
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"AdDomain");
		JSONObject jo=new JSONObject();
		jo.put("adDomain", bvo.get());
		list.add(jo);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 获取光明网
	 * @param session
	 * @return
	 */
	@RequestMapping("/gmwDomain/list")
	public StateResultList getGmwDomain(
			HttpSession session){
		List<String> list = new ArrayList<String>();
		BoundZSetOperations<String, String> bzso=stringRedisTemplate.boundZSetOps(projectName+"GmwDomain");
		Set<String> l = bzso.range(0, -1);
		list.addAll(l);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * add光明网
	 * @param session
	 * @return
	 */
	@RequestMapping("/gmwDomain/add")
	public StateResultList addGmwDomain(
			@RequestParam("value") String value,
			@RequestParam(value="score",required=false,defaultValue="1") double score,
			HttpSession session			
			){
		List<String> list = new ArrayList<String>();
		BoundZSetOperations<String, String> bzso=stringRedisTemplate.boundZSetOps(projectName+"GmwDomain");
		bzso.add(value, score);
		Set<String> l = bzso.range(0, -1);
		list.addAll(l);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * add光明网
	 * @param session
	 * @return
	 */
	@RequestMapping("/gmwDomain/del")
	public StateResultList delGmwDomain(
			@RequestParam("value") String value,
			HttpSession session			
			){
		List<String> list = new ArrayList<String>();
		BoundZSetOperations<String, String> bzso=stringRedisTemplate.boundZSetOps(projectName+"GmwDomain");
		bzso.remove(value);
		Set<String> l = bzso.range(0, -1);
		list.addAll(l);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	
	/**
	 * 设置三俗页面域名 article.html 
	 * @param session
	 * @param scaleValue
	 * @return
	 */
	@RequestMapping("/ssDomain/update")
	public StateResult updateSsDomain(
			HttpSession session,
			@RequestParam("ssDomain")String ssDomain
			){
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"SsDomain");
		bvo.getAndSet(ssDomain);
		return ResultUtil.getSuccess();
	}
	/**
	 * 获取三俗页面域名 article.html
	 * @param session
	 * @return
	 */
	@RequestMapping("/ssDomain/get")
	public StateResultList getSsDomain(
			HttpSession session){
		List<JSONObject> list = new ArrayList<JSONObject>();
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"SsDomain");
		JSONObject jo=new JSONObject();
		jo.put("ssDomain", bvo.get());
		list.add(jo);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 修改黑名单 acountId
	 * @param session
	 * @return
	 */
	@RequestMapping("/blackIp/update")
	public StateResultList updateBlackIP(
			@RequestParam("acountId")Integer acountId,
			@RequestParam("ip")String ip,
			HttpSession session){
		List<String> list = new ArrayList<String>();
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"BlackIp"+acountId);
		bvo.getAndSet(ip);
		list.add(bvo.get());
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 修改黑名单 acountId
	 * @param session
	 * @return
	 */
	@RequestMapping("/blackIp/get")
	public StateResultList getBlackIP(
			@RequestParam("acountId")Integer acountId,
			HttpSession session){
		List<String> list = new ArrayList<String>();
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"BlackIp"+acountId);
		list.add(bvo.get());
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 验证码
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@RequestLimit(count=3,time=1000)
	@RequestMapping("/getVerificationCode")
	public void getVerificationCode(
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
			ByteArrayOutputStream vc = verificationCode.execute(session);
			response.getOutputStream().write(vc.toByteArray());
		
		return ;
	}
	
	/**
	 * 二维码
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@RequestLimit(count=3,time=1000)
	@RequestMapping("/getBarcode")
	public void getBarcode(
			@RequestParam("acountId")Integer acountId,
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		String text = "http://"+bvo.get()+"/share.html?acountId="+acountId;
		QRCodeUtil.encode(text, response.getOutputStream());
		return ;
	}
	/**
	 * 二维码Url
	 * @param date
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getBarcodeUrl")
	public String getBarcodeUrl(
			@RequestParam("acountId")Integer acountId,
			HttpSession session,HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoundValueOperations<String, String> bvo=stringRedisTemplate.boundValueOps(projectName+"ShareDomain");
		String text = "http://"+bvo.get()+"/share.html?acountId="+acountId;
		return text;
	}
	
	/**
	 * 获取js代码广告列表
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsAd/list")
	public StateResultList getJsAd(
			HttpSession session){
		List<String> list = new ArrayList<String>();
		BoundZSetOperations<String, String> bzso=stringRedisTemplate.boundZSetOps(projectName+"JsAd");
		Set<String> l = bzso.range(0, -1);
		list.addAll(l);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * add js代码广告
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsAd/add")
	public StateResultList addJsAd(
			@RequestParam("value") String value,
			@RequestParam(value="score",required=false,defaultValue="1") double score,
			HttpSession session			
			){
		List<String> list = new ArrayList<String>();
		BoundZSetOperations<String, String> bzso=stringRedisTemplate.boundZSetOps(projectName+"JsAd");
		bzso.add(value, score);
		Set<String> l = bzso.range(0, -1);
		list.addAll(l);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * del js代码广告
	 * @param session
	 * @return
	 */
	@RequestMapping("/jsAd/del")
	public StateResultList delJsAd(
			@RequestParam("value") String value,
			HttpSession session			
			){
		List<String> list = new ArrayList<String>();
		BoundZSetOperations<String, String> bzso=stringRedisTemplate.boundZSetOps(projectName+"JsAd");
		bzso.remove(value);
		Set<String> l = bzso.range(0, -1);
		list.addAll(l);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * Html单个加载
	 * @return
	 */
	@RequestMapping(value={"/index","/"})
	public ModelAndView index(Model model,HttpSession session){
		System.err.println(session.getAttribute("userSessionId"));
		if(session.getAttribute("userSessionId")==null||!session.getAttribute("userSessionId").equals(session.getId())){
			session.setAttribute("userSessionId", session.getId());
		}
		model.addAttribute("sessionId", session.getId());
		return new ModelAndView("index");
	}
	@RequestMapping(value={"/404"})
	public ModelAndView go404(){
		return new ModelAndView("404");
	}
//	@RequestMapping(value={"/seller/index"})
//	public ModelAndView sellerindex(){
//		return new ModelAndView("seller/index");
//	}
	@RequestMapping("/date")
	public Date date(@RequestParam("date")Date date){
		System.out.println(date.toLocaleString());
		return date;
	}
	@RequestMapping("/date1")
	public String date1(@RequestParam("date")Date date){
		System.out.println(date.toLocaleString());
		return date.toString();
	}
	@RequestMapping("/redis")
	public String redis(String key){
		BoundValueOperations<String, String> s = stringRedisTemplate.boundValueOps("pvdata");
		if(s.get()==null||s.get().equals("")){
			s.set("5552");
		}else{
			s.set(new String().valueOf(Math.random()));
		}
		System.out.println(s.get());
		return s.get();
	}
	@RequestMapping("/setProp")
	public String setProp(@RequestParam("name")String name,@RequestParam("value")String value) throws IOException{
		String s = myPugin.SetValueByKey(name, value);
		System.out.println(s);
		return s;
	}
	@RequestMapping("/getProp")
	public String getProp(@RequestParam("name")String name){
		String s = myPugin.GetValueByKey(name);
		System.out.println(s);
		return s;
	}
	@Value("${myPugin.location}")
	 String  location;
	@RequestMapping("/getlocation")
	public String getlocation(){
		System.out.println(location);
		return this.location;
	}
	@RequestMapping("/getPhone")
	public String getPhone(HttpServletRequest request) throws IOException{
		System.out.println("ip:+"+IPCountUtil.getIpAddr(request));
		System.out.println("ipv6:+"+IPCountUtil.getLocalIPv6Address());
		//System.out.println("mac:+"+MacAddressUtil.getMacAddress(IPCountUtil.getIpAddr(request)));
		System.out.println("mac:+"+MacAddressUtil.getMacAddress("118.249.69.145"));
		//return MacAddressUtil.getMacAddress(IPCountUtil.getIpAddr(request));
		return MacAddressUtil.getMacAddress("118.249.69.145");
	}
	@Resource
	RequestToMethdoItemUtils requestToMethdoItemUtils;
	/**
	 * 获取API接口文档
	 * @return
	 */
	@RequestMapping(value={"/getAPI"})
	public StateResultList getSession(
			HttpSession session,
			HttpServletRequest request,
			HttpServletResponse response,
			@CookieValue("SESSION") String SESSION
			){
		List<RequestToMethodItem> requestToMethdoItemUtilsresult = requestToMethdoItemUtils.getRequestToMethodItemList(request);
	    for (int i = 0; i < requestToMethdoItemUtilsresult.size(); i++) {
			String controllerName = requestToMethdoItemUtilsresult.get(i).getControllerName();
			String methodName = requestToMethdoItemUtilsresult.get(i).getMethodName();
			String requestUrl = requestToMethdoItemUtilsresult.get(i).getRequestUrl();
			
		}
	    
		return ResultUtil.getSlefSRFailList(requestToMethdoItemUtilsresult);
	
	}
	/**
	 * 获取session2
	 * @return
	 */
	@RequestMapping(value={"/getSession2"})
	public String getSession2(
			HttpSession session,
			HttpServletResponse response
			){
		System.out.println(session.getId());
		return session.getId();
		
	}
	/**
	 * 获取session3
	 * @return
	 */
	@RequestMapping(value={"/getSession3"})
	public String getSession3(
			HttpSession session,
			HttpServletResponse response
			){
		BoundSetOperations<String, String> bso=stringRedisTemplate.boundSetOps("TestSet");
		bso.add("www.nieyue1.com");
		System.out.println(bso.getKey());
		bso.add("www.nieyue2.com");
		System.out.println(bso.getType());
		bso.add("www.nieyue3.com");
		bso.members().forEach(System.out::println);
		bso.remove("www.nieyue2.com");
		bso.members().forEach(System.out::println);
		bso.members().forEach(System.out::println);
		return session.getId();
		
	}
	/**
	 * 获取session1
	 * @return
	 */
	@RequestMapping(value={"/getSession1"})
	public String getSession1(
			HttpSession session,
			HttpServletResponse response,
			@RequestParam(value="token",required=false)String token
			){
		if(token==null||token.equals("")){
			Acount a = new Acount();
			a.setAcountId(1000);
			token = MyDESutil.getMD5(a.getAcountId()+new Date().getTime());
			BoundValueOperations<String, String> bvo = stringRedisTemplate.boundValueOps(token);
			
			System.out.println("set"+token);
			bvo.set(JSONObject.fromObject(a).toString(),30*60,TimeUnit.SECONDS);
			return bvo.getKey();
		}else{
			System.out.println("get"+token);
			BoundValueOperations<String, String> bvo = stringRedisTemplate.boundValueOps(token);
			bvo.set(bvo.get(),30*60,TimeUnit.SECONDS);
			return bvo.getKey();
		}
		
	}
	/**
	 * 设置session
	 * @return
	 */
	@RequestMapping(value={"/setSession"})
	public String setSession(
			HttpSession session,
			HttpServletResponse response,
			@RequestParam("SESSION") String SESSION
			){
		Cookie cookie=new Cookie("SESSION", SESSION);
		cookie.setPath("/");
		response.addCookie(cookie);
		System.out.println(SESSION);
		return session.getId();
		
	}
}
