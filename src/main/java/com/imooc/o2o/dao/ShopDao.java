package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.imooc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 分页查询店铺，可输入条件：店铺名（模糊）、店铺状态、店铺类别、区域ID、owner
	 * 有多个@Param的原因：这个方法参数有多个，必须要指定param的唯一标识
	 */
	/**
	 * 
	 * @param shopCondition 查询条件
	 * @param rowIndex 从第几行开始取数据
	 * @param pageSize 要返回多少行数据
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
			@Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);
	
	/**
	 * 返回queryShopList总数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
	/**
	 * 通过shop id 查询店铺
	 * 
	 * @param shop Id
	 * @return shop
	 */
	Shop queryByShopId(long shopId);
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	//插入店铺，返回值为insert影响的行数
	int insertShop(Shop shop);
	/**
	 * 更新店铺信息
	 */
	int updateShop(Shop shop);
}
