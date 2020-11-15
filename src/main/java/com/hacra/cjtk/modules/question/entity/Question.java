package com.hacra.cjtk.modules.question.entity;

import com.hacra.cjtk.commons.base.BaseEntity;

/**
 * entity
 * 
 * @author Hacra
 * @date 2020-11-10
 */
public class Question extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String title;		// 题目
	private String optionA;		// 选项A
	private String optionB;		// 选项B
	private String optionC;		// 选项C
	private String optionD;		// 选项D
	private String answer;		// 答案
	private String analysis;	// 解析
	private String label;		// 标签分类
	private String type;		// 题目类型（0：选择；1：填空；2：判断）
	
	public Question() {
		super();
	}
	
	public Question(String id) {
		super(id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

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
