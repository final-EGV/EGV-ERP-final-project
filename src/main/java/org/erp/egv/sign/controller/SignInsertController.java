package org.erp.egv.sign.controller;

import java.util.List;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.erp.egv.sign.model.service.SignInsertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		System.out.println(tempList);
		
		mv.addObject("tempList", tempList);
		mv.setViewName("/sign/template");
		return mv;
	}
	
	@GetMapping(value="signTemplate", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<TemplateDTO> selectTempList(){

		return signInsertService.selectTempList();
	}

	@GetMapping("/signInsert/{code}")
	public ModelAndView signInsert(ModelAndView mv, @PathVariable int code) {
		
		TemplateDTO templateDTO = signInsertService.findTemplateByCode(code);
//		System.out.println(templateDTO);
		
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		
		mv.addObject("empList", empList);
		mv.addObject("template", templateDTO);
		mv.setViewName("/sign/signInsert");
		
		return mv;
	}

}
