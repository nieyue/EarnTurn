package com.nieyue.bean;

import java.io.Serializable;

/**
 * 财务数据类
 * 
 * @author yy
 * 
 */
public class FinanceDetailsDataDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 已支出（支付宝、微信支付完成）
	 */
	private Double totalExpend;
	/**
	 * 支付宝支付完成
	 */
	private Double alipayExpend;
	/**
	 * 微信支付完成
	 */
	private Double wechatExpend;
	/**
	 * 审核中
	 */
	private Double waitExpend;
	/**
	 * 审核未通过
	 */
	private Double noExpend;
	public Double getTotalExpend() {
		return totalExpend;
	}
	public void setTotalExpend(Double totalExpend) {
		this.totalExpend = totalExpend;
	}
	public Double getAlipayExpend() {
		return alipayExpend;
	}
	public void setAlipayExpend(Double alipayExpend) {
		this.alipayExpend = alipayExpend;
	}
	public Double getWechatExpend() {
		return wechatExpend;
	}
	public void setWechatExpend(Double wechatExpend) {
		this.wechatExpend = wechatExpend;
	}
	public Double getWaitExpend() {
		return waitExpend;
	}
	public void setWaitExpend(Double waitExpend) {
		this.waitExpend = waitExpend;
	}
	public Double getNoExpend() {
		return noExpend;
	}
	public void setNoExpend(Double noExpend) {
		this.noExpend = noExpend;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public FinanceDetailsDataDTO(Double totalExpend, Double alipayExpend, Double wechatExpend, Double waitExpend,
			Double noExpend) {
		super();
		this.totalExpend = totalExpend;
		this.alipayExpend = alipayExpend;
		this.wechatExpend = wechatExpend;
		this.waitExpend = waitExpend;
		this.noExpend = noExpend;
	}
	public FinanceDetailsDataDTO() {
		super();
	}
	
	
	
}
