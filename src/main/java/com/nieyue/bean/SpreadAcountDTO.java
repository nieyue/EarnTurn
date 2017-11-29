package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 推广账户每日数据类
 * 
 * @author yy
 * 
 */
public class SpreadAcountDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 推广账户ID
	 */
	private Integer acountId;
	/**
	 * 推广注册时间
	 */
	private Date createDate;
	/**
	 * 登陆时间
	 */
	private Date loginDate;
	/**
	 * 推广ID
	 */
	private Integer spreadId;

	public SpreadAcountDTO(Integer acountId, Date createDate, Date loginDate, Integer spreadId) {
		super();
		this.acountId = acountId;
		this.createDate = createDate;
		this.loginDate = loginDate;
		this.spreadId = spreadId;
	}

	public SpreadAcountDTO() {
		super();
	}

	public Integer getAcountId() {
		return acountId;
	}

	public void setAcountId(Integer acountId) {
		this.acountId = acountId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getSpreadId() {
		return spreadId;
	}

	public void setSpreadId(Integer spreadId) {
		this.spreadId = spreadId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
