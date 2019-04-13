package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 解析HttpServletRequest的参数
 * @author shawn
 *
 */
public class HttpServletRequestUtil {
	public static int getInt(HttpServletRequest request,String key) {
		try {
			//从request中对应键位为key的值，转成整型
			return Integer.decode(request.getParameter(key));
		} catch(Exception e) {
			return -1;
		}
	}
	
	public static long getLong(HttpServletRequest request,String key) {
		try {
			//从request中提取key，转成长整型
			return Long.valueOf(request.getParameter(key));
		} catch(Exception e) {
			return -1;
		}
	}
	
	public static Double getDouble(HttpServletRequest request,String key) {
		try {
			//从request中提取key，转成double型
			return Double.valueOf(request.getParameter(key));
		} catch(Exception e) {
			return -1d;
		}
	}
	
	public static boolean getBoolean(HttpServletRequest request,String key) {
		try {
			//从request中提取key，转成boolean型
			return Boolean.valueOf(request.getParameter(key));
		} catch(Exception e) {
			return false;
		}
	}
	
	public static String  getString(HttpServletRequest request,String key) {
		try {
			String result = request.getParameter(key);
			if(result != null) {
				result.trim();
			}
			if("".equals(result)) {
				result = null;
			}
			return result;
		} catch (Exception e) {
			return null;
		}
	}
}
