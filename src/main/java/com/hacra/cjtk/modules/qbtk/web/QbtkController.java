package com.hacra.cjtk.modules.qbtk.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hacra.cjtk.commons.base.BaseController;
import com.hacra.cjtk.commons.base.Page;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.service.QuestionService;

/**
 * 全部题库
 * 
 * @author Hacra
 * @date 2020-12-07
 */
@Controller
@RequestMapping("qbtk")
public class QbtkController extends BaseController {

	@Autowired
	private QuestionService questionService;
	
	@ModelAttribute
	public Question get(@RequestParam(required = false) Integer id, HttpServletRequest request) {
		return questionService.get(id, request);
	}
	
	/**
	 * 问题列表
	 * @return
	 */
	@RequestMapping(value = {"list", ""})
	public String list(Question question, Model model, HttpServletRequest request) {
		Page<Question> page = questionService.findPage(new Page<Question>(request), question);
		addAttribute(model, "page", page);
		return "modules/qbtk/qbtkList";
	}
	
	/**
	 * 查看页面
	 * @param question
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "view")
	public String view(Question question, Model model) {
		addAttribute(model, "question", question);
		return "modules/qbtk/qbtkView";
	}
}
