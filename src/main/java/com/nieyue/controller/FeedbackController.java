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

import com.nieyue.bean.Feedback;
import com.nieyue.service.FeedbackService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 意见反馈控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Resource
	private FeedbackService feedbackService;
	
	/**
	 * 意见反馈分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingFeedback(
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="feedback_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Feedback> list = new ArrayList<Feedback>();
			list= feedbackService.browsePagingFeedback(acountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 意见反馈修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateFeedback(@ModelAttribute Feedback feedback,HttpSession session)  {
		boolean um = feedbackService.updateFeedback(feedback);
		return ResultUtil.getSR(um);
	}
	/**
	 * 意见反馈增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addFeedback(@ModelAttribute Feedback feedback, HttpSession session) {
		if(feedback.getAcountId()==null||feedback.getAcountId().equals("")||feedback.getContent()==null||feedback.getContent().equals("")){
			return ResultUtil.getSR(false);
		}
		boolean am =feedbackService.addFeedback(feedback);
		return ResultUtil.getSR(am);
	}
	/**
	 * 意见反馈删除
	 * @return
	 */
	@RequestMapping(value = "/{feedbackId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delFeedback(@PathVariable("feedbackId") Integer feedbackId,HttpSession session)  {
		boolean dm = feedbackService.delFeedback(feedbackId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 意见反馈浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="acountId",required=false)Integer acountId,
			HttpSession session)  {
		int count = feedbackService.countAll(acountId);
		return count;
	}
	/**
	 * 意见反馈单个加载
	 * @return
	 */
	@RequestMapping(value = "/{feedbackId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadFeedback(@PathVariable("feedbackId") Integer feedbackId,HttpSession session)  {
		List<Feedback> list = new ArrayList<Feedback>();
		Feedback feedback = feedbackService.loadFeedback(feedbackId);
			if(feedback!=null &&!feedback.equals("")){
				list.add(feedback);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
