package org.erp.egv.sign.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.sign.model.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/sign/*")
public class StampController {
	
	private StampService stampService;
	
	@Autowired
	public StampController(StampService stampService) {
		this.stampService = stampService;
	}
	
	@GetMapping("/stamp")
	public ModelAndView selectStamp(ModelAndView mv) {
		
		//(수정하기)test용 사원코드
		String empCode = "2021100";
		
		/* 기존에 설정된 사원의 stamp정보 가져오기 */
		EmployeeDTO emp = stampService.selectEmpStampInfo(empCode);
		System.out.println("사원정보 : " + emp);
				
		String imgPath = "/stamp-img/" + emp.getStampUuidName();
		System.out.println(imgPath);

		mv.addObject("imgPath", imgPath);
		mv.setViewName("sign/stamp");

		return mv;
	}
	
	@PostMapping(value = "/stamp")
	public ModelAndView singleFileUpload(HttpServletRequest request, HttpServletResponse response,
										 @RequestParam("stampImg") MultipartFile singleFile, 
										 ModelAndView mv) throws UnsupportedEncodingException {

		response.setContentType("application/json; charset=UTF-8");
		
		/* 파일을 저장할 경로 설정 */
//		String root = request.getSession().getServletContext().getRealPath("resources");
//		String root = "C:\\springBootwork\\EGV-ERP-final-project\\src\\main\\resources\\static\\";
//		System.out.println(root);
		
		String root = this.getClass().getResource("/").getPath();
	    String root2 = root.replace("/", "\\").substring(0, root.length() - 15).substring(1).concat("src\\main\\resources\\static\\");
	    
	    String filePath = root2 + "stamp-img/";
	    System.out.println(filePath);
		
		
		/* 폴더 생성 */
		File mkdir = new File(filePath);
		if (!mkdir.exists()) {
			System.out.println("폴더 생성 : " + mkdir.mkdir());
		}
		
		/* 파일명 변경처리 */
		String originFileName = new String(singleFile.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
		System.out.println("원본 이름 : " + originFileName);
		
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("변경한 이름 : " + saveName);
		
		/* 파일 저장 */
		try {
			singleFile.transferTo(new File(filePath + "\\" + saveName));
			
			//(수정하기)test용 사원코드
			String empCode = "2021100";
			
			/* stamp 등록 */
			EmployeeDTO employee = new EmployeeDTO();
			employee.setCode(empCode); 
			employee.setStampImgPath(filePath);
			employee.setStampOrigName(originFileName);
			employee.setStampUuidName(saveName);
			
			stampService.setStamp(employee);
			
			/* stamp 등록된 회원 정보 조회 */
			EmployeeDTO empStampinfo = stampService.selectEmpStampInfo(empCode);
			System.out.println("stamp 등록한 사원정보 : " + empStampinfo);
			
			String imgPath = "/stamp-img/" + empStampinfo.getStampUuidName();
			System.out.println("imgNewPath : " + imgPath);
			
			mv.addObject("message", "파일 업로드 성공!!!");
			mv.addObject("imgPath", imgPath);
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			/* 실패 시 업로드 된 파일 삭제 */
			new File(filePath + "\\" + saveName).delete();
			mv.addObject("message", "파일 업로드 실패!!");
		}
		mv.setViewName("sign/stamp");
		
		return mv;
	}
	
	@GetMapping("/template")
	public ModelAndView temp(ModelAndView mv) {
		
		String a = "템플릿 html";
		
		mv.addObject("test", a);
		mv.setViewName("/sign/template");
		return mv;
	}
	
}
