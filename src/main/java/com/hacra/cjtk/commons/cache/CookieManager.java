package com.hacra.cjtk.commons.cache;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * CookieManager
 * 
 * @author Hacra
 * @date 2020-12-07
 */
public class CookieManager {

	private volatile static CookieManager cookieManager;
	
	private CookieManager() {}
	
	/**
	 * 获取唯一实例化对象
	 * @return
	 */
	public static CookieManager getInstance() {
		if (cookieManager == null) {
			synchronized(CookieManager.class) {
				if (cookieManager == null) {
					cookieManager = new CookieManager();
				}
			}
		}
		return cookieManager;
	}
	
	/**
	 * 设置 Cookie（生成时间为1天）
	 * @param name 名称
	 * @param value 值
	 */
	public void setCookie(HttpServletResponse response, String name, String value) {
		setCookie(response, name, value, "/", 60*60*24);
	}
	
	/**
	 * 获得指定Cookie的值
	 * @param name 名称
	 * @return 值
	 */
	public String getCookie(HttpServletRequest request, String name) {
		String val = getCookie(request, null, name, false);
		return val == null ? "" : val;
	}
	
	/**
	 * 设置 Cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param path
	 * @param maxAge
	 */
	private void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge) {
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		try {
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 获得指定Cookie的值
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param name 名字
	 * @param isRemove 是否移除
	 * @return 值
	 */
	private String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					try {
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					if (isRemove) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return value;
	}
}
