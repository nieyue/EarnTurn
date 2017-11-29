package com.nieyue.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nieyue.bean.Acount;
import com.nieyue.bean.Finance;
import com.nieyue.bean.FinanceDetails;
import com.nieyue.bean.FinanceDetailsAcountDTO;
import com.nieyue.service.AcountService;
import com.nieyue.service.FinanceDetailsService;
import com.nieyue.service.FinanceService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;
import com.nieyue.view.XLSView;


/**
 * 财务明细控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/financeDetails")
public class FinanceDetailsController {
	@Resource
	private FinanceDetailsService financeDetailsService;
	@Resource
	private FinanceService financeService;
	@Resource
	private AcountService acountService;
	@Resource
	private XLSView xlsView;
	
	/**
	 * 财务明细分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingFinanceDetails(
			@RequestParam(value="financeId",required=false)Integer financeId,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="finance_details_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<FinanceDetails> list = new ArrayList<FinanceDetails>();
			list= financeDetailsService.browsePagingFinanceDetails(financeId,type,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
				
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 财务数据
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/data", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browseFinanceDetailsData(
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="updateDate",required=false)Date updateDate,
			@RequestParam(value="likeStatus",required=false)String likeStatus
			)  {
		List<Double> list = new ArrayList<Double>();
		list= financeDetailsService.browseFinanceDetailsData(type,updateDate,likeStatus);
		if(list.size()>0){
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	@RequestMapping(value = {"/downloadXLS"}, method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public XLSView downloadXLS(
			@RequestParam(value="financeId",required=false)Integer financeId,
			@RequestParam(value="type",required=false)Integer type,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="finance_details_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay,
			HttpSession session,Model model) {
		List<FinanceDetailsAcountDTO> financeDetailsAcountDTOList = new ArrayList<FinanceDetailsAcountDTO>();
		List<FinanceDetails> list = financeDetailsService.browsePagingFinanceDetails(financeId, type, pageNum, pageSize, orderName, orderWay);
		for (int i = 0; i < list.size(); i++) {
			FinanceDetails financeDetails = list.get(i);
			Integer fid = financeDetails.getFinanceId();
			Finance	finance = financeService.loadFinance(fid);
			Integer aid = finance.getAcountId();
			Acount acount = acountService.loadAcount(aid);
			FinanceDetailsAcountDTO financeDetailsAcountDTO=new FinanceDetailsAcountDTO();
			financeDetailsAcountDTO.setFinanceDetails(financeDetails);
			financeDetailsAcountDTO.setAcount(acount);
			financeDetailsAcountDTOList.add(financeDetailsAcountDTO);
		}
		model.addAttribute("financeDetailsAcountDTOList", financeDetailsAcountDTOList);
		xlsView.setFileName("提现信息");
		return xlsView;
		
	}
	
	/**
	 * 财务明细修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateFinanceDetails(@ModelAttribute FinanceDetails financeDetails,HttpSession session)  {
		boolean um =financeDetailsService.updateFinanceDetails(financeDetails);
		return ResultUtil.getSR(um);
	}
	/**
	 * 财务明细增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addFinanceDetails(@ModelAttribute FinanceDetails financeDetails, HttpSession session) {
		boolean am = financeDetailsService.addFinanceDetails(financeDetails);
		return ResultUtil.getSR(am);
	}
	/**
	 * 财务明细删除
	 * @return
	 */
	@RequestMapping(value = "/{financeDetailsId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delFinanceDetails(@PathVariable("financeDetailsId") Integer financeDetailsId,HttpSession session)  {
		boolean dm = financeDetailsService.delFinanceDetails(financeDetailsId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 财务明细浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="financeId",required=false)Integer financeId,
			@RequestParam(value="type",required=false)Integer type,
			HttpSession session)  {
		int count = financeDetailsService.countAll(financeId,type);
		return count;
	}
	/**
	 * 财务明细单个加载
	 * @return
	 */
	@RequestMapping(value = "/{financeDetailsId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadFinanceDetails(@PathVariable("financeDetailsId") Integer financeDetailsId,HttpSession session)  {
		List<FinanceDetails> list = new ArrayList<FinanceDetails>();
		FinanceDetails	financeDetails = financeDetailsService.loadFinanceDetails(financeDetailsId);
			if(financeDetails!=null &&!financeDetails.equals("")){
				list.add(financeDetails);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
	/**
	 * 财务明细单个加载acountId
	 * @return
	 */
	@RequestMapping(value = "/acountId", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadAcountIdByFinanceDetailsId(
			@RequestParam(value="financeId")Integer financeId,
			HttpSession session)  {
		List<Integer> list = new ArrayList<Integer>();
		Finance	finance = financeService.loadFinance(financeId);
		if(finance!=null &&!finance.equals("")){
			list.add(finance.getAcountId());
			return ResultUtil.getSlefSRSuccessList(list);
		}else{
			return ResultUtil.getSlefSRFailList(list);
		}
	}
	
}
