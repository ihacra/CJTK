package com.hacra.cjtk.commons.util;

/**
 * StringUtils
 * 
 * @author Hacra
 * @date 2020-11-10
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	
	public static int toInt(String v) {
		if (v != null && v.matches("^[0-9]+$")) {
			return Integer.parseInt(v);
		} else {
			return 0;
		}
	}
}
