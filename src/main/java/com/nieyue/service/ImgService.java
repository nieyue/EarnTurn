package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Img;

/**
 * 图片逻辑层接口
 * @author yy
 *
 */
public interface ImgService {
	/** 新增图片 */	
	public boolean addImg(Img img) ;	
	/** 删除图片 */	
	public boolean delImg(Integer imgId) ;
	/** 更新图片*/	
	public boolean updateImg(Img img);
	/** 装载图片 */	
	public Img loadImg(Integer imgId);	
	/** 图片总共数目 */	
	public int countAll(Integer articleId);
	/** 分页图片信息 */
	public List<Img> browsePagingImg(Integer articleId,int pageNum,int pageSize,String orderName,String orderWay) ;
}
