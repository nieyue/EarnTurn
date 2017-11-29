package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章日数据
 * @author 聂跃
 * @date 2017年7月25日
 */
public class ArticleDayDataDTO implements Serializable{

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
	 * 阅读数
	 */
	private Long  number;
	/**
	 * 用户收益
	 */
	private Double  userNowTotalPrice;
	/**
	 * 用户自身收益
	 */
	private Double  userSelfNowTotalPrice;
	/**
	 * 用户徒弟收益
	 */
	private Double  userPartnerNowTotalPrice;
	/**
	 * 日期
	 */
	private Date  createDate;
	
	public ArticleDayDataDTO(Long pvs, Long uvs, Long ips, Long number, Double userSelfNowTotalPrice,
			Double userPartnerNowTotalPrice, Double userNowTotalPrice, Date createDate) {
		super();
		this.pvs = pvs;
		this.uvs = uvs;
		this.ips = ips;
		this.number = number;
		this.userSelfNowTotalPrice = userSelfNowTotalPrice;
		this.userPartnerNowTotalPrice = userPartnerNowTotalPrice;
		this.userNowTotalPrice = userNowTotalPrice;
		this.createDate = createDate;
	}
	public ArticleDayDataDTO() {
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
	public Double getUserNowTotalPrice() {
		return userNowTotalPrice;
	}
	public void setUserNowTotalPrice(Double userNowTotalPrice) {
		this.userNowTotalPrice = userNowTotalPrice;
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
	public Double getUserSelfNowTotalPrice() {
		return userSelfNowTotalPrice;
	}
	public void setUserSelfNowTotalPrice(Double userSelfNowTotalPrice) {
		this.userSelfNowTotalPrice = userSelfNowTotalPrice;
	}
	public Double getUserPartnerNowTotalPrice() {
		return userPartnerNowTotalPrice;
	}
	public void setUserPartnerNowTotalPrice(Double userPartnerNowTotalPrice) {
		this.userPartnerNowTotalPrice = userPartnerNowTotalPrice;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	
	
}
