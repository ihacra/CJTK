package com.hacra.cjtk.modules.question.utils;

import com.hacra.cjtk.commons.cache.CacheManager;
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

	public static final String QU_COUNT_XZT_NAME = "QU_COUNT_0";		// 选择题问题数量缓存名称
	public static final String QU_COUNT_TKT_NAME = "QU_COUNT_1";		// 填空题问题数量缓存名称
	private static QuestionDao questionDao = SpringContextUtils.getBean(QuestionDao.class);
	
	/**
	 * 获取当前选择题题库数量
	 * @param type 问题类型
	 * @return
	 */
	public static int getCountOfXZTQuestions() {
		CacheManager cacheManager = CacheManager.getInstance();
		if (cacheManager.getCache(QU_COUNT_XZT_NAME) == null) {
			Question question = new Question();
			question.setType("0");
			int val = questionDao.getCount(question);
			cacheManager.addCache(QU_COUNT_XZT_NAME, val);
			return val;
		}
		return (int) cacheManager.getCache(QU_COUNT_XZT_NAME);
	}
	
	/**
	 * 获取当前填空题题库数量
	 * @param type 问题类型
	 * @return
	 */
	public static int getCountOfTKTQuestions() {
		CacheManager cacheManager = CacheManager.getInstance();
		if (cacheManager.getCache(QU_COUNT_TKT_NAME) == null) {
			Question question = new Question();
			question.setType("1");
			int val = questionDao.getCount(question);
			cacheManager.addCache(QU_COUNT_TKT_NAME, val);
			return val;
		}
		return (int) cacheManager.getCache(QU_COUNT_TKT_NAME);
	}
}
