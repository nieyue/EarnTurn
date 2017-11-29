package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 * @author 聂跃
 * @date 2017年5月2日
 */
public class Notice  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公告id
	 */
	private Integer noticeId;
	/**
	 * 公告标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 时间
	 */
	private Date createDate;
	public Integer getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Notice(Integer noticeId, String title, String content, Date createDate) {
		super();
		this.noticeId = noticeId;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}
	public Notice() {
		super();
	}
	
	
}
