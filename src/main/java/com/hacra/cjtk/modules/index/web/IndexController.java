package com.hacra.cjtk.modules.index.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hacra.cjtk.commons.base.BaseController;

/**
 * IndexController
 * 
 * @author Hacra
 * @date 2020-11-10
 */
@Controller
@RequestMapping({"index", ""})
public class IndexController extends BaseController {

	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("")
	public String index() {
		return "redirect:/zswd/";
	}
}
