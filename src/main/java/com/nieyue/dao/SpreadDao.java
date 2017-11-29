package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Spread;


/**
 * 推广数据库接口
 * @author yy
 *
 */
@Mapper
public interface SpreadDao {
	/** 新增推广*/	
	public boolean addSpread(Spread spread) ;	
	/** 删除推广 */	
	public boolean delSpread(Integer spreadId) ;
	/** 更新推广*/	
	public boolean updateSpread(Spread spread);
	/** 装载推广 */	
	public Spread loadSpread(Integer spreadId);	
	/** 推广总共数目 */	
	public int countAll(@Param("platform")Integer platform,@Param("status")String status);	
	/** 分页推广信息 */
	public List<Spread> browsePagingSpread(@Param("platform")Integer platform,@Param("status")String status,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
