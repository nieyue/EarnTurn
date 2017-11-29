package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 财务明细
 * @author 聂跃
 * @date 2017年5月2日
 */
public class FinanceDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 财务明细id
	 */
	private Integer financeDetailsId;
	/**
	 * 类型 默认是0 提现  
	 * 1是充值
	 */
	private Integer type;
	/**
	 * 每次提现/充值金额
	 */
	private Double money;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 财务id外键
	 */
	private Integer financeId;
	public Integer getFinanceDetailsId() {
		return financeDetailsId;
	}
	public void setFinanceDetailsId(Integer financeDetailsId) {
		this.financeDetailsId = financeDetailsId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getFinanceId() {
		return financeId;
	}
	public void setFinanceId(Integer financeId) {
		this.financeId = financeId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FinanceDetails(Integer financeDetailsId, Integer type, Double money, String status, Date updateDate,
			Integer financeId) {
		super();
		this.financeDetailsId = financeDetailsId;
		this.type = type;
		this.money = money;
		this.status = status;
		this.updateDate = updateDate;
		this.financeId = financeId;
	}
	public FinanceDetails() {
		super();
	}
	
}
