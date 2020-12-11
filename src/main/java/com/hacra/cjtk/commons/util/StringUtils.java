package com.hacra.cjtk.commons.util;

/**
 * StringUtils
 * 
 * @author Hacra
 * @date 2020-11-10
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 固定字符串长度
	 * @param v
	 * @param n
	 * @return
	 */
	public static String formatLength(String v, int n) {
		if (v == null) {
			v = "";
		}
		if (v.length() < n) {
			n = n - v.length();
			String fillChar = "0";
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < n; i++) {
				sBuilder.append(fillChar);
			}
			sBuilder.append(v);
			return sBuilder.toString();
		} else if (v.length() > n) {
			return v.substring(0, n);
		}
		return v;
	}
}
