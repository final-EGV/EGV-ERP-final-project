package org.erp.egv.sign.controller;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.common.UUIDFileRenamePolicy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sign/*")
public class StampController {
	
	@GetMapping("/stamp")
	public void selectStamp() {}
	
	@PostMapping("/stamp")
	public ModelAndView insertStamp(ModelAndView mv, HttpServletRequest request) {
		
		if (ServletFileUpload.isMultipartContent(request)) {
//			System.out.println("파일을 업로드 하는 경우");
			
			String rootLocation = "src/main/resources/static";
			int maxFileSize = 1024 * 1024 * 10;
			String encodingType = "UTF-8";
			String fileUploadDirectory = rootLocation + "/stamp-img/";
			
//			System.out.println("파일 저장 root 경로 : " + rootLocation);
//			System.out.println("최대 업로드 파일 용량 : " + maxFileSize);
//			System.out.println("인코딩 방식 : " + encodingType);
//			System.out.println("파일 저장 root 경로 : " + fileUploadDirectory);
			
			File directory = new File(fileUploadDirectory);  

			if (!directory.exists()) {
				System.out.println("폴더 생성 : " + directory.mkdirs()); 
			}


		}
		
		mv.setViewName("/sign/stamp");
		return mv;
	}
}
