package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈
 * @author 聂跃
 * @date 2017年5月2日
 */
public class Feedback  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 意见反馈id
	 */
	private Integer feedbackId;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 时间
	 */
	private Date createDate;
	/**
	 * 提交人
	 */
	private Integer acountId;
	public Integer getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Feedback(Integer feedbackId, Integer title, String content, Date createDate,Integer acountId) {
		super();
		this.feedbackId = feedbackId;
		this.content = content;
		this.createDate = createDate;
		this.acountId = acountId;
	}
	public Feedback() {
		super();
	}
	public Integer getAcountId() {
		return acountId;
	}
	public void setAcountId(Integer acountId) {
		this.acountId = acountId;
	}
	
}
