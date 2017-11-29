package com.nieyue.bean;

import java.io.Serializable;

/**
 * 用户收益混合类
 * 
 * @author yy
 * 
 */
public class ProfitArticleDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 收益
	 */
	private Profit profit;
	/**
	 * 文章
	 */
	private Article article;
	public Profit getProfit() {
		return profit;
	}
	public void setProfit(Profit profit) {
		this.profit = profit;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ProfitArticleDTO(Profit profit, Article article) {
		super();
		this.profit = profit;
		this.article = article;
	}
	public ProfitArticleDTO() {
		super();
	}
	

}
