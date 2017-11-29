package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.FinanceDetails;

/**
 * 财务明细数据库接口
 * @author yy
 *
 */
@Mapper
public interface FinanceDetailsDao {
	/** 新增财务明细*/	
	public boolean addFinanceDetails(FinanceDetails financeDetails) ;	
	/** 删除财务明细 */	
	public boolean delFinanceDetails(Integer financeDetailsId) ;
	/** 更新财务明细*/	
	public boolean updateFinanceDetails(FinanceDetails financeDetails);
	/** 装载财务明细 */	
	public FinanceDetails loadFinanceDetails(Integer financeDetailsId);	
	/** 财务明细总共数目 */	
	public int countAll(@Param("financeId")Integer financeId,@Param("type")Integer type);	
	/** 分页财务明细信息 */
	public List<FinanceDetails> browsePagingFinanceDetails(@Param("financeId")Integer financeId,@Param("type")Integer type,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
	/**财务数据 */
	public List<Double> browseFinanceDetailsData(
			@Param("type")Integer type,
			@Param("updateDate")Date updateDate,
			@Param("likeStatus")String likeStatus
			);		
}
