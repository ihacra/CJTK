package com.hacra.cjtk.modules.index.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hacra.cjtk.commons.base.BaseController;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.service.QuestionService;
import com.hacra.cjtk.modules.question.utils.QuestionUtils;

/**
 * IndexController
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Controller
@RequestMapping({"index", ""})
public class IndexController extends BaseController {

	@Autowired
	private QuestionService questionService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("")
	public String index(Question question, Model model) {
		Map<String, String> map = QuestionUtils.getSubjectMap();
		List<Integer> list = new ArrayList<Integer>();
		for (String key : map.keySet()) {
			question.setSubject(key);
			question.setType("0");
			list.add(questionService.getCount(question));
			question.setType("1");
			list.add(questionService.getCount(question));
			question.setType("2");
			list.add(questionService.getCount(question));
		}
		addAttribute(model, "subjectMap", map);
		addAttribute(model, "subjectList", list);
		return "modules/index/index";
	}
	
	/**
	 * 重定向至zswd
	 * 并存储选择的科目
	 * @param subject
	 * @param response
	 * @return
	 */
	@RequestMapping("forward")
	public String forward(String subject, HttpServletResponse response) {
		QuestionUtils.setSubjectKey(response, subject);
		QuestionUtils.setSubjectVal(response, QuestionUtils.getSubjectMap().get(subject));
		return "redirect:/zswd/";
	}
}
