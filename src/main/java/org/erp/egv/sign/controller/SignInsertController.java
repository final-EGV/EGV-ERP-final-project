package org.erp.egv.sign.controller;

import java.util.List;

import org.erp.egv.sign.model.dto.TemplateDTO;
import org.erp.egv.sign.model.service.SignInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sign/*")
public class SignInsertController {
	
private SignInsertService signService;
	
	@Autowired
	public SignInsertController(SignInsertService signService) {
		this.signService = signService;
	}
	
	@GetMapping("template")
	public ModelAndView temp(ModelAndView mv) {
		System.out.println("test");
		List<TemplateDTO> tempList = signService.selectTempList();
		
		mv.addObject("tempList", tempList);
		mv.setViewName("/sign/template");
		return mv;
	}
	
	@GetMapping("/signInsert")
	public void signInsert() {}

}
