package com.hacra.cjtk.commons.util.excel;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.hacra.cjtk.commons.util.StringUtils;

/**
 * ExportExcel
 * 
 * @author Hacra
 * @version 2020/12/12
 */
public class ExportExcel {

	/**
	 * 工作薄对象
	 */
	private SXSSFWorkbook workbook;
	
	/**
	 * 工作表对象
	 */
	private SXSSFSheet sheet;
	
	/**
	 * 行号
	 */
	private int rownum;
	
	/**
	 * 样式列表
	 */
	private Map<String, CellStyle> styleMap;
	
	/**
	 * 注解列表（Object[]{ExcelField, Method}）
	 */
	private List<Object[]> annotationList;
	
	/**
	 * 构造函数
	 * @param title
	 * @param clazz
	 * @param group
	 */
	public ExportExcel(String title, Class<?> clazz, int group) {
		this.workbook = new SXSSFWorkbook(500);
		this.sheet = this.workbook.createSheet("Export");
		this.styleMap = this.createStyles();
		this.annotationList = this.getAnnotationList(clazz, group);
		this.initialize(title);
	}
	
	/**
	 * 添加数据
	 * @param <T>
	 * @param list
	 * @return
	 */
	public <T> ExportExcel setDataList(List<T> list) {
		for (T t : list) {
			int colNum = 0;
			Row row = this.sheet.createRow(this.rownum++);
			for (Object[] os : this.annotationList) {
				try {
					Cell cell = row.createCell(colNum++);
					cell.setCellStyle(styleMap.get("data"));
					Method method = (Method) os[1];
					Object val = method.invoke(t);
					if (val instanceof String) {
						cell.setCellValue((String)val);
					} else if (val instanceof Integer) {
						cell.setCellValue((Integer)val);
					} else if (val instanceof Long) {
						cell.setCellValue((Long)val);
					} else if (val instanceof Double) {
						cell.setCellValue((Double)val);
					} else if (val instanceof Float) {
						cell.setCellValue((Float)val);
					} else if (val instanceof Date) {
						cell.setCellValue((Date)val);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		this.sheet.trackAllColumnsForAutoSizing();
		for (int i = 0; i < this.annotationList.size(); i++) {
			this.sheet.autoSizeColumn(i); 
			if (this.sheet.getColumnWidth(i) > 13000) {
				this.sheet.setColumnWidth(i, 13000);
			}
		}
		return this;
	}
	
	/**
	 * 输出文件
	 * @param request
	 * @param response
	 * @param fileName
	 * @return
	 */
	public void write(HttpServletRequest request, HttpServletResponse response, String fileName) {
		try {
			String userAgent = request.getHeader("User-Agent");
			if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
	        	fileName = URLEncoder.encode(fileName, "UTF-8");
	        } else {
	        	fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
	        }
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-disposition", "attachment; filename="+fileName);
			this.workbook.write(response.getOutputStream());
			this.workbook.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建表格样式
	 * @return
	 */
	private Map<String, CellStyle> createStyles() {
		styleMap = new HashMap<String, CellStyle>(8);
		// title
		CellStyle style = this.workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setWrapText(true);
		Font titleFont = this.workbook.createFont();
		titleFont.setBold(true);
		titleFont.setFontName("宋体");
		titleFont.setFontHeightInPoints((short)12);
		style.setFont(titleFont);
		styleMap.put("title", style);
		// header
		style = this.workbook.createCellStyle();
		style.cloneStyleFrom(styleMap.get("title"));
		styleMap.put("header", style);
		// data
		style = this.workbook.createCellStyle();
		style.cloneStyleFrom(styleMap.get("title"));
		Font dataFont = this.workbook.createFont();
		dataFont.setBold(false);
		dataFont.setFontName("宋体");
		dataFont.setFontHeightInPoints((short)12);
		style.setFont(dataFont);
		styleMap.put("data", style);
		return styleMap;
	}
	
	/**
	 * 获取被注解的方法
	 * @param <T>
	 * @param clazz 导出对象类型
	 * @param group 导出分组
	 * @return
	 */
	private <T> List<Object[]> getAnnotationList(Class<T> clazz, int group) {
		annotationList = new ArrayList<Object[]>(16);
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			ExcelField excelField = method.getAnnotation(ExcelField.class);
			if (excelField != null && (excelField.type() == 0 || excelField.type() == 2)) {
				if (group == 0) {
					annotationList.add(new Object[]{excelField, method});
				} else {
					for (int g : excelField.groups()) {
						if (g == group) {
							annotationList.add(new Object[]{excelField, method});
							break;
						}
					}
				}
			}
		}
		Collections.sort(annotationList, new Comparator<Object[]>() {
			@Override
			public int compare(Object[] o1, Object[] o2) {
				return ((ExcelField)o1[0]).sort() - ((ExcelField)o2[0]).sort();
			}
		});
		return annotationList;
	}
	
	/**
	 * 初始化函数
	 * @param title 表格标题，传“空值”，表示无标题
	 */
	private void initialize(String title) {
		this.rownum = 0;
		// 获取列名
		List<String> headerList = new ArrayList<>(16);
		for (Object[] os : this.annotationList) {
			headerList.add(((ExcelField)os[0]).title());
		}
		if (headerList.isEmpty()) {
			throw new RuntimeException("headerList not null!");
		}
		// 创建title
		if (StringUtils.isNotBlank(title)) {
			Row titleRow = this.sheet.createRow(this.rownum++);
			titleRow.setHeightInPoints(30);
			Cell titleCell = titleRow.createCell(0);
			titleCell.setCellStyle(styleMap.get("title"));
			titleCell.setCellValue(title);
			this.sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headerList.size()-1));
		}
		// 创建header
		Row headerRow = this.sheet.createRow(this.rownum++);
		headerRow.setHeightInPoints(16);
		for (int i = 0; i < headerList.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellStyle(styleMap.get("header"));
			cell.setCellValue(headerList.get(i));
		}
	}
}

