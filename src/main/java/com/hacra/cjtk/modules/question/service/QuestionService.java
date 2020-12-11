package com.hacra.cjtk.modules.question.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hacra.cjtk.commons.base.BaseService;
import com.hacra.cjtk.commons.util.StringUtils;
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
	 * 获取问题
	 */
	@Override
	public Question get(String id) {
		Question question = null;
		if (StringUtils.isNotBlank(id)) {
			question = super.get(id);
		} 
		if (question == null) {
			question = new Question();
		}
		return question;
	}
	
	/**
	 * 获取ID列表
	 * @return
	 */
	public List<String> getIdList() {
		return dao.getIdList();
	}
	
	/**
	 * 保存方法
	 */
	@Override
	@Transactional(readOnly = false)
	public void save(Question entity) {
		if (StringUtils.isNotBlank(entity.getTitle())) {
			String regex = "(（）|\\(\\))";
			entity.setTitle(entity.getTitle().replaceAll(regex, "（ ）"));;
		}
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(dao.getNextId());
			entity.setCode("CJ" + StringUtils.formatLength(entity.getId(), 3));
			entity.setCreateDate(new Date());
			entity.setUpdateDate(entity.getCreateDate());
			dao.insert(entity);
		} else {
			entity.setUpdateDate(new Date());
			dao.update(entity);
		}
	}
}
