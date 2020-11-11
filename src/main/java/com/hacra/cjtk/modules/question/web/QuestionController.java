package com.hacra.cjtk.modules.question.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller
 * 
 * @author Hacra
 * @date 2020-11-11
 */
@Controller
@RequestMapping("question")
public class QuestionController {

	@RequestMapping({"add", ""})
	public String add() {
		return "modules/question/questionForm";
	}
}
