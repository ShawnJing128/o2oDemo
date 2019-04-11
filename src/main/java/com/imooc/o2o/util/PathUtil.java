package com.imooc.o2o.util;

public class PathUtil {
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")) {
			basePath = "D:/project/image/";
		} else {
			basePath = "/Users/shawn/Document/实战练习/image/";
		}
	}
}
