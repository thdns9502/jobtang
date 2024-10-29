package com.kr.jabtang;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String home(Model model, NaverUserDTO naver) {	
		model.addAttribute("nickname", naver.getNickname());
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {	
		return "login/login";
	}
	
	// 네이버 콜백
	@RequestMapping(value = "/naver", method = RequestMethod.GET)
	public String callback(Locale locale, Model model) {
		return "login/naverCallback";
	}
	
	
}

	
	

