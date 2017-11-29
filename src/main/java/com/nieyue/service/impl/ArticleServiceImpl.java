package com.nieyue.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nieyue.bean.Article;
import com.nieyue.bean.ArticleDataDTO;
import com.nieyue.bean.ArticleDayDataDTO;
import com.nieyue.bean.Img;
import com.nieyue.dao.ArticleDao;
import com.nieyue.dao.ImgDao;
import com.nieyue.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	ArticleDao articleDao;
	@Resource
	ImgDao imgDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addArticle(Article article) {
		boolean b=false;
		article.setCreateDate(new Date());
		article.setUpdateDate(new Date());
		article.setTurnNumber(0);
		article.setReadingNumber(0);
		article.setScale(0.0);
		article.setPvs(0l);
		article.setUvs(0l);
		article.setIps(0l);
		article.setNowTotalPrice(0.0);
		article.setUserNowTotalPrice(0.0);
		b = articleDao.addArticle(article);
		List<Img> imgList = article.getImgList();
		for (int i = 0; i < imgList.size(); i++) {
			Img img = imgList.get(i);
			img.setArticleId(article.getArticleId());
			b=imgDao.addImg(img);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delArticle(Integer articleId) {
		boolean b = articleDao.delArticle(articleId);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateArticle(Article article) {
		boolean b=false;
		article.setUpdateDate(new Date());
		b= articleDao.updateArticle(article);
		List<Img> delImgList = imgDao.browsePagingImg(article.getArticleId(), 0, Integer.MAX_VALUE, "img_id","asc");
		for (Img img : delImgList) {
		b = imgDao.delImg(img.getImgId());
		}
		
		List<Img> imgList = article.getImgList();
		for (int i = 0; i < imgList.size(); i++) {
			Img img = imgList.get(i);
			img.setArticleId(article.getArticleId());
			b=imgDao.addImg(img);
		}
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean updateArticleClick(Article article) {
		boolean b=false;
		b= articleDao.updateArticle(article);
		return b;
	}

	@Override
	public Article loadArticle(Integer articleId) {
		Article r = articleDao.loadArticle(articleId);
		List<Img> imgl = imgDao.browsePagingImg(r.getArticleId(), 0, 3, "img_id","asc");
		r.setImgList(imgl);
		return r;
	}

	@Override
	public int countAll(String status,Integer acountId,String type,Integer isRecommend,Integer fixedRecommend) {
		int c = articleDao.countAll(status,acountId,type,isRecommend,fixedRecommend);
		return c;
	}

	@Override
	public List<Article> browsePagingArticle(String status,Integer acountId, String type, Integer isRecommend, Integer fixedRecommend,
			int pageNum, int pageSize, String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Article> l = articleDao.browsePagingArticle(status,acountId,type,isRecommend,fixedRecommend,pageNum-1, pageSize, orderName, orderWay);
		for (Article article : l) {
			List<Img> imgl = imgDao.browsePagingImg(article.getArticleId(), 0, 3, "img_id","asc");
			article.setImgList(imgl);
			//list.add(article);
		}
		return l;
	}
	@Override
	public List<String> browseArticleTypeList(Integer acountId) {
		List<String> l = articleDao.browseArticleTypeList(acountId);
		return l;
	}
	@Override
	public Article loadSmallArticle(Integer articleId) {
		Article r = articleDao.loadSmallArticle(articleId);
		if(r==null||r.equals("")){
			r=new Article();
		}else{
			List<Img> imgl = imgDao.browsePagingImg(r.getArticleId(), 0, 3, "img_id","asc");
			r.setImgList(imgl);			
		}
		return r;
	}

	@Override
	public List<ArticleDataDTO> browseArticleData(Integer acountId) {
		List<ArticleDataDTO> l = articleDao.browseArticleData( acountId) ;
		return l;
	}
	@Override
	public List<ArticleDayDataDTO> browseArticleDayData(Date startDate, Date endDate) {
		List<ArticleDayDataDTO> l = articleDao.browseArticleDayData(startDate, endDate);
		return l;
	}

}
