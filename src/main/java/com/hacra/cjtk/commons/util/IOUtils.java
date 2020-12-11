package com.hacra.cjtk.commons.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * IOUtils
 * 
 * @author Hacra
 * @date 2020-12-10
 */
public class IOUtils {

	/**
	 * 关闭IO流
	 * @param obj
	 */
	public static void close(Closeable obj) {
		if (obj != null) {
			try {
				obj.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
