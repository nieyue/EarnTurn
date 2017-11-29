package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.AcountDTO;
import com.nieyue.bean.Finance;

/**
 * 财务数据库接口
 * @author yy
 *
 */
@Mapper
public interface FinanceDao {
	/** 新增财务*/	
	public boolean addFinance(Finance finance) ;	
	/** 删除财务 */	
	public boolean delFinance(Integer financeId) ;
	/** 更新财务*/	
	public boolean updateFinance(Finance finance);
	/** 装载财务 */	
	public Finance loadFinance(Integer financeId);	
	/** 财务总共数目 */	
	public int countAll(@Param("acountId")Integer acountId);
	/** 账户分页浏览收益数目排行榜 */
	public List<AcountDTO> browsePagingFinanceByAcountId(@Param("acountId")Integer acountId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;
	/** 分页财务信息 */
	public List<Finance> browsePagingFinance(@Param("acountId")Integer acountId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
