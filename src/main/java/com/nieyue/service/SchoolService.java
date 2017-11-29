package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.School;

/**
 * 学堂逻辑层接口
 * @author yy
 *
 */
public interface SchoolService {
	/** 新增学堂 */	
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
	public List<School> browsePagingSchool(int pageNum,int pageSize,String orderName,String orderWay) ;
}
