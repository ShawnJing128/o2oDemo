package com.imooc.o2o.entity;

import java.util.Date;

/**
 * 区域
 * @author shawn
 *
 */
public class Area {
	//ID
	/*
	 * 设置为引用类型而不是基本类型原因：
	 * 基本类型不赋值时默认为0，但有的值我们不希望默认为0，希望空值就是空值，因此用引用类型
	 */
	private Integer areaId;
	//区域名称
	private String areaName;
	//权重-用于优先级排序
	private Integer priority;
	//创建时间
	private Date createTime;
	//更新时间
	private Date lastEditTime;
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
	
}
