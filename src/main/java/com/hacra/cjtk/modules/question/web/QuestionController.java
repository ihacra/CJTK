package com.hacra.cjtk.modules.question.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.service.QuestionService;

/**
 * controller
 * 
 * @author Hacra
 * @date 2020-11-11
 */
@Controller
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@ModelAttribute
	public Question get(@RequestParam(required = false) String id) {
		Question question = null;
		if (StringUtils.isNotBlank(id)) {
			question = questionService.get(id);
		} 
		if (question == null) {
			question = new Question();
		}
		return question;
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping({"add", ""})
	public String add() {
		return "modules/question/questionForm";
	}
	
	/**
	 * 保存方法
	 * @param question
	 * @return
	 */
	@RequestMapping("save")
	public String save(Question question) {
		return "redirect:/index/";
	}
}
