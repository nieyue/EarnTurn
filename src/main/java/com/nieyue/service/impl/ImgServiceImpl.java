package com.nieyue.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Img;
import com.nieyue.dao.ImgDao;
import com.nieyue.service.ImgService;
@Service
public class ImgServiceImpl implements ImgService{
	@Resource
	ImgDao imgDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addImg(Img img) {
		boolean b = imgDao.addImg(img);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delImg(Integer imgId) {
		boolean b = imgDao.delImg(imgId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateImg(Img img) {
		boolean b = imgDao.updateImg(img);
		return b;
	}

	@Override
	public Img loadImg(Integer imgId) {
		Img r = imgDao.loadImg(imgId);
		return r;
	}

	@Override
	public int countAll(Integer articleId) {
		int c = imgDao.countAll(articleId);
		return c;
	}

	@Override
	public List<Img> browsePagingImg(Integer articleId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Img> l = imgDao.browsePagingImg(articleId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
