package com.hacra.cjtk.modules.zswd.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.sys.entity.Question;
import com.hacra.cjtk.modules.sys.service.QuestionService;
import com.hacra.cjtk.modules.zswd.service.ZswdService;

/**
 * 知识问答
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Controller
@RequestMapping("zswd")
public class ZswdController {

	@Autowired
	private ZswdService zswdService;
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
	 * 知识问答
	 * @return
	 */
	@RequestMapping("view")
	public String view(Question question, Model model) {
		model.addAttribute("question", question);
		return "modules/zswd/view";
	}
}
