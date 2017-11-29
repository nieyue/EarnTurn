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

import com.nieyue.bean.Spread;
import com.nieyue.service.AcountService;
import com.nieyue.service.SpreadService;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResult;
import com.nieyue.util.StateResultList;




/**
 * 推广控制类
 * @author yy
 *
 */
@RestController
@RequestMapping("/spread")
public class SpreadController {
	@Resource
	private SpreadService spreadService;
	@Resource
	private AcountService acountService;
	
	/**
	 * 推广分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList browsePagingSpread(
			@RequestParam(value="platform",required=false)Integer platform,
			@RequestParam(value="status",required=false)String status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="spread_id") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Spread> list = new ArrayList<Spread>();
			
			list= spreadService.browsePagingSpread(platform,status,pageNum, pageSize, orderName, orderWay);
			for (int i = 0; i < list.size(); i++) {
				Spread sp = list.get(i);
				Integer sid = sp.getSpreadId();
				//List<Acount> al = acountService.browsePagingAcount(sid, null, null, null, null, null, null, null,null,null, 1, Integer.MAX_VALUE, "acount_id", "asc");
				//sp.setRegisterNumber(Long.valueOf(al.size()));
				int count = acountService.countAll(sid, null, null, null, null, null, null, null, null, null);
				sp.setRegisterNumber(Long.valueOf(count));
				sp.setNowTotalPrice(sp.getUnitPrice()*sp.getRegisterNumber());
				spreadService.updateSpread(sp);
			}
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	/**
	 * 推广修改
	 * @return
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult updateSpread(@ModelAttribute Spread spread,HttpSession session)  {
		boolean um = spreadService.updateSpread(spread);
		return ResultUtil.getSR(um);
	}
	/**
	 * 推广增加
	 * @return 
	 */
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult addSpread(@ModelAttribute Spread spread, HttpSession session) {
		boolean am = spreadService.addSpread(spread);
		return ResultUtil.getSR(am);
	}
	/**
	 * 推广删除
	 * @return
	 */
	@RequestMapping(value = "/{spreadId}/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResult delSpread(@PathVariable("spreadId") Integer spreadId,HttpSession session)  {
		boolean dm = spreadService.delSpread(spreadId);
		return ResultUtil.getSR(dm);
	}
	/**
	 * 推广浏览数量
	 * @return
	 */
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody int countAll(
			@RequestParam(value="platform",required=false)Integer platform,
			@RequestParam(value="status",required=false)String status,
			HttpSession session)  {
		int count = spreadService.countAll(platform,status);
		return count;
	}
	/**
	 * 推广单个加载
	 * @return
	 */
	@RequestMapping(value = "/{spreadId}", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList loadSpread(@PathVariable("spreadId") Integer spreadId,HttpSession session)  {
		List<Spread> list = new ArrayList<Spread>();
		Spread spread = spreadService.loadSpread(spreadId);
			Integer sid = spread.getSpreadId();
			//List<Acount> al = acountService.browsePagingAcount(sid, null, null, null, null, null, null, null, null,null,1, Integer.MAX_VALUE, "acount_id", "asc");
			//spread.setRegisterNumber(Long.valueOf(al.size()));
			int count = acountService.countAll(sid, null, null, null, null, null, null, null, null, null);
			spread.setRegisterNumber(Long.valueOf(count));
			spread.setNowTotalPrice(spread.getUnitPrice()*spread.getRegisterNumber());
			spreadService.updateSpread(spread);
			if(spread!=null &&!spread.equals("")){
				list.add(spread);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				return ResultUtil.getSlefSRFailList(list);
			}
	}
	
}
