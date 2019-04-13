package com.imooc.o2o.service.impl;

import java.io.File;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDao shopDao;
	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, File shopImg) {
		//店铺不存在时，ShopExecution使用构造器--生成ShopExecution对象
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		//try {
			//给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.insertShop(shop);
			//如果影响行数<=0，则添加失败
			if(effectedNum<=0) {
				//抛出异常，终止事务执行
				//这里是RuntimeException而不是Exception的原因：只有RuntimeException 事务才能终止
				throw new ShopOperationException("店铺创建失败");
			} else {
				//添加成功后，先判断传入的图片是不是为空 
				if(shopImg != null) {
					//存储图片
//					try {
//						addShopImg(shop,shopImg);
//					}catch (Exception e) {
//						
//						throw new ShopOperationException("addShopImg error"+e.getMessage());
//						
//					}
					addShopImg(shop,shopImg);
					//更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
//		}catch(Exception e){
//			throw new ShopOperationException("addShop error"+e.getMessage());
//		}
		//返回状态值-CHECK(待审核)
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}
	
	private void addShopImg(Shop shop, File shopImg) {
		//获取shop图片目录的相对路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);//得出图片实际存储的绝对值路径，存片存入后返回图片的相对值路径
		shop.setShopImg(shopImgAddr);
	}
 
}
