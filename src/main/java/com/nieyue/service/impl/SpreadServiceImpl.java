package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Spread;
import com.nieyue.dao.SpreadDao;
import com.nieyue.service.SpreadService;

@Service
public class SpreadServiceImpl implements SpreadService{
	@Resource
	SpreadDao spreadDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addSpread(Spread spread) {
		spread.setCreateDate(new Date());
		spread.setStatus("正常");
		spread.setDownloadNumber(0l);
		spread.setRegisterNumber(0l);
		spread.setNowTotalPrice(0.0);
		spread.setStatus("正常");
		boolean b = spreadDao.addSpread(spread);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delSpread(Integer spreadId) {
		boolean b = spreadDao.delSpread(spreadId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateSpread(Spread spread) {
		boolean b = spreadDao.updateSpread(spread);
		return b;
	}

	@Override
	public Spread loadSpread(Integer spreadId) {
		Spread r =spreadDao.loadSpread(spreadId);
		return r;
	}

	@Override
	public int countAll(Integer platform,String status) {
		int c = spreadDao.countAll( platform, status);
		return c;
	}

	@Override
	public List<Spread> browsePagingSpread(Integer platform,String status,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Spread> l = spreadDao.browsePagingSpread( platform, status,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
