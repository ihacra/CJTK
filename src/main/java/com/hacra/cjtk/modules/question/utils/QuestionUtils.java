package com.hacra.cjtk.modules.question.utils;

import com.hacra.cjtk.commons.util.SpringContextUtils;
import com.hacra.cjtk.modules.question.dao.QuestionDao;
import com.hacra.cjtk.modules.question.entity.Question;

/**
 * QuestionUtils
 * 
 * @author Hacra
 * @version 2020/12/12
 */
public class QuestionUtils {

	private static QuestionDao questionDao = SpringContextUtils.getBean(QuestionDao.class);
	
	/**
	 * 获取当前题库数量
	 * @param type 问题类型
	 * @return
	 */
	public static int getCountOfQuestionsByType(String type) {
		Question question = new Question();
		question.setType(type);
		return questionDao.getCount(question);
	}
}
