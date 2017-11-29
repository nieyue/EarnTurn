package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Data;
import com.nieyue.dao.DataDao;
import com.nieyue.service.DataService;
@Service
public class DataServiceImpl implements DataService{
	@Resource
	DataDao dataDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addData(Data data) {
		data.setCreateDate(new Date());
		boolean b = dataDao.addData(data);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delData(Integer dataId) {
		boolean b = dataDao.delData(dataId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateData(Data data) {
		boolean b = dataDao.updateData(data);
		return b;
	}

	@Override
	public Data loadData(Integer dataId,Date createDate,Integer articleId,Integer acountId) {
		Data r = dataDao.loadData(dataId,createDate,articleId,acountId);
		return r;
	}

	@Override
	public int countAll(Date createDate,Integer articleId,Integer acountId) {
		int c = dataDao.countAll(createDate,articleId,acountId);
		return c;
	}

	@Override
	public List<Data> browsePagingData(Date createDate,Integer articleId,Integer acountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Data> l = dataDao.browsePagingData(createDate,articleId,acountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	@Override
	public boolean saveOrUpdateData(Data data, int uv, int ip) {
		boolean b = dataDao.saveOrUpdateData(data, uv, ip);
		return b;
	}

	
}
