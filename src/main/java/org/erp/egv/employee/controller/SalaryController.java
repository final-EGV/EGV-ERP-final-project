package org.erp.egv.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp/salary")
public class SalaryController {

	private EmpInfoService empInfoService;
	
	@Autowired
	public SalaryController(EmpInfoService empInfoService) {
		this.empInfoService = empInfoService;
	}
	
	@GetMapping("/salary")	
	public ModelAndView salaryRequest(ModelAndView mv) {
				
		List<EmployeeDTO> empList = empInfoService.salaryRequest();
		
		List emplists= new ArrayList<>();

		
		mv.addObject("empList", empList);
		mv.setViewName("emp/salary/salary");
		
		return mv;
	}
	
	@GetMapping("/severancePay")	
	public ModelAndView severancePayRequest(ModelAndView mv) {
				
		List<EmployeeDTO> empList = empInfoService.severancePayRequest();
		
		List emplists= new ArrayList<>();

		
		mv.addObject("empList", empList);
		mv.setViewName("emp/salary/severancePay");
		
		return mv;
	}
	
	@GetMapping("/login")
	public void memberLogin() { }
	
	
}
