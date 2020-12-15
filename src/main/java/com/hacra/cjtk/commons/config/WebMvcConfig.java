package com.hacra.cjtk.commons.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hacra.cjtk.commons.filter.RequestFilter;
import com.hacra.cjtk.commons.interceptor.SubjectInterceptor;

/**
 * WebMvcConfig
 * 
 * @author Hacra
 * @date 2020-12-14
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new SubjectInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/", "/index/**", "/static/**", "/image/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
	@Bean
	public FilterRegistrationBean<RequestFilter> requestFilter() {
		FilterRegistrationBean<RequestFilter> filter = new FilterRegistrationBean<>();
		filter.setFilter(new RequestFilter());
		filter.addUrlPatterns("/*");
		filter.setName("requestFilter");
		filter.setOrder(1);
		return filter;
	}
}
