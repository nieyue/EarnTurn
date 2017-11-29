package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户收益类
 * 
 * @author yy
 * 
 */
public class Profit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户收益id
	 */
	private Integer profitId;
	
	/**
	 * 收益类型，0自身，1合伙人
	 */
	private Integer type;
	/**
	 * 阅读数
	 */
	private Long number;
	/**
	 * 收益金额
	 */
	private Double money;
	
	/**
	 *创建时间
	 */
	private Date createDate;
	/**
	 * 文章ID
	 */
	private Integer articleId;
	/**
	 * 账户ID
	 */
	private Integer acountId;
	public Profit() {
		super();
	}
	public Profit(Integer profitId, Integer type, Long number, Double money, Date createDate, Integer articleId,
			Integer acountId) {
		super();
		this.profitId = profitId;
		this.type = type;
		this.number = number;
		this.money = money;
		this.createDate = createDate;
		this.articleId = articleId;
		this.acountId = acountId;
	}

	public Integer getProfitId() {
		return profitId;
	}

	public void setProfitId(Integer profitId) {
		this.profitId = profitId;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getAcountId() {
		return acountId;
	}
	public void setAcountId(Integer acountId) {
		this.acountId = acountId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Profit [profitId=" + profitId + ", type=" + type + ", number=" + number + ", money=" + money
				+ ", createDate=" + createDate + ", articleId=" + articleId + ", acountId=" + acountId + "]";
	}
	
}
