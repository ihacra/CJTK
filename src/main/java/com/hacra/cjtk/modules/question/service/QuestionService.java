package com.hacra.cjtk.modules.question.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hacra.cjtk.commons.base.BaseService;
import com.hacra.cjtk.modules.question.dao.QuestionDao;
import com.hacra.cjtk.modules.question.entity.Question;

/**
 * service
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Service
public class QuestionService extends BaseService<QuestionDao, Question> {

	/**
	 * 获取ID列表
	 * @return
	 */
	public List<String> getIdList() {
		return dao.getIdList();
	}
}
