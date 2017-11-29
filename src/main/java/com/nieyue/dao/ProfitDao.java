package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Profit;

/**
 * 用户收益用户收益库接口
 * @author yy
 *
 */
@Mapper
public interface ProfitDao {
	/** 新增用户收益*/	
	public boolean addProfit(@Param("Profit") Profit profit) ;	
	/** 删除用户收益 */	
	public boolean delProfit(Integer profitId) ;
	/** 更新用户收益*/	
	public boolean updateProfit(Profit profit);
	/** 装载用户收益 */	
	public Profit loadProfit(Integer profitId);	
	/** 账户分页浏览收益数目排行榜 */
	//public List<Profit> browsePagingProfitByAcoutId(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/** 用户收益总共数目 */	
	public int countAll(@Param("articleId")Integer articleId,@Param("acountId")Integer acountId,@Param("createDate")Date createDate,@Param("type")Integer type);	
	/** 分页用户收益信息 */
	public List<Profit> browsePagingProfit(@Param("articleId")Integer articleId,@Param("acountId")Integer acountId,@Param("createDate")Date createDate,@Param("type")Integer type,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
