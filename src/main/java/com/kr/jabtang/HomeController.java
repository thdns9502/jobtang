package com.kr.jabtang;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;



@Controller
@Log4j
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
	
	@GetMapping(value = "/uploadTest")
	public void uploadTest() {	
		System.out.println("upload form");
	}
	
	@PostMapping("/uploadAction")
	public void uploadAction( MultipartFile[] uploadFile, Model model ) {
	
		String uploadFolder = "C:\\upload";
		
		for( MultipartFile multipartFile : uploadFile ) {
			System.out.println("-----------------------------------");
			System.out.println("Upload File Name : " + multipartFile.getOriginalFilename());
			System.out.println("Upload File Size : " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch (Exception e) {
				System.out.println("/uploadAction 오류 :" + e.getMessage());
			}
		}
	}
	
	
}

	
	

