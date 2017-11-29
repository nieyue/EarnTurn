package com.nieyue.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.nieyue.bean.Acount;
import com.nieyue.bean.AcountDTO;
import com.nieyue.bean.SpreadAcountDTO;

/**
 * 账户数据库接口
 * @author yy
 *
 */
@Mapper
public interface AcountDao {
	/** 新增账户*/	
	public boolean addAcount(Acount acount) ;	
	/** 微信账户登录 */
	public Acount weixinBaseAcountLogin(String uuid);	
	/** 删除账户 */	
	public boolean delAcount(Integer acountId) ;
	/** 更新账户*/	
	public boolean updateAcount(Acount acount);
	/** 装载账户 */	
	public Acount loadAcount(Integer acountId);	
	/** 登录管理员 */	
	public Acount loginAcount(
			@Param("adminName")String adminName,
			@Param("password")String password,
			@Param("acountId")Integer acountId);
	/** 账户总共数目 */	
	public int countAll(
			@Param("spreadId")Integer spreadId,
			@Param("phone")String phone,
			@Param("nickname")String nickname,
			@Param("minScale")Double minScale,
			@Param("maxScale")Double maxScale,
			@Param("masterId")Integer masterId,
			@Param("roleId")Integer roleId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("loginDate")Date loginDate
			);	
	/** 账户分页浏览合伙人数目排行榜 */
	public List<AcountDTO> browsePagingAcountByMasterId(
			@Param("masterId")Integer masterId,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
	/** 分页账户信息 */
	public List<Acount> browsePagingAcount(
			@Param("spreadId")Integer spreadId,
			@Param("phone")String phone,
			@Param("nickname")String nickname,
			@Param("minScale")Double minScale,
			@Param("maxScale")Double maxScale,
			@Param("masterId")Integer masterId,
			@Param("roleId")Integer roleId,
			@Param("status")Integer status,
			@Param("createDate")Date createDate,
			@Param("loginDate")Date loginDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
	/** 渠道分页账户信息 */
	public List<SpreadAcountDTO> browsePagingAcountBySpread(
			@Param("spreadId")Integer spreadId,
			@Param("createDate")Date createDate,
			@Param("loginDate")Date loginDate,
			@Param("pageNum")int pageNum,
			@Param("pageSize")int pageSize,
			@Param("orderName")String orderName,
			@Param("orderWay")String orderWay) ;		
}
