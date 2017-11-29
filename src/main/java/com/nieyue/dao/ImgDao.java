package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Img;

/**
 * 图片数据库接口
 * @author yy
 *
 */
@Mapper
public interface ImgDao {
	/** 新增图片*/	
	public boolean addImg(Img img) ;	
	/** 删除图片 */	
	public boolean delImg(Integer imgId) ;
	/** 更新图片*/	
	public boolean updateImg(Img img);
	/** 装载图片 */	
	public Img loadImg(Integer imgId);	
	/** 图片总共数目 */	
	public int countAll(@Param("articleId")Integer articleId);	
	/** 分页图片信息 */
	public List<Img> browsePagingImg(@Param("articleId")Integer articleId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
