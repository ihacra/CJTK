package com.hacra.cjtk.commons.base;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * BaseController
 * 
 * @author Hacra
 * @version 2020/11/16
 */
public class BaseController {
	
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
