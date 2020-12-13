package com.hacra.cjtk.commons.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hacra.cjtk.commons.util.StringUtils;
import com.hacra.cjtk.modules.question.utils.QuestionUtils;

/**
 * BaseController
 * 
 * @author Hacra
 * @version 2020/11/16
 */
public class BaseController {
	
	/**
	 * 科目缓存是否为空
	 * @param request
	 * @return
	 */
	public boolean verification(HttpServletRequest request) {
		return StringUtils.isBlank(QuestionUtils.getSubjectVal(request));
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String message) {
		model.addAttribute("message", message);
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String message) {
		redirectAttributes.addFlashAttribute("message", message);
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addTitleAttribute(Model model, HttpServletRequest request) {
		model.addAttribute("title", "题库("+QuestionUtils.getSubjectVal(request)+")");
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addTitleAttribute(RedirectAttributes redirectAttributes, HttpServletRequest request) {
		redirectAttributes.addFlashAttribute("title", "题库("+QuestionUtils.getSubjectVal(request)+")");
	}
	
	/**
	 * 添加Model消息
	 * @param key
	 * @param val
	 */
	protected void addAttribute(Model model, String key, Object val) {
		model.addAttribute(key, val);
	}
	
	/**
	 * 添加Flash消息
	 * @param key
	 * @param val
	 */
	protected void addAttribute(RedirectAttributes redirectAttributes, String key, Object val) {
		redirectAttributes.addFlashAttribute(key, val);
	}
}
