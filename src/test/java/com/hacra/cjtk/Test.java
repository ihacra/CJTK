package com.hacra.cjtk;

import java.io.UnsupportedEncodingException;

/**
 * Test
 * 
 * @author Hacra
 * @date 2020-12-08
 */
public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String a = "能的是（　）。";
		a = a.replace("　", " ");
		String regex = "(（\\s*?）|\\(\\s*?\\))";
		System.out.println(a);
		System.out.println(a.replaceAll(regex, "（ ）"));
		
		
		a = "会计以（ ）作为";
		System.out.println(a);
		System.out.println(a.replaceAll(regex, "（ ）"));
	}
}
