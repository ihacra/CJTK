package com.hacra.cjtk.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.hacra.cjtk.modules.question.utils.QuestionUtils;

/**
 * RequestFilter
 * 
 * @author Hacra
 * @date 2020-12-14
 */
public class RequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setAttribute("title", "题库("+QuestionUtils.getSubjectVal((HttpServletRequest)request)+")");
		chain.doFilter(request, response);
	}
}
