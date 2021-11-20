package org.erp.egv.employee.controller;

import java.util.List;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp")
public class EmpInfoController {

	private EmpInfoService empInfoService;
	
	@Autowired
	public EmpInfoController(EmpInfoService empInfoService) {
		this.empInfoService = empInfoService;
	}
	
	@GetMapping("/empTestV1")
	public void emptest() {}
	
	@GetMapping("/list")
	public ModelAndView empListRequest(ModelAndView mv) {
		
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		for(EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/list");
		
		return mv;
	}
	
	
}
