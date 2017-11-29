package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.FinanceDetails;

/**
 * 财务明细逻辑层接口
 * @author yy
 *
 */
public interface FinanceDetailsService {
	/** 新增财务明细 */	
	public boolean addFinanceDetails(FinanceDetails financeDetails) ;	
	/** 删除财务明细 */	
	public boolean delFinanceDetails(Integer financeDetailsId) ;
	/** 更新财务明细*/	
	public boolean updateFinanceDetails(FinanceDetails financeDetails);
	/** 装载财务明细 */	
	public FinanceDetails loadFinanceDetails(Integer financeDetailsId);	
	/** 财务明细总共数目 */	
	public int countAll(Integer financeId,Integer type);
	/** 分页财务明细信息 */
	public List<FinanceDetails> browsePagingFinanceDetails(Integer financeDetailsId,Integer type,int pageNum,int pageSize,String orderName,String orderWay) ;
	/**财务数据 */
	public List<Double> browseFinanceDetailsData(
			Integer type,Date updateDate,String likeStatus
			);
}
