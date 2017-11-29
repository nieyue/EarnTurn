package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Data;

/**
 * 数据逻辑层接口
 * @author yy
 *
 */
public interface DataService {
	/** 新增数据 */	
	public boolean addData(Data data) ;	
	/** 删除数据 */	
	public boolean delData(Integer dataId) ;
	/** 更新数据*/	
	public boolean updateData(Data data);
	/** 新增或更新数据*/	
	public boolean saveOrUpdateData(Data data,int uv,int ip);
	/** 装载数据 */	
	public Data loadData(Integer dataId,Date createDate,Integer articleId,Integer acountId);	
	/** 数据总共数目 */	
	public int countAll(Date createDate,Integer articleId,Integer acountId);
	/** 分页数据信息 */
	public List<Data> browsePagingData(Date createDate,Integer articleId,Integer acountId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
