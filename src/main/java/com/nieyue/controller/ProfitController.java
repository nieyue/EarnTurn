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

import com.nieyue.bean.Article;
import com.nieyue.bean.Profit;
import com.nieyue.bean.ProfitArticleDTO;
import com.nieyue.service.ArticleService;
import com.nieyue.service.ProfitService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;


/**
 * 用户收益控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/profit")
public class ProfitController {
	@Resource
	private ProfitService profitService;
	@Resource
	private ArticleService articleService;
	
	/**
	 *浏览AcountId用户收益明细 
	 * @param orderName 商品排序用户收益库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/listProfitByAcountId", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingProfitByAcountId(
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="profit_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
		List<Profit> list= profitService.browsePagingProfit(articleId,acountId,createDate,type,pageNum, pageSize, orderName, orderWay);
		List<ProfitArticleDTO> nlist = new ArrayList<ProfitArticleDTO>();
		if(list.size()>0){
			for (int i = 0,ls=list.size(); i <ls; i++) {
			ProfitArticleDTO padto=new ProfitArticleDTO();
				Profit p = list.get(i);
				padto.setProfit(p);
				Integer a = p.getArticleId();
				Article ar = articleService.loadSmallArticle(a);
				padto.setArticle(ar);
				nlist.add(padto);
			}
			
			return ResultUtil.getSlefSRSuccessList(nlist);
			
		}else{
			return ResultUtil.getSlefSRFailList(nlist);
		}
	}
	/**
	 * 用户收益分页浏览
	 * @param orderName 商品排序用户收益库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingProfit(
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="profit_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,HttpSession session)  {
			List<Profit> list = new ArrayList<Profit>();
			list= profitService.browsePagingProfit(articleId,acountId,createDate,type,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
				
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 用户收益修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateProfit(@ModelAttribute Profit profit,HttpSession session)  {
		boolean um =profitService.updateProfit(profit);
		return ResultUtil.getSR(um);
	}
	/**
	 * 用户收益增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addProfit(@ModelAttribute Profit profit, HttpSession session) {
		boolean am = profitService.addProfit(profit);
		return ResultUtil.getSR(am);
	}
	/**
	 * 用户收益删除
	 * @return
	 */
	@RequestMapping(value = "/{profitId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delProfit(@PathVariable("profitId") Integer profitId,HttpSession session)  {
		boolean dm =profitService.delProfit(profitId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 用户收益浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="articleId",required=false)Integer articleId,
			@RequestParam(value="acountId",required=false)Integer acountId,
			@RequestParam(value="createDate",required=false)Date createDate,
			@RequestParam(value="type",required=false)Integer type,
			HttpSession session)  {
		int count = profitService.countAll(articleId,acountId,createDate,type);
		return count;
	}
	/**
	 * 用户收益单个加载
	 * @return
	 */
	@RequestMapping(value = "/{profitId}", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList loadProfit(
			@PathVariable("profitId") Integer profitId,HttpSession session)  {
		List<Profit> list = new ArrayList<Profit>();
		Profit profit = profitService.loadProfit(profitId);
			if(profit!=null &&!profit.equals("")){
				list.add(profit);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
