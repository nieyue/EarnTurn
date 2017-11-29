package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Spread;


/**
 * 推广逻辑层接口
 * @author yy
 *
 */
public interface SpreadService {
	/** 新增推广 */	
	public boolean addSpread(Spread spread) ;	
	/** 删除推广 */	
	public boolean delSpread(Integer spreadId) ;
	/** 更新推广*/	
	public boolean updateSpread(Spread spread);
	/** 装载推广 */	
	public Spread loadSpread(Integer spreadId);	
	/** 推广总共数目 */	
	public int countAll(Integer platform,String status);
	/** 分页推广信息 */
	public List<Spread> browsePagingSpread(Integer platform,String status,int pageNum,int pageSize,String orderName,String orderWay) ;
}
