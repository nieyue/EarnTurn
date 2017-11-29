package com.nieyue.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.bean.Acount;
import com.nieyue.bean.Finance;
import com.nieyue.bean.Role;
import com.nieyue.exception.MySessionException;

/**
 * 用户session控制拦截器
 * @author yy
 *
 */
@Configuration
public class SessionControllerInterceptor implements HandlerInterceptor {

	@Resource
	StringRedisTemplate stringRedisTemplate;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	
		
		//如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
        	System.out.println(handler);
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
       System.out.println(method.getDefaultValue());
        
        //自定义token
//        if(method.getName().equals("loginAdmin")||method.getName().equals("isloginAdmin")||method.getName().equals("tokenAdmin")){
//        	return true;
//        }else if (manager.checkToken("XuDeOAadmin", manager.getToken("XuDeOAadmin", request), request, response)) {
//        	//验证token成功
//            return true;
//        }
        Acount sessionAcount = null;
        Role sessionRole=null;
        Finance sessionFinance=null;
        if(request.getSession()!=null
        		&&request.getSession().getAttribute("acount")!=null
        		&&request.getSession().getAttribute("role")!=null
        		&&request.getSession().getAttribute("finance")!=null
        		){
        sessionAcount = (Acount) request.getSession().getAttribute("acount");
        sessionRole = (Role) request.getSession().getAttribute("role");
         sessionFinance = (Finance) request.getSession().getAttribute("finance");
        }
        
       if(request.getRequestURI().indexOf("getSession")>-1){
        	return true;
        }
        if(
        		request.getServletPath().equals("/")
        		||request.getRequestURI().indexOf("scaleIncrement")>-1
        		||request.getRequestURI().indexOf("blackIp")>-1
        		||request.getRequestURI().indexOf("Domain")>-1
        		||request.getRequestURI().indexOf("jsAd")>-1
        		//二维码、验证码、404、
        		||request.getRequestURI().indexOf("getVerificationCode")>-1
        		||request.getRequestURI().indexOf("getBarcode")>-1
        		||request.getRequestURI().indexOf("getBarcodeUrl")>-1
        		||request.getRequestURI().indexOf("getlocation")>-1
        		||request.getRequestURI().indexOf("404")>-1
        		//登陆、登出、增加师傅徒弟关系
        		||request.getRequestURI().indexOf("acount/count")>-1
        		||request.getRequestURI().indexOf("acount/loginout")>-1
        		||request.getRequestURI().indexOf("acount/islogin")>-1
        		||request.getRequestURI().indexOf("acount/wxlogin")>-1
        		||request.getRequestURI().indexOf("acount/addMasterId")>-1
        		||request.getRequestURI().indexOf("acount/login")>-1
        		||request.getRequestURI().indexOf("acount/listGroupByMasterId")>-1
        		||request.getRequestURI().indexOf("acount/data")>-1
        		//版本
        		||request.getRequestURI().indexOf("appVersion/count")>-1
        		||request.getRequestURI().indexOf("appVersion/list")>-1
        		||method.getName().equals("loadAppVersion")
        		//article
        		||request.getRequestURI().indexOf("article/count")>-1
        		||request.getRequestURI().indexOf("article/click")>-1
        		||request.getRequestURI().indexOf("article/list")>-1
        		//||request.getRequestURI().indexOf("article/data")>-1
        		||method.getName().equals("loadArticle")
        		||request.getRequestURI().indexOf("article/type")>-1
        		||request.getRequestURI().indexOf("article/img/add")>-1
        		//data
        		||request.getRequestURI().indexOf("data/count")>-1
        		||request.getRequestURI().indexOf("data/list")>-1
        		||method.getName().equals("loadData")
        		//feedback
        		||request.getRequestURI().indexOf("feedback/count")>-1
        		||request.getRequestURI().indexOf("feedback/list")>-1
        		||method.getName().equals("loadFeedback")
        		//finance
        		||request.getRequestURI().indexOf("finance/count")>-1
        		||request.getRequestURI().indexOf("finance/listFinanceByAcountId")>-1
        		||request.getRequestURI().indexOf("finance/today")>-1
        		//financeDetails
        		||request.getRequestURI().indexOf("financeDetails/count")>-1
        		||request.getRequestURI().indexOf("financeDetails/list")>-1
        		||request.getRequestURI().indexOf("financeDetails/acountId")>-1
        		//img
        		||request.getRequestURI().indexOf("img/count")>-1
        		||request.getRequestURI().indexOf("img/list")>-1
        		||method.getName().equals("loadImg")
        		//notice
        		||request.getRequestURI().indexOf("notice/count")>-1
        		||request.getRequestURI().indexOf("notice/list")>-1
        		||method.getName().equals("loadNotice")
        		//profit
        		||request.getRequestURI().indexOf("profit/count")>-1
        		||request.getRequestURI().indexOf("profit/list")>-1
        		||request.getRequestURI().indexOf("profit/listProfitByAcountId")>-1
        		||method.getName().equals("loadProfit")
        		//role
        		||request.getRequestURI().indexOf("role/count")>-1
        		//school
        		||request.getRequestURI().indexOf("school/count")>-1
        		||request.getRequestURI().indexOf("school/list")>-1
        		||method.getName().equals("loadSchool")
        		//spread
        		||request.getRequestURI().indexOf("spread/count")>-1
        		||request.getRequestURI().indexOf("spread/list")>-1
        		||method.getName().equals("loadSpread")
       
        		){
        	return true;
        }else if (sessionAcount!=null) {
        	//确定角色存在
        	if(sessionRole!=null ){
        	//超级管理员
        	if(sessionRole.getName().equals("超级管理员")
        			||sessionRole.getName().equals("广告主管理员")
        			||sessionRole.getName().equals("广告主")
        			){
        		return true;
        	}
        	//admin中只许修改自己的值
        	if(sessionRole.getName().equals("用户")){
        		//账户不许删除/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/acount/add")>-1
        				|| request.getRequestURI().equals("/acount/list")
        				|| request.getRequestURI().indexOf("/acount/update")>-1
        				||(method.getName().equals("loadAcount")&& request.getRequestURI().indexOf(sessionAcount.getAcountId().toString())<=-1)
        				){
        			//获取合伙人
        			if(request.getParameter("masterId").equals(sessionAcount.getAcountId().toString())){
        				return true;
        			}
        			//提交修改自身信息
        			if(request.getParameter("acountId").equals(sessionAcount.getAcountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//意见反馈只许增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/feedback/update")>-1){
        			throw new MySessionException();
        		}
        		//财务不许删除/修改/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/finance/update")>-1 
        				|| request.getRequestURI().indexOf("/finance/list")>-1 
        				|| request.getRequestURI().indexOf("/finance/add")>-1 
        				||(method.getName().equals("loadFinance")&& request.getRequestURI().indexOf(sessionFinance.getFinanceId().toString())<=-1)){
        			throw new MySessionException();
        		}
        		//提现、充值不许删除/修改/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/financeDetails/add")>-1
        				|| request.getRequestURI().indexOf("/financeDetails/update")>-1 ){
        			if(request.getParameter("financeId").equals(sessionFinance.getFinanceId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//图片不许删除/修改/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/img/update")>-1 
        				|| request.getRequestURI().indexOf("/img/add")>-1){
        			throw new MySessionException();
        		}
        		//公告不许删除/修改/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/notice/update")>-1 
        				|| request.getRequestURI().indexOf("/notice/add")>-1){
        			throw new MySessionException();
        		}
        		//收益不许删除/修改/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/profit/update")>-1 
        				|| request.getRequestURI().indexOf("/profit/add")>-1){
        			throw new MySessionException();
        		}
        		//角色全不许
        		if( request.getRequestURI().indexOf("/role")>-1 ){
        			throw new MySessionException();
        		}
        		//学堂不许删除/修改/增加
        		if( request.getRequestURI().indexOf("delete")>-1 
        				|| request.getRequestURI().indexOf("/school/update")>-1 
        				|| request.getRequestURI().indexOf("/school/add")>-1){
        			throw new MySessionException();
        		}
        		return true;
        	}
        	}
        	
        }
        //如果验证token失败
       throw new MySessionException();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
