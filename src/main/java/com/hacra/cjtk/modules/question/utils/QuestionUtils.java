package com.hacra.cjtk.modules.question.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hacra.cjtk.commons.util.CookieUtils;

/**
 * QuestionUtils
 * 
 * @author Hacra
 * @version 2020/12/12
 */
public class QuestionUtils {

	private static Map<String, String> subjectMap;				// 题库科目Map
	public static final String SUBJECT_VAL = "SUBJECT_VAL";		// 题库科目名称
	public static final String SUBJECT_KEY = "SUBJECT_KEY";		// 题库科目键值
	
	/**
	 * 获取题库科目
	 * @return
	 */
	public static Map<String, String> getSubjectMap() {
		if (subjectMap == null) {
			subjectMap = new LinkedHashMap<>(8);
			subjectMap.put("0", "基础会计学");
			subjectMap.put("1", "消费经济学");
		}
		return subjectMap;
	}
	
	/**
	 * 获取题库科目名称
	 * @return
	 */
	public static String getSubjectVal(HttpServletRequest request) {
		return CookieUtils.getCookie(request, SUBJECT_VAL);
	}
	
	/**
	 * 获取题库科目键值
	 * @return
	 */
	public static String getSubjectKey(HttpServletRequest request) {
		return CookieUtils.getCookie(request, SUBJECT_KEY);
	}
	
	/**
	 * 存储题库科目名称
	 * @param request
	 * @param val
	 */
	public static void setSubjectVal(HttpServletResponse response, String val) {
		CookieUtils.setCookie(response, SUBJECT_VAL, val);
	}
	
	/**
	 * 存储题库科目键值
	 * @param request
	 * @param val
	 */
	public static void setSubjectKey(HttpServletResponse response, String val) {
		CookieUtils.setCookie(response, SUBJECT_KEY, val);
	}
}
