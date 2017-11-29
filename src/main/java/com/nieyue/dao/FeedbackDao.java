package com.nieyue.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Feedback;

/**
 * 意见反馈数据库接口
 * @author yy
 *
 */
@Mapper
public interface FeedbackDao {
	/** 新增意见反馈*/	
	public boolean addFeedback(Feedback feedback) ;	
	/** 删除意见反馈 */	
	public boolean delFeedback(Integer feedbackId) ;
	/** 更新意见反馈*/	
	public boolean updateFeedback(Feedback feedback);
	/** 装载意见反馈 */	
	public Feedback loadFeedback(Integer feedbackId);	
	/** 意见反馈总共数目 */	
	public int countAll(@Param("acountId")Integer acountId);	
	/** 分页意见反馈信息 */
	public List<Feedback> browsePagingFeedback(@Param("acountId")Integer acountId,@Param("pageNum")int pageNum,@Param("pageSize")int pageSize,@Param("orderName")String orderName,@Param("orderWay")String orderWay) ;		
}
