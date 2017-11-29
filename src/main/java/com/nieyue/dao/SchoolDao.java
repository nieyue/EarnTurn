package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.School;

/**
 * 学堂数据库接口
 * @author yy
 *
 */
@Mapper
public interface SchoolDao {
	/** 新增学堂*/	
	public boolean addSchool(School school) ;	
	/** 删除学堂 */	
	public boolean delSchool(Integer schoolId) ;
	/** 更新学堂*/	
	public boolean updateSchool(School school);
	/** 装载学堂 */	
	public School loadSchool(Integer schoolId);	
	/** 学堂总共数目 */	
	public int countAll();	
	/** 分页学堂信息 */
	public List<School> browsePagingSchool(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
