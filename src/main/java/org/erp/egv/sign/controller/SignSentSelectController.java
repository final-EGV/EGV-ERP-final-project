package org.erp.egv.sign.controller;

import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.service.SignSentSelectService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign/sent/*")
public class SignSentSelectController {
	
	SignSentSelectService signService;
	
	public SignSentSelectController(SignSentSelectService signService) {
		this.signService = signService;
	}
	
	@GetMapping("completedsign")
	public ModelAndView selectcompletedSignList(ModelAndView mv, Principal principal) {
		List<SignDTO> signList = signService.selectcompletedSignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/sent/completedsign");
		return mv;
	}
	
	@GetMapping("progresssign")
	public ModelAndView selectProgresssignSignList(ModelAndView mv, Principal principal) {
		List<SignDTO> signList = signService.selectProgresssignSignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/sent/progresssign");
		return mv;
	}
	
	@GetMapping("rejectsign")
	public ModelAndView selectRejectsignSignList(ModelAndView mv, Principal principal) {
		List<SignDTO> signList = signService.selectRejectsignSignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/sent/rejectsign");
		return mv;
	}
	
	@GetMapping("savesign")
	public ModelAndView selectSaveSignList(ModelAndView mv, Principal principal) {
		List<SignDTO> signList = signService.selectSaveSignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("/sign/sent/savesign");
		return mv;
	}
}
