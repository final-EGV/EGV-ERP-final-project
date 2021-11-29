package org.erp.egv.sign.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sign/*")
public class SignInsertController {
	
private SignInsertService signInsertService;
private EmpInfoService empInfoService;
	
	@Autowired
	public SignInsertController(SignInsertService signInsertService, EmpInfoService empInfoService) {
		this.signInsertService = signInsertService;
		this.empInfoService = empInfoService;
	}
	
	@GetMapping("template")
	public ModelAndView temp(ModelAndView mv) {
		System.out.println("test");
		List<TemplateDTO> tempList = signInsertService.selectTempList();
//		System.out.println(tempList);
		
		mv.addObject("tempList", tempList);
		mv.setViewName("/sign/template");
		return mv;
	}
	
	@GetMapping(value="signTemplate", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<TemplateDTO> selectTempList(){

		return signInsertService.selectTempList();
	}

	@GetMapping("/signInsert")
	public ModelAndView signInsert(ModelAndView mv, @RequestParam int code) {
		
		TemplateDTO templateDTO = signInsertService.findTemplateByCode(code);
//		System.out.println(templateDTO);
		
//		final String unescapedText = HtmlEscape.unescapeHtml(tempContent); 
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		mv.addObject("empList", empList);
		mv.addObject("template", templateDTO);
		mv.setViewName("/sign/signInsert");
		
		return mv;
	}
	
	@PostMapping("/signInsert")
	public ModelAndView chooseApprover(ModelAndView mv, HttpServletRequest request, RedirectAttributes rAttr, Principal principal) throws ParseException {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		
		int tempCode = Integer.valueOf(request.getParameter("template"));
		String dateString = request.getParameter("writeDate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = format.parse(dateString);
		java.sql.Date date = new java.sql.Date(utilDate.getTime());
		String status = request.getParameter("signStatus");
		String title = request.getParameter("documentTitle");
		String contents = request.getParameter("documentContent");
		
		String[] approverList = request.getParameterValues("approverInput");
		String[] referrerList = request.getParameterValues("referrerInput");
		
		/* 기안서 insert */
		TemplateDTO templateDTO = signInsertService.findTemplateByCode(tempCode);
		EmployeeDTO employeeDTO = empInfoService.empOneRequest(empCode);
		int signCode = signInsertService.findMaxSignCode() + 1;
		
		SignDTO newSignDTO = new SignDTO();
		newSignDTO.setCode(signCode);
		newSignDTO.setTemp(templateDTO);
		newSignDTO.setEmployee(employeeDTO);
		newSignDTO.setDate(date);
		newSignDTO.setStatus(status);
		newSignDTO.setTitle(title);
		newSignDTO.setContents(contents);
		
		signInsertService.insertSign(newSignDTO);
		
		/* 결재자 insert */
		int approverPriority = 1;
		for (String approver : approverList) {
			int approverCode = signInsertService.findMaxApproverCode() + 1;
			
			if (approver.length() > 5) {
				String approverEmpCode = approver.substring(4,11);
				EmployeeDTO approverEmployeeDTO = empInfoService.empOneRequest(approverEmpCode);
				
				ApproverDTO approverDTO = new ApproverDTO();
				approverDTO.setCode(approverCode);
				approverDTO.setEmp(approverEmployeeDTO);				
				approverDTO.setSign(newSignDTO);
				approverDTO.setOrder(approverPriority);
				approverDTO.setStatus("대기");
				
				System.out.println(approverDTO);
				
				signInsertService.insertApprover(approverDTO);
				
				approverPriority++;
				approverCode++;
			}
		}
		
		/* 참조자 insert */
		for(String referrer : referrerList) {
			int referrerCode = signInsertService.findMaxReferrerCode() + 1;

			if (referrer.length() > 5) {
				String referrerEmpCode = referrer.substring(4,11);
				EmployeeDTO referrerEmployeeDTO = empInfoService.empOneRequest(referrerEmpCode);
				
				RefferrerDTO refferrerDTO = new RefferrerDTO();
				refferrerDTO.setCode(referrerCode);
				refferrerDTO.setEmp(referrerEmployeeDTO);
				refferrerDTO.setSign(newSignDTO);
				refferrerDTO.setReadYN("N");
				System.out.println(refferrerDTO);

				signInsertService.insertReferrer(refferrerDTO);
				
				referrerCode++;
			}
		}

		mv.setViewName("redirect:/main");
		return mv;
	}

}
