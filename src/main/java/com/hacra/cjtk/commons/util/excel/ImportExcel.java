package com.hacra.cjtk.commons.util.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
	public ImportExcel(MultipartFile multipartFile) throws IOException {
		this(multipartFile.getOriginalFilename(), multipartFile.getInputStream(), 0, 0);
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
	 * @param groups 导入分组
	 * @return
	 */
	public <T> List<T> getDataList(Class<T> clazz, int ...groups) {
		List<T> dataList = new ArrayList<>();
		
		return dataList;
	}
}
