package com.kr.jabtang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.HttpHeaders;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public String home(Model model, UserDTO user) {	
		model.addAttribute("nickname", user.getNickname());
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
	
	//도현
	@GetMapping(value = "/uploadTest")
	public void uploadTest() {	
		System.out.println("upload form");
	}
	
	//도현
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
	
	//도현
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		logger.info("upload ajax");
	}
	
	//도현
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){
		List<AttachFileDTO> list = new ArrayList<>();		
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		
		// make folder ----
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		
		if(uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
				
		for (MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			String uploadFileName = multipartFile.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			//File saveFile = new File(uploadFolder, uploadFileName);
			//File saveFile = new File(uploadPath, uploadFileName);
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				//check image type file
				if (checkImageType(saveFile)) {
					attachDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));					
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				
				// add to List
				list.add(attachDTO);
				
			} catch (Exception e) {
				e.getMessage();
			}
		}	
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	//도현
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	//도현
	private boolean checkImageType(File file) {
		try{
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		}catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//도현
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName){
		logger.info(fileName);
		File file = new File("c:\\upload\\" + fileName);
		logger.info("file : " + file);
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
}

	
	

