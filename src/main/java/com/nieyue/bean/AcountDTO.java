package com.nieyue.bean;

import java.io.Serializable;
/**
 * 账户DTO
 * @author 聂跃
 * @date 2017年4月12日
 */
public class AcountDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 账户id
	 */
	private Integer acountId;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 图像
	 */
	private String icon;
	/**
	 * 徒弟数目
	 */
	private Integer apprenticeNum;
	/**
	 * 总收益=合伙人收益+基准收益=余额（money）+提现金额（withdrawals）+消费金额（consume）-充值金额（recharge）
	 */
	private Double profitMoney;
	/**
	 * 拥有徒弟的排行
	 */
	private Integer apprenticeOrderNum;
	/**
	 * 拥有收益的排行
	 */
	private Integer profitMoneyOrderNum;
	public Integer getAcountId() {
		return acountId;
	}
	public void setAcountId(Integer acountId) {
		this.acountId = acountId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getApprenticeNum() {
		return apprenticeNum;
	}
	public void setApprenticeNum(Integer apprenticeNum) {
		this.apprenticeNum = apprenticeNum;
	}
	public Double getProfitMoney() {
		return profitMoney;
	}
	public void setProfitMoney(Double profitMoney) {
		this.profitMoney = profitMoney;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getApprenticeOrderNum() {
		return apprenticeOrderNum;
	}
	public void setApprenticeOrderNum(Integer apprenticeOrderNum) {
		this.apprenticeOrderNum = apprenticeOrderNum;
	}
	public Integer getProfitMoneyOrderNum() {
		return profitMoneyOrderNum;
	}
	public void setProfitMoneyOrderNum(Integer profitMoneyOrderNum) {
		this.profitMoneyOrderNum = profitMoneyOrderNum;
	}
	public AcountDTO() {
		super();
	}
	public AcountDTO(Integer acountId, String nickname, String icon, Integer apprenticeNum, Double profitMoney,
			Integer apprenticeOrderNum, Integer profitMoneyOrderNum) {
		super();
		this.acountId = acountId;
		this.nickname = nickname;
		this.icon = icon;
		this.apprenticeNum = apprenticeNum;
		this.profitMoney = profitMoney;
		this.apprenticeOrderNum = apprenticeOrderNum;
		this.profitMoneyOrderNum = profitMoneyOrderNum;
	}
	
	
}
