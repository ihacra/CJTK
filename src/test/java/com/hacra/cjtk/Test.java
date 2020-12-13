package com.hacra.cjtk;

import com.hacra.cjtk.commons.util.StringUtils;

/**
 * Test
 * 
 * @author Hacra
 * @date 2020-12-08
 */
public class Test {

	public static void main(String[] args) {
		System.out.println(StringUtils.trimToEmpty(null));
		System.out.println(StringUtils.trimToEmpty(""));
		System.out.println(StringUtils.trimToEmpty("  "));
		System.out.println(StringUtils.trimToEmpty("123"));
	}
}
