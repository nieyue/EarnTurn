package com.nieyue.bean;

import java.io.Serializable;

/**
 * 财务详情账户混合类
 * 
 * @author yy
 * 
 */
public class FinanceDetailsAcountDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 财务详情
	 */
	private FinanceDetails financeDetails;
	/**
	 * 账户
	 */
	private Acount acount;
	public FinanceDetailsAcountDTO(FinanceDetails financeDetails, Acount acount) {
		super();
		this.financeDetails = financeDetails;
		this.acount = acount;
	}
	public FinanceDetailsAcountDTO() {
		super();
	}
	public FinanceDetails getFinanceDetails() {
		return financeDetails;
	}
	public void setFinanceDetails(FinanceDetails financeDetails) {
		this.financeDetails = financeDetails;
	}
	public Acount getAcount() {
		return acount;
	}
	public void setAcount(Acount acount) {
		this.acount = acount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "FinanceDetailsAcountDTO [financeDetails=" + financeDetails + ", acount=" + acount
				+ ", getFinanceDetails()=" + getFinanceDetails() + ", getAcount()=" + getAcount() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
