package com.imooc.o2o.dto;

import java.util.List;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopExecution {
	/**
	 * 状态 为枚举类型，在ShopStateEnum.java中定义
	 */
	//结果状态
	private int state;
	//状态标识(以文字的形式解释state的作用)
	private String stateInfo;
	//店铺数量
	private int count;
	
	//操作的shop（增删改店铺的时候用到）
	private Shop shop;
	//shop列表（查询店铺列表的时候使用）
	private List<Shop> shopList;
	
	public ShopExecution() {
		
	}
	//在店铺操作失败时使用的构造器--生成ShopExecution对象
	public ShopExecution(ShopStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//在店铺操作在成功时使用的构造器-返回一个店铺对象
	public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	//在店铺操作在成功时使用的构造器-返回一个店铺对象列表
	public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopList = shopList;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public List<Shop> getShopList() {
		return shopList;
	}
	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}
