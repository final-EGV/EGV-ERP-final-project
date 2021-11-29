package org.erp.egv.sign.controller;

import java.security.Principal;

import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.service.SignDetailService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign/*")
public class SignDetailController {
	
	private SignDetailService signService;
	
	public SignDetailController(SignDetailService signService) {
		this.signService = signService;
	}
	
	@GetMapping("detail/detail")
	public ModelAndView selectSignDetail(ModelAndView mv, @RequestParam("code") String code, Principal principal) {
		SignDTO sign = signService.selectSign(Integer.valueOf(code));
		
		for (ApproverDTO app : sign.getApprover()) {
			if(app.getEmp().getCode().equals(((UserImpl)((Authentication)principal).getPrincipal()).getCode())) {
				System.out.println(app.getStatus());
				mv.addObject("status", app.getStatus());
				break;
			}
		}
		
		if(sign.getStatus().equals("반려")) {
			if(sign.getEmployee().getCode().equals(((UserImpl)((Authentication)principal).getPrincipal()).getCode())){
				mv.addObject("status", sign.getStatus());
			}
		}
		
		mv.addObject("sign", sign);
		mv.setViewName("/sign/detail/detail");
		
		return mv;
	}
	
	@GetMapping("detail/progress")
	public ModelAndView selectProgressSignDetail(ModelAndView mv, @RequestParam("code") String code) {
		SignDTO sign = signService.selectSign(Integer.valueOf(code));
		String status = null;
		
		for (ApproverDTO app : sign.getApprover()) {
			if(app.getStatus().equals("대기")) {
				status = "기안";
			} else {
				status = null;
			}
		}
		
		mv.addObject("status", status);
		mv.addObject("sign", sign);
		mv.setViewName("/sign/detail/detail");
		
		return mv;
	}
	
	@GetMapping("detail/cancle")
	public String cancleSign(@RequestParam("code") String code) {
		signService.cancleSign(Integer.valueOf(code));
		
		return "redirect:/sign/sent/savesign";
	}
	
	@GetMapping("detail/approval")
	public String approvalSign(@RequestParam("code") String code, Principal principal) {
		signService.approvalSign(Integer.valueOf(code), ((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		return "redirect:/sign/received/watingsign";
	}
	
	@GetMapping("detail/return")
	public String returnSign(@RequestParam("code") String code, Principal principal) {
		signService.returnSign(Integer.valueOf(code), ((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		return "redirect:/sign/received/watingsign";
	}
	
}
