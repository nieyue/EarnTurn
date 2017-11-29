package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Profit;

/**
 * 用户收益逻辑层接口
 * @author yy
 *
 */
public interface ProfitService {
	/** 新增用户收益 */	
	public boolean addProfit(Profit profit) ;	
	/** 删除用户收益 */	
	public boolean delProfit(Integer profitId) ;
	/** 更新用户收益*/	
	public boolean updateProfit(Profit profit);
	/** 装载用户收益 */	
	public Profit loadProfit(Integer profitId);	
	/** 账户分页浏览收益数目排行榜 *//*
	public List<AcountDTO> browsePagingProfitByAcoutId(int pageNum,int pageSize,String orderName,String orderWay) ;		
*/	/** 用户收益总共数目 */	
	public int countAll(Integer articleId,Integer acountId,Date createDate,Integer type);
	/** 分页用户收益信息 */
	public List<Profit> browsePagingProfit(Integer articleId,Integer acountId,Date createDate,Integer type,int pageNum,int pageSize,String orderName,String orderWay) ;
}
