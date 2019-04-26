package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 判断验证码是否符合预期
 * @author shawn
 *
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		//实际的验证码
		String verifyCodeExcepted = (String)request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//输入的验证码
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if(verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExcepted)) {
			return false;
		}
		return true;
	}
}
