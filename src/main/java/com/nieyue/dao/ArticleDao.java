package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Article;
import com.nieyue.bean.ArticleDataDTO;
import com.nieyue.bean.ArticleDayDataDTO;

/**
 * 文章数据库接口
 * @author yy
 *
 */
@Mapper
public interface ArticleDao {
	/** 新增文章*/	
	public boolean addArticle(Article article) ;	
	/** 删除文章 */	
	public boolean delArticle(Integer articleId) ;
	/** 更新文章*/	
	public boolean updateArticle(Article article);
	/** 装载文章 */	
	public Article loadArticle(Integer articleId);	
	/** 装载文章Small */	
	public Article loadSmallArticle(Integer articleId);	
	/** 文章总共数目 */	
	public int countAll(
			@Param("status")String status,
			@Param("acountId")Integer acountId,
			@Param("type")String type,
			@Param("isRecommend")Integer isRecommend,
			@Param("fixedRecommend")Integer fixedRecommend);	
	/** 分页文章信息 */
	public List<Article> browsePagingArticle(
			@Param("status")String status,
			@Param("acountId")Integer acountId,
			@Param("type")String type,
			@Param("isRecommend")Integer isRecommend,
			@Param("fixedRecommend")Integer fixedRecommend,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay);		
	/** 文章类型 */
	public List<String> browseArticleTypeList(@Param("acountId")Integer acountId) ;		
	/** 文章数据 */
	public List<ArticleDataDTO> browseArticleData(@Param("acountId")Integer acountId) ;		
	/** 文章日数据 */
	public List<ArticleDayDataDTO> browseArticleDayData(@Param("startDate")Date startDate,@Param("endDate")Date endDate) ;		

}
