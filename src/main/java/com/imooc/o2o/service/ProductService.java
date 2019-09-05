package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.exceptions.ProductOperationException;

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
	/**
	 * 通过商品id查询唯一的商品信息
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	/**
	 * 修改商品信息以及图片处理
	 * @param product
	 * @param thumbnail
	 * @param productImgHolderList
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution modifyProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgHolderList)
	throws ProductOperationException;
	/**
	 * 查询商品列表并分页，可输入条件：商品名（模糊），商品状态，店铺Id，商品类别
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition,int pageIndex, int pageSize);
}