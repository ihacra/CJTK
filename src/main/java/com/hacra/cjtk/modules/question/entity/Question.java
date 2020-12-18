package com.hacra.cjtk.modules.question.entity;

import com.hacra.cjtk.commons.base.BaseEntity;
import com.hacra.cjtk.commons.util.excel.ExcelField;

/**
 * entity
 * 
 * @author Hacra
 * @date 2020-11-10
 */
public class Question extends BaseEntity<Question> {

	private static final long serialVersionUID = 1L;
	private String subject;		// 科目
	private String title;		// 题目
	private String optionA;		// 选项A
	private String optionB;		// 选项B
	private String optionC;		// 选项C
	private String optionD;		// 选项D
	private String answer;		// 答案
	private String analysis;	// 解析
	private String label;		// 标签分类
	private String type;		// 题目类型（0：选择；1：填空）
	
	public Question() {
		super();
	}
	
	public Question(Integer id) {
		super(id);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@ExcelField(title = "题目", sort = 10, groups = {1, 2})
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ExcelField(title = "选项A", sort = 20, groups = {1})
	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	@ExcelField(title = "选项B", sort = 30, groups = {1})
	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	@ExcelField(title = "选项C", sort = 40, groups = {1})
	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	@ExcelField(title = "选项D", sort = 50, groups = {1})
	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	@ExcelField(title = "答案", sort = 60, groups = {1, 2})
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@ExcelField(title = "解析", sort = 70, groups = {1, 2})
	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@ExcelField(title = "标签", sort = 80, groups = {1, 2})
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
