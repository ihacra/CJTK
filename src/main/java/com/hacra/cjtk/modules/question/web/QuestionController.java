package com.hacra.cjtk.modules.question.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hacra.cjtk.commons.base.BaseController;
import com.hacra.cjtk.commons.util.excel.ImportExcel;
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
public class QuestionController extends BaseController {

	@Autowired
	private QuestionService questionService;
	
	@ModelAttribute
	public Question get(@RequestParam(required = false) String id) {
		return questionService.get(id);
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping({"form", ""})
	public String add(Model model) {
		return "modules/question/questionForm";
	}
	
	/**
	 * 修改页面
	 * @param question
	 * @param model
	 * @return
	 */
	@RequestMapping("modify")
	public String modify(Question question, String path, Model model) {
		addAttribute(model, "path", path);
		addAttribute(model, "question", question);
		return "modules/question/questionModify";
	}
	
	/**
	 * 保存方法
	 * @param question
	 * @return
	 */
	@RequestMapping("save")
	public String save(Question question, String path, RedirectAttributes redirectAttributes) {
		questionService.save(question);
		if ("zswd".equals(path)) {
			addMessage(redirectAttributes, "会计题目修改成功!");
			return "redirect:/zswd/?id=" + question.getId();
		} else if ("qbtk".equals(path)) {
			addMessage(redirectAttributes, "会计题目修改成功!");
			return "redirect:/qbtk/view?id=" + question.getId();
		} else {
			addMessage(redirectAttributes, "会计题目添加成功!");
			return "redirect:/question/";
		}
	}
	
	/**
	 * 导入Excel
	 * @param multipartFile
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("importExcel")
	public String importExcel(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) {
		try {
			ImportExcel importExcel = new ImportExcel(multipartFile);
			List<Question> list = importExcel.getDataList(Question.class, 1);
			for (Question question : list) {
				questionService.save(question);
			}
			addMessage(redirectAttributes, "会计题目导入成功：共导入"+list.size()+"条！");
		} catch (IOException e) {
			addMessage(redirectAttributes, "会计题目导入失败!");
		}
		return "redirect:/question/";
	}
}
