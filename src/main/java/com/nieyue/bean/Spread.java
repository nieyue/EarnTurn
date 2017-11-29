package com.nieyue.bean;

import java.io.Serializable;
import java.util.Date;
/**
 * 推广
 * @author 聂跃
 * @date 2017年4月12日
 */
public class Spread implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 推广id
	 */
	private Integer spreadId;
	/**
	 * 推广平台，默认0安卓，1为IOS
	 */
	private Integer platform;
	/**
	 * 推广名
	 */
	private String name;
	/**
	 * 推广方式
	 */
	private String method;
	/**
	 * app链接
	 */
	private String link;
	/**
	 * 计费方式，0注册，1下载
	 */
	private Integer model;
	/**
	 * 单价
	 */
	private Double unitPrice;
	/**
	 * 总价
	 */
	private Double totalPrice;
	/**
	 * 下载次数
	 */
	private Long downloadNumber;
	/**
	 * 注册次数
	 */
	private Long registerNumber;
	/**
	 * 消耗金额
	 */
	private Double nowTotalPrice;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 状态  正常，完成
	 */
	private String status;
	public Integer getSpreadId() {
		return spreadId;
	}
	public void setSpreadId(Integer spreadId) {
		this.spreadId = spreadId;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Integer getModel() {
		return model;
	}
	public void setModel(Integer model) {
		this.model = model;
	}
	public Double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getDownloadNumber() {
		return downloadNumber;
	}
	public void setDownloadNumber(Long downloadNumber) {
		this.downloadNumber = downloadNumber;
	}
	public Long getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(Long registerNumber) {
		this.registerNumber = registerNumber;
	}
	public Double getNowTotalPrice() {
		return nowTotalPrice;
	}
	public void setNowTotalPrice(Double nowTotalPrice) {
		this.nowTotalPrice = nowTotalPrice;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Spread(Integer spreadId, Integer platform, String name, String method, String link, Integer model,
			Double unitPrice, Double totalPrice, Long downloadNumber, Long registerNumber, Double nowTotalPrice,
			Date createDate, Date endDate, String status) {
		super();
		this.spreadId = spreadId;
		this.platform = platform;
		this.name = name;
		this.method = method;
		this.link = link;
		this.model = model;
		this.unitPrice = unitPrice;
		this.totalPrice = totalPrice;
		this.downloadNumber = downloadNumber;
		this.registerNumber = registerNumber;
		this.nowTotalPrice = nowTotalPrice;
		this.createDate = createDate;
		this.endDate = endDate;
		this.status = status;
	}
	public Spread() {
		super();
	}
	
}
