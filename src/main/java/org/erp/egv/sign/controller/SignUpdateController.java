package org.erp.egv.sign.controller;

import java.security.Principal;
import java.util.List;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.erp.egv.sign.model.service.SignUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	public SignUpdateController(SignUpdateService signUpdateService, EmpInfoService empInfoService) {
		this.signUpdateService = signUpdateService;
		this.empInfoService = empInfoService;
	}

	@GetMapping("detail/signUpdate")
	public ModelAndView selectSignSaved(ModelAndView mv, @RequestParam("code") String code, Principal principal) {

		// 선택한 임시저장 기안서 코드
		int signCode = Integer.valueOf(code);
//		System.out.println(code);
		
		/* (모달창에서 사용) 사원정보 조회 */
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		/* 선택한 기안서 정보 조회*/
		SignDTO savedSignDTO = signUpdateService.selectSavedSign(signCode);
//		System.out.println(savedSignDTO);
		
		/* 템플릿 정보 조회 */
		TemplateDTO templateDTO = savedSignDTO.getTemp();
//		System.out.println(templateDTO);
		
		/* 결재자 정보 조회 */
		List<ApproverDTO> approverList = signUpdateService.selectSignApproverList(signCode);
		for(ApproverDTO x : approverList) {
			System.out.println("eeeeeeeeeeeeeeee" + x);
		}
		//int approverListSize = approverList.size();
		int approverListSize = approverList.size();
		System.out.println("approverListSize : " + approverListSize);
		
		/* 참조자 정보 조회 */
		List<RefferrerDTO> refferrerList = signUpdateService.selectSignRefferrerList(signCode);
		int refferrerListSize = refferrerList.size();
//		System.out.println(refferrerListSize);

		mv.addObject("empList", empList);
		mv.addObject("template", templateDTO);
		mv.addObject("sign", savedSignDTO);
		mv.addObject("approverList", approverList);
		mv.addObject("approverListSize", approverListSize);
		mv.addObject("refferrerList", refferrerList);
		
		mv.setViewName("/sign/detail/signUpdate");
		
		return mv;
	}
	

}
