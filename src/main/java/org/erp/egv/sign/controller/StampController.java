package org.erp.egv.sign.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.sign.model.service.StampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sign/*")
public class StampController {
	
	private StampService stampService;
	
	@Autowired
	public StampController(StampService stampService) {
		this.stampService = stampService;
	}
	
	@GetMapping("/stamp")
	public ModelAndView selectStamp(ModelAndView mv, Principal principal) {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		
		/* 기존에 설정된 사원의 stamp정보 가져오기 */
		EmployeeDTO emp = stampService.selectEmpStampInfo(empCode);
		System.out.println("사원정보 : " + emp);
		
		String imgPath = "/stamp-img/" + emp.getStampUuidName();
		System.out.println(imgPath);

		String test = emp.getStampUuidName();
		mv.addObject("imgPath", imgPath);
		mv.addObject("emp", emp);
		mv.addObject("test", test);
		mv.setViewName("sign/stamp");

		return mv;
	}
	
	@PostMapping("/stamp")
	public ModelAndView singleFileUpload(HttpServletRequest request,
										 @RequestParam("stampImg") MultipartFile singleFile, 
										 ModelAndView mv,
										 RedirectAttributes rAttr,
										 Principal principal) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		
		String root = this.getClass().getResource("/").getPath();
	    String srcRoot = root.replace("/", "\\").substring(0, root.length() - 15).substring(1).concat("src\\main\\resources\\static\\");
//	    String targetRoot = root.replace("/", "\\").concat("static\\stamp-img\\");
	    
	    String filePath = srcRoot + "stamp-img/";
	    
		/* 폴더 생성 */
		File mkdir = new File(filePath);
		if (!mkdir.exists()) {
			System.out.println("폴더 생성 : " + mkdir.mkdir());
		}
		
		/* 파일명 변경처리 */
//		String originFileName = new String(singleFile.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
		String originFileName = new String(singleFile.getOriginalFilename());
		System.out.println("원본 이름 : " + originFileName);
		
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		
		String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
		System.out.println("변경한 이름 : " + saveName);

		/* 파일 저장 */
		try {
			singleFile.transferTo(new File(filePath + "\\" + saveName));
//			singleFile.transferTo(new File(targetRoot + "\\" + saveName));
			
			String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
			
			/* stamp 등록 */ 
			EmployeeDTO employee = new EmployeeDTO();
			employee.setCode(empCode); 
			employee.setStampOrigName(originFileName);
			employee.setStampUuidName(saveName);
			employee.setStampImgPath(filePath);
			
			stampService.setStamp(employee);
			
			/* stamp 등록된 회원 정보 조회 */
			EmployeeDTO empStampinfo = stampService.selectEmpStampInfo(empCode);
			System.out.println("stamp 등록한 사원정보 : " + empStampinfo);
			
//			String imgPath = "/stamp-img/" + empStampinfo.getStampUuidName();
//			System.out.println("imgNewPath : " + imgPath);
			
//			mv.addObject("imgPath", imgPath);
			String test = empStampinfo.getStampUuidName();
			rAttr.addFlashAttribute("message", "파일 업로드 성공!!!");
			
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			
			/* 실패 시 업로드 된 파일 삭제 */
			new File(filePath + "\\" + saveName).delete();
			rAttr.addFlashAttribute("message", "파일 업로드 실패!!");
		}
		mv.setViewName("redirect:/main");
		
		return mv;
	}
}
