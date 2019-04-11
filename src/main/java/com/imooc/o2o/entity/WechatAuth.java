package com.imooc.o2o.entity;

import java.util.Date;

/**
 * 微信账号
 * @author shawn
 *
 */
public class WechatAuth {
	private Long wechatAuthId;
	//与微信公众号关联用
	private String openId;
	private Date createTime;
	//与实体类关联
	private PersonInfo personInfo;
	public Long getWechatAuthId() {
		return wechatAuthId;
	}
	public void setWechatAuthId(Long wechatAuthId) {
		this.wechatAuthId = wechatAuthId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public PersonInfo getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(PersonInfo personInfo) {
		this.personInfo = personInfo;
	}
	
}
