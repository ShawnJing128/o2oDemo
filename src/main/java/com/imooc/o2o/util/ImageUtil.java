package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


/**
 * 将图片改变大小、加水印、压缩、输出在同级目录下
 */
public class ImageUtil {
	public static void main(String[] args) throws IOException {
		//获取classPath的绝对值路径
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		Thumbnails.of(new File("/Users/shawn/Document/实战练习/image/xiaohuangren.jpg"))
		.size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
		.outputQuality(0.8f).toFile("/Users/shawn/Document/实战练习/image/xiaohuangrennew.jpg");
	}
}
