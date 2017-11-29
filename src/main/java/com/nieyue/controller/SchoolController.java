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

import com.nieyue.bean.School;
import com.nieyue.service.SchoolService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 学堂控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/school")
public class SchoolController {
	@Resource
	private SchoolService schoolService;
	
	/**
	 * 学堂分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingSchool(
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="school_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<School> list = new ArrayList<School>();
			list= schoolService.browsePagingSchool(pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 学堂修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSchool(@ModelAttribute School school,HttpSession session)  {
		boolean um = schoolService.updateSchool(school);
		return ResultUtil.getSR(um);
	}
	/**
	 * 学堂增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSchool(@ModelAttribute School school, HttpSession session) {
		boolean am = schoolService.addSchool(school);
		return ResultUtil.getSR(am);
	}
	/**
	 * 学堂删除
	 * @return
	 */
	@RequestMapping(value = "/{schoolId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSchool(@PathVariable("schoolId") Integer schoolId,HttpSession session)  {
		boolean dm = schoolService.delSchool(schoolId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 学堂浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(HttpSession session)  {
		int count = schoolService.countAll();
		return count;
	}
	/**
	 * 学堂单个加载
	 * @return
	 */
	@RequestMapping(value = "/{schoolId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadSchool(@PathVariable("schoolId") Integer schoolId,HttpSession session)  {
		List<School> list = new ArrayList<School>();
		School school = schoolService.loadSchool(schoolId);
			if(school!=null &&!school.equals("")){
				list.add(school);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
