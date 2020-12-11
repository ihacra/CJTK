package com.hacra.cjtk.commons.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hacra.cjtk.commons.util.IOUtils;
import com.hacra.cjtk.commons.util.StringUtils;

/**
 * ImportExcel
 * 
 * @author Hacra
 * @date 2020-12-10
 */
public class ImportExcel {

	/**
	 * 工作薄对象
	 */
	private Workbook workbook;
	
	/**
	 * 工作表对象
	 */
	private Sheet sheet;
	
	/**
	 * 数据行号
	 */
	private int dataNum;
	
	/**
	 * 构造函数
	 * @param multipartFile
	 * @throws IOException
	 */
	public ImportExcel(MultipartFile multipartFile, int sheetIndex, int dataNum) throws IOException {
		this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), sheetIndex, dataNum);
	}
	
	/**
	 * 构造函数
	 * @param fileName 文件名称
	 * @param iStream io流
	 * @param sheetIndex sheet序号
	 * @param dataNum 数据行号
	 */
	public ImportExcel(String fileName, InputStream iStream, int sheetIndex, int dataNum) {
		try {
			if (StringUtils.isBlank(fileName)) {
				throw new RuntimeException("导入文档不能为空!");
			} else if (fileName.toLowerCase().endsWith("xlsx")) {
				this.workbook = new XSSFWorkbook(iStream);
			} else {
				throw new RuntimeException("文档格式不正确!");
			}
			if (this.workbook.getNumberOfSheets() < sheetIndex) {
				throw new RuntimeException("文档中没有工作表!");
			}
			this.sheet = this.workbook.getSheetAt(sheetIndex);
			this.dataNum = dataNum;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.close(iStream);
		}
	}
	
	/**
	 * 获取导入数据列表
	 * @param <T>
	 * @param clazz 导入对象类型
	 * @param group 导入分组
	 * @return
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public <T> List<T> getDataList(Class<T> clazz, int group) throws InstantiationException, IllegalAccessException {
		List<Object[]> annotationList = getAnnotationList(clazz, group);
		List<T> dataList = new ArrayList<>();
		int lastRowNum = this.sheet.getLastRowNum();
		for (int i = this.dataNum; i <= lastRowNum; i++) {
			T entity = clazz.newInstance();
			int colNum = 0;
			Row row = this.sheet.getRow(i);
			for (Object[] os : annotationList) {
				Object val = this.getCellValue(row, colNum++);
				if (val != null) {
					Method method = (Method) os[1];
					Class<?> valType = method.getReturnType();
					try {
						if (valType == String.class) {
							val = val.toString();
						} else if (valType == Integer.class) {
							val = Double.valueOf(val.toString()).intValue();
						} else if (valType == Long.class) {
							val = Double.valueOf(val.toString()).longValue();
						} else if (valType == Float.class) {
							val = Float.valueOf(val.toString());
						} else if (valType == Double.class) {
							val = Double.valueOf(val.toString());
						}
						this.invokeMethod(entity, method, valType, val);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			dataList.add(entity);
		}
		return dataList;
	}
	
	/**
	 * 获取被注解的方法
	 * @param <T>
	 * @param clazz 导入对象类型
	 * @param group 导入分组
	 * @return
	 */
	private <T> List<Object[]> getAnnotationList(Class<T> clazz, int group) {
		List<Object[]> annotationList = new ArrayList<>();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			ExcelField excelField = method.getAnnotation(ExcelField.class);
			if (excelField != null && (excelField.type() == 0 || excelField.type() == 1)) {
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
	 * 获取单元格的值
	 * @param row
	 * @param colNum
	 * @return
	 */
	public Object getCellValue(Row row, int colNum) {
		Object val = null;
		Cell cell = row.getCell(colNum);
		if (cell != null) {
			switch(cell.getCellType()) {
			case NUMERIC: 
				val = cell.getNumericCellValue();
				break;
			case STRING:
				val = cell.getStringCellValue();
				break;
			case FORMULA:
				break;
			case BLANK:
				break;
			case BOOLEAN:
				val = cell.getBooleanCellValue();
				break;
			case ERROR:
				val = cell.getErrorCellValue();
				break;
			default:
				break;
			}
		}
		return val;
	}
	
	/**
	 * 使用反射为对象赋值
	 * @param entity 实体类
	 * @param method get方法
	 * @param valType 参数类型
	 * @param val 值
	 * @throws Exception
	 */
	private void invokeMethod(Object entity, Method method, Class<?> valType, Object val) throws Exception {
		String methodName = "s" + method.getName().substring(1);
		method = entity.getClass().getDeclaredMethod(methodName, valType);
		method.setAccessible(true);
		method.invoke(entity, val);
	}
}
