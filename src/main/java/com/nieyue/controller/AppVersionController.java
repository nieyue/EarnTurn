package com.nieyue.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.AppVersion;
import com.nieyue.service.AppVersionService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;




/**
 * App版本控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/appVersion")
public class AppVersionController {
	@Resource
	private AppVersionService appVersionService;
	
	/**
	 * App版本分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingAppVersion(
			@RequestParam(value="platform",required=false)Integer platform,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="app_version_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<AppVersion> list = new ArrayList<AppVersion>();
			list= appVersionService.browsePagingAppVersion(platform,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * App版本修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateAppVersion(@ModelAttribute AppVersion appVersion,HttpSession session)  {
		boolean um = appVersionService.updateAppVersion(appVersion);
		return ResultUtil.getSR(um);
	}
	/**
	 * App版本增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addAppVersion(@ModelAttribute AppVersion appVersion, HttpSession session) {
		boolean am = appVersionService.addAppVersion(appVersion);
		return ResultUtil.getSR(am);
	}
	/**
	 * App版本删除
	 * @return
	 */
	@RequestMapping(value = "/{appVersionId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delAppVersion(@PathVariable("appVersionId") Integer appVersionId,HttpSession session)  {
		boolean dm = appVersionService.delAppVersion(appVersionId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * App版本浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="platform",required=false)Integer platform,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		int count = appVersionService.countAll(platform,status);
		return count;
	}
	/**
	 * App版本单个加载
	 * @return
	 */
	@RequestMapping(value = "/{appVersionId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadAppVersion(@PathVariable("appVersionId") Integer appVersionId,HttpSession session)  {
		List<AppVersion> list = new ArrayList<AppVersion>();
		AppVersion appVersion = appVersionService.loadAppVersion(appVersionId);
			if(appVersion!=null &&!appVersion.equals("")){
				list.add(appVersion);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
