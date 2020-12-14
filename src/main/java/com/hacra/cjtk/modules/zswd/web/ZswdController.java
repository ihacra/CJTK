package com.hacra.cjtk.modules.zswd.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hacra.cjtk.commons.base.BaseController;
import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.service.QuestionService;
import com.hacra.cjtk.modules.zswd.service.ZswdService;

/**
 * 知识问答
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Controller
@RequestMapping("zswd")
public class ZswdController extends BaseController {

	@Autowired
	private ZswdService zswdService;
	@Autowired
	private QuestionService questionService;
	
	@ModelAttribute
	public Question get(@RequestParam(required = false) String id, HttpServletRequest request) {
		return questionService.get(id, request);
	}
	
	/**
	 * 知识问答
	 * @return
	 */
	@RequestMapping({"view", ""})
	public String view(Question question, Model model, HttpServletRequest request) {
		List<String> idList = zswdService.randomQuestionIdList(request);
		if (StringUtils.isBlank(question.getId()) && !idList.isEmpty()) {
			question = questionService.get(String.valueOf(idList.get(0)), request);
		}
		addAttribute(model, "question", question);
		addAttribute(model, "length", idList.size());
		return "modules/zswd/zswdView";
	}
	
	/**
	 * 返回对应ID的问题
	 * @param index
	 * @return
	 */
	@ResponseBody
	@RequestMapping("show")
	public Question show(int index, HttpServletRequest request) {
		Question question = null;
		if (index < 0 || index >= zswdService.getIdList().size()) {
			question = new Question();
		} else {
			question = questionService.get(String.valueOf(zswdService.getIdList().get(index)), request);
		}
		return question;
	}
}
