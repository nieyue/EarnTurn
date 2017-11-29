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

import com.nieyue.bean.Img;
import com.nieyue.service.ImgService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 图片控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/img")
public class ImgController {
	@Resource
	private ImgService imgService;
	
	/**
	 * 图片分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingImg(
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="img_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Img> list = new ArrayList<Img>();
			list= imgService.browsePagingImg(articleId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
				
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 图片修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateImg(@ModelAttribute Img img,HttpSession session)  {
		boolean um = imgService.updateImg(img);
		return ResultUtil.getSR(um);
	}
	/**
	 * 图片增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addImg(@ModelAttribute Img img, HttpSession session) {
		boolean am = imgService.addImg(img);
		return ResultUtil.getSR(am);
	}
	/**
	 * 图片删除
	 * @return
	 */
	@RequestMapping(value = "/{imgId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delImg(@PathVariable("imgId") Integer imgId,HttpSession session)  {
		boolean dm =imgService.delImg(imgId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 图片浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(@RequestParam(value="articleId",required=false)Integer articleId,HttpSession session)  {
		int count = imgService.countAll(articleId);
		return count;
	}
	/**
	 * 图片单个加载
	 * @return
	 */
	@RequestMapping(value = "/{imgId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loadImg(@PathVariable("imgId") Integer imgId,HttpSession session)  {
		List<Img> list = new ArrayList<Img>();
		Img Img = imgService.loadImg(imgId);
			if(Img!=null &&!Img.equals("")){
				list.add(Img);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
