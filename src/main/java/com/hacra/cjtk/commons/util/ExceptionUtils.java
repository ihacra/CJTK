package com.hacra.cjtk.commons.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * ExceptionUtils
 * 
 * @author Hacra
 * @date 2020-12-16
 */
public class ExceptionUtils {

	/**
	 * 在request中获取异常类
	 * @param request
	 * @return 
	 */
	public static Throwable getThrowable(HttpServletRequest request){
		Throwable ex = null;
		if (request.getAttribute("exception") != null) {
			ex = (Throwable) request.getAttribute("exception");
		} else if (request.getAttribute("javax.servlet.error.exception") != null) {
			ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
		}
		return ex;
	}
	
	/**
	 * 将ErrorStack转化为String.
	 * @param e
	 * @throws IOException 
	 */
	public static String getStackTraceAsString(Throwable ex) throws IOException  {
		if (ex == null) {
			return null;
		}
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		printWriter.close();
		stringWriter.close();
		return stringWriter.toString();
	}
}
