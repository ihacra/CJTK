package com.hacra.cjtk.commons.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ExcelField
 * 添加与对应字段的“get”方法上
 * 
 * @author Hacra
 * @date 2020-12-10
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	/**
	 * 导出字段标题
	 */
	String title() default "";
	
	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 */
	int type() default 0;
	
	/**
	 * 导出字段字段排序（升序）
	 */
	int sort() default 0;
	
	/**
	 * 字段归属组（根据分组导出导入）
	 */
	int[] groups() default {};
}
