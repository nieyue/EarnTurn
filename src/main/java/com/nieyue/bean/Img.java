package com.nieyue.bean;

import java.io.Serializable;
/**
 * 图片类
 * @author yy
 *
 */
public class Img implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图片id
	 */
	private Integer imgId;
	/**
	 * 图片地址
	 */
	private String imgAddress;
	/**
	 * 图片顺序
	 */
	private Integer number;
	/**
	 * 文章id
	 */
	private Integer articleId;
	public Img(Integer imgId, String imgAddress,Integer number, Integer articleId) {
		super();
		this.imgId = imgId;
		this.imgAddress = imgAddress;
		this.number=number;
		this.articleId = articleId;
	}
	public Img() {
		super();
	}
	public Integer getImgId() {
		return imgId;
	}
	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}
	public String getImgAddress() {
		return imgAddress;
	}
	public void setImgAddress(String imgAddress) {
		this.imgAddress = imgAddress;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
