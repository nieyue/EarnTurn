package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Profit;
import com.nieyue.dao.ProfitDao;
import com.nieyue.service.ProfitService;
@Service
public class ProfitServiceImpl implements ProfitService{
	@Resource
	ProfitDao profitDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addProfit(Profit profit) {
		boolean b = profitDao.addProfit(profit);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delProfit(Integer profitId) {
		boolean b = profitDao.delProfit(profitId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateProfit(Profit profit) {
		boolean b = profitDao.updateProfit(profit);
		return b;
	}

	@Override
	public Profit loadProfit(Integer profitId) {
		Profit r = profitDao.loadProfit(profitId);
		return r;
	}

	@Override
	public int countAll(Integer articleId,Integer acountId,Date createDate,Integer type) {
		int c = profitDao.countAll(articleId, acountId,createDate,type);
		return c;
	}

	@Override
	public List<Profit> browsePagingProfit(Integer articleId,Integer acountId,Date createDate,Integer type,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Profit> l = profitDao.browsePagingProfit( articleId, acountId,createDate,type,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
/*	@Override
	public List<AcountDTO> browsePagingProfitByAcoutId(int pageNum, int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<AcountDTO> l = profitDao.browsePagingProfitByAcoutId( pageNum-1, pageSize, orderName, orderWay);
		return l;
	}*/

	
}
