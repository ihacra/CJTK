package com.hacra.cjtk.modules.zswd.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	 * 随机获取问题ID
	 * 获取全部ID并随机打乱顺序
	 * @return
	 */
	public List<String> randomQuestionIdList() {
		List<String> idList = questionService.getIdList();
		Random random = new Random();
		for (int i = 0; i<<1 < idList.size(); i++) {
			int j = random.nextInt(idList.size());
			String tempId = idList.get(i);
			idList.set(i, idList.get(j));
			idList.set(j, tempId);
		}
		return idList;
	}
}
