package com.hacra.cjtk.commons.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContextUtils
 * 
 * @author Hacra
 * @version 2020/12/12
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringContextUtils.applicationContext = arg0;
	}

	/**
	 * 通过Clas获取Bean
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return SpringContextUtils.applicationContext.getBean(clazz);
	}
}
