package com.hacra.cjtk.modules.question.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hacra.cjtk.commons.base.BaseService;
import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.question.dao.QuestionDao;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.utils.QuestionUtils;

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
	public Question get(String id, HttpServletRequest request) {
		Question question = new Question(id);
		question.setSubject(QuestionUtils.getSubjectKey(request));
		if (StringUtils.isBlank(id)) {
			return question;
		}
		Question entity = super.get(question);
		return entity == null ? question : entity;
	}
	
	/**
	 * 获取对应题库数量
	 * @param question
	 * @return
	 */
	public int getCount(Question question) {
		return dao.getCount(question);
	}
	
	/**
	 * 获取ID列表
	 * @return
	 */
	public List<String> getIdList(Question question) {
		return dao.getIdList(question);
	}
	
	/**
	 * 保存方法
	 */
	@Override
	@Deprecated
	public void save(Question entity) {
		super.save(entity);
	}
	
	/**
	 * 保存方法
	 */
	@Transactional(readOnly = false)
	public void save(Question entity, HttpServletRequest request) {
		formatData(entity);
		if (StringUtils.isBlank(entity.getId())) {
			entity.setId(dao.getNextId());
			entity.setSubject(QuestionUtils.getSubjectKey(request));
			entity.setCreateDate(new Date());
			entity.setUpdateDate(entity.getCreateDate());
			dao.insert(entity);
		} else {
			entity.setUpdateDate(new Date());
			dao.update(entity);
		}
	}
	
	/**
	 * 格式化数据
	 * @param question
	 */
	private void formatData(Question question) {
		question.setTitle(StringUtils.trimToEmpty(question.getTitle()));
		question.setOptionA(StringUtils.trimToEmpty(question.getOptionA()));
		question.setOptionB(StringUtils.trimToEmpty(question.getOptionB()));
		question.setOptionC(StringUtils.trimToEmpty(question.getOptionC()));
		question.setOptionD(StringUtils.trimToEmpty(question.getOptionD()));
		question.setAnswer(StringUtils.trimToEmpty(question.getAnswer()));
		question.setAnalysis(StringUtils.trimToEmpty(question.getAnalysis()));
		question.setLabel(StringUtils.trimToEmpty(question.getLabel()));
		if (StringUtils.isNotBlank(question.getTitle())) {
			String regex = "(（）|\\(\\))";
			question.setTitle(question.getTitle().replaceAll(regex, "（ ）"));
		}
		question.setAnalysis(StringUtils.defaultIfBlank(question.getAnalysis(), "无"));
		question.setLabel(StringUtils.defaultIfBlank(question.getLabel(), "无"));
		question.setType(StringUtils.defaultIfBlank(question.getType(), "0"));
	}
}
