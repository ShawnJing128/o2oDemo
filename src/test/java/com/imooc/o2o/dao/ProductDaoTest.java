package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
	@Autowired ProductDao productDao;
	@Autowired ProductImgDao productImgDao;
	@Test
	// @Ignore
	public void testAInsertProduct() throws Exception{
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(1L);
		Product product1 = new Product();
		product1.setProductName("测试9.5");
		product1.setProductDesc("测试9.5 Desc");
		product1.setImgAddr("测试9.5");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		Product product2 = new Product();
		product2.setProductName("测试9.5 2");
		product2.setProductDesc("测试9.5 Desc2");
		product2.setImgAddr("测试9.5 2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc1);
		Product product3 = new Product();
		product3.setProductName("测试9.5 3");
		product3.setProductDesc("测试9.5 Desc3");
		product3.setImgAddr("测试9.5 3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(pc1);
		// 判断添加是否成功
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}
	@Test
	@Ignore
	public void testBQueryProductList() throws Exception{
		Product productCondition = new Product();
		//分页查询
		List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
		System.out.println(productList.size());
		//查询名称为测试的商品总数
		int count = productDao.queryProductCount(productCondition);
		System.out.println(count);
		//使用模糊查询
		productCondition.setProductName("测试");
		productList = productDao.queryProductList(productCondition, 0, 3);
		System.out.println(productList.size());
		count = productDao.queryProductCount(productCondition);
		System.out.println(count);
	}
	@Test
	@Ignore
	public void testCQueryProductByProductId() {
		long productId = 1;
		//初始化粮价格商品详情图实例作为productId为1的商品下的详情图片
		//批量插入到商品详情图表中
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片1");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(productId);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		//查询productId为1的商品信息并校验返回的详情图实例列表size是否为2
		Product product = productDao.queryProductById(productId);
		System.out.println(product.getProductImgList().size());
		//assertEquals(2, product.getProductImgList().size());
		//删除新增的两个商品详情图实例
		effectedNum = productImgDao.deleteProductImgByProductId(productId);
		System.out.println(effectedNum);
		//assertEquals(2, effectedNum);
	}
	@Test
	@Ignore
	public void testDUpdateProduct() throws Exception{
		Product product  = new Product();
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1L);
		pc.setProductCategoryId(3L);
		product.setProductId(1L);
		product.setShop(shop);
		product.setProductName("第二个产品");
		product.setProductCategory(pc);
		//修改productId为1的商品的名称
		//以及商品类别并校验影响的行数是否为1
		int effectedNum = productDao.updateProduct(product);
		assertEquals(1, effectedNum);
	}
	
}