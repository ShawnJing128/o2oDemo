package com.imooc.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


/**
 * 将图片改变大小、加水印、压缩、输出在同级目录下
 */
public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class); 
	/**
	 * 将CommonsMultipartFile转换成File
	 * @param cFile
	 * @return
	 */
	public static File transferCommonsMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);//将cFile的文件流写入到新创建的文件newFile里
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	/**
	 * 处理缩略图，并返回新生成图片的相对值路径
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(InputStream thumbnailInputStream,String fileName,String targetAddr) {
		//由于用户上传的图片可能重名，因此要用系统随机生成的不重名的文件名
		String realFileName = getRandomFileName();//随机名
		String extension = getFileExtension(fileName);//扩展名
		makeDirPath(targetAddr);//目标目录可能不存在，要可以自动创建目录
		String relativeAddr = targetAddr + realFileName + extension;//targetAddr是相对路径
		logger.debug("current relativeAddr is: " + relativeAddr);
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);//对路径进行拼接，形成全路径
		logger.debug("current complete addr is: " + PathUtil.getImgBasePath() + relativeAddr);
		try {
			//缩略图 加水印
			System.out.println("basepath:---"+basePath);
			Thumbnails.of(thumbnailInputStream).size(200, 200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
			.outputQuality(0.8f).toFile(dest);
		} catch(IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		/**
		 *  返回图片相对路径地址原因：
		 *  1、数据库里有shop_img字段，存储处理图片后的地址 
		 *  2、防止程序迁移系统后也能照常读取图片，读取的时候由PathUtil.getImgBasePath() + relativeAddr进行拼接
		 */
		return relativeAddr;
	}
	/**
	 * 创建目标路径上涉及到的目录
	 * @param targetAddr
	 */
	private static void makeDirPath(String targetAddr) {
		//targetAddr是相对路径，因此要获取它的绝对路径
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if(!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}
	/**
	 * 获取输入文件流的扩展名
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		//获取最后的点号后的字符
		return fileName.substring(fileName.lastIndexOf("."));
	}
	/**
	 * 生成随机文件名，当前年月日小时分钟秒+五位随机数
	 * @return
	 */
	public static String getRandomFileName() {
		//获取随机五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return rannum + nowTimeStr;//字符串 + 整型  自动转换为字符串
	}
	public static void main(String[] args) throws IOException {
		//获取classPath的绝对值路径,在上面写成公有变量
		//String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		Thumbnails.of(new File("/Users/shawn/Document/实战练习/image/xiaohuangren.jpg"))
		.size(200,200).watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
		.outputQuality(0.8f).toFile("/Users/shawn/Document/实战练习/image/xiaohuangrennew.jpg");
	}
	/**
	 * storePath是文件路径还是目录路径
	 * 如果是文件路径，则删除该文件
	 * 如果是目录路径，则删除该目录下的所有文件
	 * @param storePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);//全路径
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File files[] = fileOrPath.listFiles();
				for(int i = 0; i < files.length; i++) {
					files[i].delete();
				}
			}
			fileOrPath.delete();
		}
	}
}
