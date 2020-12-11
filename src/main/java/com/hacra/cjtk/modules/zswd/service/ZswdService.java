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

	private long lastUpdateTime = 0;	// 上次更新时间
	private List<String> idList;		// 随机ID数组
	@Autowired
	private QuestionService questionService;
	
	/**
	 * 随机获取问题ID
	 * 获取全部ID并随机打乱顺序
	 * @return
	 */
	public List<String> randomQuestionIdList() {
		long curTime = System.currentTimeMillis();
		if (idList != null && curTime - lastUpdateTime < 600000) {
			return idList;
		}
		idList = questionService.getIdList();
		Random random = new Random();
		int n = idList.size() / 2;
		for (int i = 0; i < n; i++) {
			int j = random.nextInt(idList.size());
			String tempId = idList.get(i);
			idList.set(i, idList.get(j));
			idList.set(j, tempId);
		}
		lastUpdateTime = System.currentTimeMillis();
		return idList;
	}
	
	/**
	 * 获取问题id列表
	 * @return
	 */
	public List<String> getIdList() {
		return idList;
	}
}
