package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.School;
import com.nieyue.dao.SchoolDao;
import com.nieyue.service.SchoolService;
@Service
public class SchoolServiceImpl implements SchoolService{
	@Resource
	SchoolDao schoolDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSchool(School school) {
		school.setCreateDate(new Date());
		boolean b = schoolDao.addSchool(school);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSchool(Integer schoolId) {
		boolean b = schoolDao.delSchool(schoolId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSchool(School school) {
		boolean b = schoolDao.updateSchool(school);
		return b;
	}

	@Override
	public School loadSchool(Integer schoolId) {
		School r = schoolDao.loadSchool(schoolId);
		return r;
	}

	@Override
	public int countAll() {
		int c = schoolDao.countAll();
		return c;
	}

	@Override
	public List<School> browsePagingSchool(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<School> l = schoolDao.browsePagingSchool(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
