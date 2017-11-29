package com.nieyue.bean;

import java.io.Serializable;
/**
 * 数据rabbitmq对象
 * @author 聂跃
 * @date 2017年6月6日
 */
public class DataRabbitmqDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *文章id外键
	 */
	private  Integer articleId ;
	/**
	 *账户id外键
	 */
	private  Integer acountId ;
	/**
	 *uv
	 */
	private  Integer uv ;
	/**
	 *ip
	 */
	private  String ip ;
	
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
	public Integer getUv() {
		return uv;
	}
	public void setUv(Integer uv) {
		this.uv = uv;
	}
	public DataRabbitmqDTO() {
		super();
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public DataRabbitmqDTO(Integer articleId, Integer acountId, Integer uv, String ip) {
		super();
		this.articleId = articleId;
		this.acountId = acountId;
		this.uv = uv;
		this.ip = ip;
	}
	@Override
	public String toString() {
		return "DataRabbitmqDTO [articleId=" + articleId + ", acountId=" + acountId + ", uv=" + uv + ", ip=" + ip + "]";
	}
	
}

