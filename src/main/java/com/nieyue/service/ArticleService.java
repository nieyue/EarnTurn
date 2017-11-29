package com.nieyue.service;

import java.util.Date;
import java.util.List;

import com.nieyue.bean.Article;
import com.nieyue.bean.ArticleDataDTO;
import com.nieyue.bean.ArticleDayDataDTO;

/**
 * 文章逻辑层接口
 * @author yy
 *
 */
public interface ArticleService {
	/** 新增文章 */	
	public boolean addArticle(Article article) ;	
	/** 删除文章 */	
	public boolean delArticle(Integer articleId) ;
	/** 更新文章*/	
	public boolean updateArticle(Article article);
	/** 更新文章点击*/	
	public boolean updateArticleClick(Article article);		
	/** 装载文章 */	
	public Article loadArticle(Integer articleId);
	/** 装载文章Small */	
	public Article loadSmallArticle(Integer articleId);
	/** 文章总共数目 */	
	public int countAll(String status,Integer acountId,String type,Integer isRecommend,Integer fixedRecommend);
	/** 分页文章信息 */
	public List<Article> browsePagingArticle(
			String status,
			Integer acountId,String type,Integer isRecommend,Integer fixedRecommend,
			int pageNum,int pageSize,String orderName,String orderWay) ;
	/** 文章类型 */
	public List<String> browseArticleTypeList(Integer acountId) ;
	/** 文章数据 */
	public List<ArticleDataDTO> browseArticleData(Integer acountId) ;
	/** 文章日数据 */
	public List<ArticleDayDataDTO> browseArticleDayData(Date startDate,Date endDate) ;

}
