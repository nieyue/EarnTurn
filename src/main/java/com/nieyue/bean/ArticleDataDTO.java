package com.nieyue.bean;

import java.io.Serializable;

/**
 * 文章数据
 * @author 聂跃
 * @date 2017年7月25日
 */
public class ArticleDataDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 展示(PV)
	 */
	private Long  pvs;
	/**
	 * 独立访客(UV)
	 */
	private Long  uvs;
	/**
	 * IP
	 */
	private Long  ips;
	/**
	 * 广告主消耗额
	 */
	private Double  nowTotalPrice;
	/**
	 * 阅读数
	 */
	private Long  readingNumber;
	/**
	 * 用户收益
	 */
	private Double  userNowTotalPrice;
	/**
	 * 账户所有者Id
	 */
	private Integer  acountId;
	public ArticleDataDTO(Long pvs, Long uvs, Long ips, Double nowTotalPrice, Long readingNumber,
			Double userNowTotalPrice, Integer acountId) {
		super();
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.nowTotalPrice = nowTotalPrice;
		this.readingNumber = readingNumber;
		this.userNowTotalPrice = userNowTotalPrice;
		this.acountId = acountId;
	}
	public ArticleDataDTO() {
		super();
	}
	public Long getPvs() {
		return pvs;
	}
	public void setPvs(Long pvs) {
		this.pvs = pvs;
	}
	public Long getUvs() {
		return uvs;
	}
	public void setUvs(Long uvs) {
		this.uvs = uvs;
	}
	public Long getIps() {
		return ips;
	}
	public void setIps(Long ips) {
		this.ips = ips;
	}
	public Double getNowTotalPrice() {
		return nowTotalPrice;
	}
	public void setNowTotalPrice(Double nowTotalPrice) {
		this.nowTotalPrice = nowTotalPrice;
	}
	public Long getReadingNumber() {
		return readingNumber;
	}
	public void setReadingNumber(Long readingNumber) {
		this.readingNumber = readingNumber;
	}
	public Double getUserNowTotalPrice() {
		return userNowTotalPrice;
	}
	public void setUserNowTotalPrice(Double userNowTotalPrice) {
		this.userNowTotalPrice = userNowTotalPrice;
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
	
	
}
