package com.imooc.o2o.entity;

import java.util.Date;

/**
 * 产品类别
 * @author shawn
 *
 */
public class ProductCategory {
	private Long productCategoryId;
	private Long shopId;//没有用shop实体类原因：我们获取ProductCategory时不需要获取shop类中除shopId外的其他信息
	private String productCategoryName;
	private Integer priority;
	private Date createTime;
	public Long getProductCategoryId() {
		return productCategoryId;
	}
	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
	public String getProductCategoryName() {
		return productCategoryName;
	}
	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
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
	
}
