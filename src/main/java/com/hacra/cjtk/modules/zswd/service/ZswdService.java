package com.hacra.cjtk.modules.zswd.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.service.QuestionService;

/**
 * 知识问答
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Service
public class ZswdService {


	@Autowired
	private QuestionService questionService;
	
	/**
	 * 随机获取问题
	 * @return
	 */
	public Question randomQuestion() {
		Question question = null;
		Random random = new Random();
		int count = 0;
		int maxId = StringUtils.toInt(questionService.getMaxId());
		do {
			count++;
			int randomId = random.nextInt(maxId-100) + 101;
			question = questionService.get(String.valueOf(randomId));
		} while (question == null && count < 3);
		return question;
	}
}
