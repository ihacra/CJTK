package com.hacra.cjtk.modules.question.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hacra.cjtk.commons.base.BaseController;
import com.hacra.cjtk.commons.util.excel.ExportExcel;
import com.hacra.cjtk.commons.util.excel.ImportExcel;
import com.hacra.cjtk.modules.question.entity.Question;
import com.hacra.cjtk.modules.question.service.QuestionService;
import com.hacra.cjtk.modules.question.utils.QuestionUtils;

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
	public Question get(@RequestParam(required = false) String id, HttpServletRequest request) {
		return questionService.get(id, request);
	}
	
	/**
	 * 添加页面
	 * @return
	 */
	@RequestMapping({"form", ""})
	public String add() {
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
	public String save(Question question, String path, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		questionService.save(question, request);
		if ("zswd".equals(path)) {
			addMessage(redirectAttributes, "会计题目修改成功!");
			return "redirect:/zswd/?id=" + question.getId();
		} else if ("qbtk".equals(path)) {
			addMessage(redirectAttributes, "会计题目修改成功!");
			return "redirect:/qbtk/view?id=" + question.getId();
		} else {
			addMessage(redirectAttributes, "会计题目添加成功!");
			return "redirect:/zswd/?id=" + question.getId();
		}
	}
	
	/**
	 * 删除方法
	 * @param question
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(Question question, RedirectAttributes redirectAttributes) {
		questionService.delete(question);
		addMessage(redirectAttributes, "会计题目删除成功!");
		return "redirect:/qbtk/";
	}
	
	/**
	 * 导入Excel
	 * @param multipartFile
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("importExcel")
	public String importExcel(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		try {
			ImportExcel importExcel = new ImportExcel(multipartFile, 0, 1);
			List<Question> list = importExcel.getDataList(Question.class, 1);
			for (Question question : list) {
				question.setType("0");
				questionService.save(question, request);
			}
			addMessage(redirectAttributes, "会计题目导入成功：共导入"+list.size()+"条！");
		} catch (Exception e) {
			addMessage(redirectAttributes, "会计题目导入失败!");
		}
		return "redirect:/zswd/";
	}
	
	/**
	 * 导出Excel
	 * @param question
	 * @return
	 */
	@RequestMapping("exportExcel")
	public String exportExcel(Question question, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		question.setType("0");
		List<Question> list = questionService.findList(question);
		ExportExcel exportExcel = new ExportExcel(QuestionUtils.getSubjectVal(request) + "（选择题）", Question.class, 1);
		exportExcel.setDataList(list).write(request, response, QuestionUtils.getSubjectVal(request)+".xlsx");
		return null;
	}
}
