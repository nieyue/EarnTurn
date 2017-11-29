package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * app版本
 * @author yy
 *
 */
public class AppVersion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * app版本id
	 */
	private Integer appVersionId;
	
	/**
	 * app平台，默认0安卓，1为IOS
	 */
	private Integer platform;
	/**
	 * app版本名
	 */
	private String name;
	/**
	 * app类型
	 */
	private Integer type;
	/**
	 * app版本内容
	 */
	private String content;
	/**
	 * app版本链接
	 */
	private String link;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * app状态，默认0上线，1为未上线
	 */
	private Integer status;
	
	public Integer getAppVersionId() {
		return appVersionId;
	}
	public void setAppVersionId(Integer appVersionId) {
		this.appVersionId = appVersionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AppVersion() {
		super();
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public AppVersion(Integer appVersionId, Integer platform, String name, Integer type, String content, String link,
			Date updateDate, Integer status) {
		super();
		this.appVersionId = appVersionId;
		this.platform = platform;
		this.name = name;
		this.type = type;
		this.content = content;
		this.link = link;
		this.updateDate = updateDate;
		this.status = status;
	}

	
}
