package com.imooc.o2o.service;

import java.io.InputStream;
import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;

public interface ProductService {
	/**
	 * 添加商品
	 * 1、处理缩略图
	 * 2、处理商品详情图片
	 * 3、添加商品信息
	 * @param product
	 * @param thumbnail
	 * @param thumbnailName
	 * @param productImgList
	 * @param productImgNameList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductExecution addProduct(Product product,ImageHolder thumbnail,
			List<ImageHolder> productImgList) throws ProductCategoryOperationException;
}