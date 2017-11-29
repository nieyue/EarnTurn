package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Finance;
import com.nieyue.bean.FinanceDetails;
import com.nieyue.dao.FinanceDao;
import com.nieyue.dao.FinanceDetailsDao;
import com.nieyue.service.FinanceDetailsService;
@Service
public class FinanceDetailsServiceImpl implements FinanceDetailsService{
	@Resource
	FinanceDetailsDao financeDetailsDao;
	@Resource
	FinanceDao financeDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addFinanceDetails(FinanceDetails financeDetails) {
		if(!financeDetails.getType().equals(0)&&!financeDetails.getType().equals(1)){
			return false;
		}
		financeDetails.setUpdateDate(new Date());
		//不许多个
		List<FinanceDetails> l = financeDetailsDao.browsePagingFinanceDetails(financeDetails.getFinanceId(), financeDetails.getType(), 0, Integer.MAX_VALUE, "finance_details_id", "asc");
		for (int i = 0; i < l.size(); i++) {
			if(l.get(i).getStatus().equals("审核中")){
				return false;
			};
		}
		boolean b = financeDetailsDao.addFinanceDetails(financeDetails);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delFinanceDetails(Integer financeDetailsId) {
		boolean b = financeDetailsDao.delFinanceDetails(financeDetailsId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateFinanceDetails(FinanceDetails financeDetails) {
		if(!financeDetails.getType().equals(0)&&!financeDetails.getType().equals(1)){
			return false;
		}
		//financeDetails.setUpdateDate(new Date());
		boolean b = financeDetailsDao.updateFinanceDetails(financeDetails);
		if(financeDetails.getStatus().equals("审核未通过")){
			return b;
		}
		Integer financeId = financeDetails.getFinanceId();
		Finance finance = financeDao.loadFinance(financeId);
		//提现
		if(financeDetails.getType().equals(0)){
			if(finance.getMoney()-financeDetails.getMoney()<0){
				return false;
			}
			finance.setWithdrawals(finance.getWithdrawals()+financeDetails.getMoney());//加提现余额
			finance.setMoney(finance.getMoney()-financeDetails.getMoney());//减余额
		}
		//充值
		if(financeDetails.getType().equals(1)){
			finance.setRecharge(finance.getRecharge()+financeDetails.getMoney());//加充值金额
			finance.setMoney(finance.getMoney()+financeDetails.getMoney());//加余额
		}
		b=financeDao.updateFinance(finance);
		
		return b;
	}

	@Override
	public FinanceDetails loadFinanceDetails(Integer financeDetailsId) {
		FinanceDetails r = financeDetailsDao.loadFinanceDetails(financeDetailsId);
		return r;
	}

	@Override
	public int countAll(Integer financeId,Integer type) {
		int c = financeDetailsDao.countAll(financeId,type);
		return c;
	}

	@Override
	public List<FinanceDetails> browsePagingFinanceDetails(Integer financeDetailsId,Integer type,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<FinanceDetails> l = financeDetailsDao.browsePagingFinanceDetails(financeDetailsId,type,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}
	@Override
	public List<Double> browseFinanceDetailsData(
			Integer type,Date updateDate,String likeStatus
			) {
		List<Double> l = financeDetailsDao.browseFinanceDetailsData( 
				type,updateDate, likeStatus
				);
		return l;
	}

	
}
