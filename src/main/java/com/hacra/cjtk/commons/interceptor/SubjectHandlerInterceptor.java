package com.hacra.cjtk.commons.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.question.utils.QuestionUtils;

/**
 * Cookie中是否存在科目信息
 * 
 * @author Hacra
 * @date 2020-12-14
 */
public class SubjectHandlerInterceptor implements HandlerInterceptor {

	/**
	 * Cookie中若不存在科目信息则重定向至首页
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String key = QuestionUtils.getSubjectKey(request);
		String val = QuestionUtils.getSubjectVal(request);
		if (StringUtils.isBlank(key) || StringUtils.isBlank(val)) {
			request.setAttribute("message", "需先选择对应科目！");
			request.getRequestDispatcher("/").forward(request, response);
			return false;
		} else {
			return true;
		}
	}
}
