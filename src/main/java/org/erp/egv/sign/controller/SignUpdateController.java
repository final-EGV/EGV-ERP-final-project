package org.erp.egv.sign.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.erp.egv.sign.model.service.SignInsertService;
import org.erp.egv.sign.model.service.SignUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sign/*")
public class SignUpdateController {
	
	private SignUpdateService signUpdateService;
	private EmpInfoService empInfoService;
	private SignInsertService signInsertService;

	@Autowired
	public SignUpdateController(SignUpdateService signUpdateService, EmpInfoService empInfoService, SignInsertService signInsertService) {
		this.signUpdateService = signUpdateService;
		this.empInfoService = empInfoService;
		this.signInsertService = signInsertService;
	}

	@GetMapping("detail/signUpdate")
	public ModelAndView selectSignSaved(ModelAndView mv, @RequestParam("code") String code, Principal principal) {

		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		
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
		int approverListSize = approverList.size();
		
		/* 참조자 정보 조회 */
		List<RefferrerDTO> refferrerList = signUpdateService.selectSignRefferrerList(signCode);
//		System.out.println(refferrerListSize);

		mv.addObject("empCode", empCode);
		mv.addObject("empList", empList);
		mv.addObject("template", templateDTO);
		mv.addObject("sign", savedSignDTO);
		mv.addObject("approverList", approverList);
		mv.addObject("approverListSize", approverListSize);
		mv.addObject("refferrerList", refferrerList);
		
		mv.setViewName("/sign/detail/signUpdate");
		
		return mv;
	}
	
	@PostMapping("detail/signUpdate")
	public ModelAndView updateSignSaved(ModelAndView mv, HttpServletRequest request, RedirectAttributes rAttr, Principal principal) {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		
		int signCode = Integer.valueOf(request.getParameter("signCode"));
		int tempCode = Integer.valueOf(request.getParameter("template"));
		String status = request.getParameter("signStatus");
//		System.out.println("-------status : " + status);
		String title = request.getParameter("documentTitle");
		String contents = request.getParameter("documentContent");
		
		java.util.Date today = new java.util.Date();		
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		String[] approverList = request.getParameterValues("approverInput");
		String[] referrerList = request.getParameterValues("referrerInput");
		
		String deleteYn = request.getParameter("deleteDocumentYn");
//		System.out.println("-------deleteYn : " + deleteYn);

		if ("N".equals(deleteYn)) {	
			
			TemplateDTO templateDTO = signInsertService.findTemplateByCode(tempCode);
			EmployeeDTO employeeDTO = empInfoService.empOneRequest(empCode);
			
			SignDTO updateSign = new SignDTO();
			updateSign.setCode(signCode);
			updateSign.setTemp(templateDTO);
			updateSign.setEmployee(employeeDTO);
			updateSign.setDate(date);
			updateSign.setStatus(status);
			updateSign.setTitle(title);
			updateSign.setContents(contents);
			
			/* 기안서 update */
			signUpdateService.updateSign(updateSign);
			
			/* 기존 결재자, 참조자 기존 정보 delete */
			signUpdateService.deleteSignApprover(signCode);
			signUpdateService.deleteSignRefferrer(signCode);
			
			/* 결재자, 참조자 new 정보 insert */
			/* 결재자 insert */
			int approverPriority = 1;
			for (String approver : approverList) {
				int approverCode = signInsertService.findMaxApproverCode() + 1;
				
				String approverEmpCode = approver.substring(4,11);
				EmployeeDTO approverEmployeeDTO = empInfoService.empOneRequest(approverEmpCode);
				
				ApproverDTO approverDTO = new ApproverDTO();
				approverDTO.setCode(approverCode);
				approverDTO.setEmp(approverEmployeeDTO);				
				approverDTO.setSign(updateSign);
				approverDTO.setOrder(approverPriority);
				approverDTO.setStatus("대기");
				
	//			System.out.println(approverDTO);
				
				signInsertService.insertApprover(approverDTO);
				
				approverPriority++;
				approverCode++;
			}
			
			if (referrerList != null) {
				/* 참조자 insert */
				for(String referrer : referrerList) {
					int referrerCode = signInsertService.findMaxReferrerCode() + 1;
		
					String referrerEmpCode = referrer.substring(4,11);
					EmployeeDTO referrerEmployeeDTO = empInfoService.empOneRequest(referrerEmpCode);
					
					RefferrerDTO refferrerDTO = new RefferrerDTO();
					refferrerDTO.setCode(referrerCode);
					refferrerDTO.setEmp(referrerEmployeeDTO);
					refferrerDTO.setSign(updateSign);
					refferrerDTO.setReadYN("N");
					
		//			System.out.println(refferrerDTO);
		
					signInsertService.insertReferrer(refferrerDTO);
					
					referrerCode++;
				}
			}
			
		} else {
			
			/* 기존 결재자, 참조자 기존 정보 delete */
			signUpdateService.deleteSignApprover(signCode);
			signUpdateService.deleteSignRefferrer(signCode);
			
			/* 기안서 정보 delete */
			signUpdateService.deleteSign(signCode);
		}

		
		mv.setViewName("redirect:/sign/sent/savesign");
		return mv;
	}
	
	@GetMapping("detail/resign")
	public ModelAndView reSign(ModelAndView mv, @RequestParam("code") String code, Principal principal) {
		
		// 선택한 반려 기안서 코드
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
		int approverListSize = approverList.size();
		
		/* 참조자 정보 조회 */
		List<RefferrerDTO> refferrerList = signUpdateService.selectSignRefferrerList(signCode);
//		System.out.println(refferrerListSize);

		mv.addObject("empList", empList);
		mv.addObject("template", templateDTO);
		mv.addObject("sign", savedSignDTO);
		mv.addObject("approverList", approverList);
		mv.addObject("approverListSize", approverListSize);
		mv.addObject("refferrerList", refferrerList);
		mv.addObject("status", "resign");
		
		mv.setViewName("/sign/detail/resign");
		
		return mv;
	}

}
