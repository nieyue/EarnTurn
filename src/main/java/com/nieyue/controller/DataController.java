package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.nieyue.bean.Data;
import com.nieyue.service.DataService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 数据控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/data")
public class DataController {
	@Resource
	private DataService dataService;
	
	/**
	 * 数据分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingData(
			@RequestParam(value="crateDate",required=false)Date crateDate,
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="data_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Data> list = new ArrayList<Data>();
			list= dataService.browsePagingData(crateDate,articleId,acountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
				
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 数据修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateData(@ModelAttribute Data data,HttpSession session)  {
		boolean um = dataService.updateData(data);
		return ResultUtil.getSR(um);
	}
	/**
	 * 数据增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addData(@ModelAttribute Data data, HttpSession session) {
		boolean am = dataService.addData(data);
		return ResultUtil.getSR(am);
	}
	/**
	 * 数据删除
	 * @return
	 */
	@RequestMapping(value = "/{dataId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delData(@PathVariable("dataId") Integer dataId,HttpSession session)  {
		boolean dm =dataService.delData(dataId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 数据浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="crateDate",required=false)Date crateDate,
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="acountId",required=false)Integer acountId,
			HttpSession session)  {
		int count = dataService.countAll(crateDate,articleId,acountId);
		return count;
	}
	/**
	 * 数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/{dataId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loadData(@PathVariable("dataId") Integer dataId,HttpSession session)  {
		List<Data> list = new ArrayList<Data>();
		Data Data = dataService.loadData(dataId,null,null,null);
			if(Data!=null &&!Data.equals("")){
				list.add(Data);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 数据单个加载
	 * @return
	 */
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loadDataByParams(
			@RequestParam(value="dataId",required=false)Integer dataId,
			@RequestParam(value="crateDate",required=false)Date crateDate,
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="acountId",required=false)Integer acountId,
			HttpSession session)  {
		List<Data> list = new ArrayList<Data>();
		Data Data = dataService.loadData(dataId,crateDate,articleId,acountId);
		if(Data!=null &&!Data.equals("")){
			list.add(Data);
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	
}
