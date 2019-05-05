package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;

public interface ProductDao {
	/**
	 * 	添加商品
	 * @param product
	 * @return
	 */
	int insertProduct(Product product);
}
