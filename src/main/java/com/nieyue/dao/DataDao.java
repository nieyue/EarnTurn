package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Data;

/**
 * 数据数据库接口
 * @author yy
 *
 */
@Mapper
public interface DataDao {
	/** 新增数据*/	
	public boolean addData(Data data) ;	
	/** 删除数据 */	
	public boolean delData(Integer dataId) ;
	/** 更新数据*/	
	public boolean updateData(Data data);
	/** 新增或更新数据*/	
	public boolean saveOrUpdateData(@Param("Data")Data data,@Param("uv")int uv,@Param("ip")int ip);
	/** 装载数据 */	
	public Data loadData(@Param("dataId")Integer dataId,@Param("createDate")Date createDate,@Param("articleId")Integer articleId,@Param("acountId")Integer acountId);	
	/** 数据总共数目 */	
	public int countAll(@Param("createDate")Date createDate,@Param("articleId")Integer articleId,@Param("acountId")Integer acountId);	
	/** 分页数据信息 */
	public List<Data> browsePagingData(@Param("createDate")Date createDate,@Param("articleId")Integer articleId,@Param("acountId")Integer acountId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
