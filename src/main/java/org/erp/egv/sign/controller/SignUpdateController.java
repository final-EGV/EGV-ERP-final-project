package org.erp.egv.sign.controller;

import java.security.Principal;
import java.util.List;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.erp.egv.sign.model.service.SignInsertService;
import org.erp.egv.sign.model.service.SignUpdateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign/*")
public class SignUpdateController {
	
	private SignUpdateService signUpdateService;
	private EmpInfoService empInfoService;
	private SignInsertService signInsertService;

	public SignUpdateController(SignUpdateService signUpdateService, EmpInfoService empInfoService, SignInsertService signInsertService) {
		this.signUpdateService = signUpdateService;
		this.empInfoService = empInfoService;
		this.signInsertService = signInsertService;
	}

	@GetMapping("detail/signUpdate")
	public ModelAndView selectSignSaved(ModelAndView mv, @RequestParam("code") String code, Principal principal) {

		// 선택한 임시저장 기안서 코드
		int signCode = Integer.valueOf(code);
		
		/* (모달창에서 사용) 사원정보 조회 */
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		/* 선택한 기안서 정보 조회*/
		SignDTO savedSignDTO = signUpdateService.selectSavedSign(signCode);
		
		/* 템플릿 정보 조회 */
		int tempCode = 1;
		TemplateDTO templateDTO = signInsertService.findTemplateByCode(tempCode);
		
		/* 참조자 정보 조회 */
		
		
		/* 결재자 정보 조회 */
		
		
		mv.addObject("empList", empList);
		mv.addObject("template", templateDTO);
		
		mv.setViewName("/sign/detail/signUpdate");
		
		return mv;
	}
}
