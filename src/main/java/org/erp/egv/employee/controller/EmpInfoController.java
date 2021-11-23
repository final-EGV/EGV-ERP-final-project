package org.erp.egv.employee.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		
		System.out.println("콘트롤러 오나요?");
		
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		List emplists= new ArrayList<>();

		
		mv.addObject("empList", empList);
		mv.setViewName("emp/emplist");
		
		return mv;
	}
	
	@GetMapping("/login")
	public void empLogin() { }
	
	@GetMapping("/findid")
	public void findId() { }
	
	@PostMapping(value="findid", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String findId2(@RequestParam("name") String name,@RequestParam("birth") String birth,@RequestParam(
			"email") String email) {
		
		System.out.println(name);
		System.out.println(birth);
		System.out.println(email);
		
		String birtha = birth.substring(2, 4) + birth.substring(5, 7) + birth.substring(8, 10);
		
		Map<String, String> result = empInfoService.finId(name, birtha, email);
		
		Gson gson = new GsonBuilder().create();
		
		return gson.toJson(result);
		
	}
	
	@GetMapping("/pwreset")
	public void pwreset() { }
	
	
	@PostMapping(value="repw", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String resetPw(@RequestParam("code") String code, @RequestParam("name") String name,@RequestParam("birth") String birth,@RequestParam(
			"email") String email) {
		Map<String, String> result = null;
		
		String birtha = birth.substring(2, 4) + birth.substring(5, 7) + birth.substring(8, 10);
		
		EmployeeDTO emp = empInfoService.pwReset(code, name, birtha, email);
		
		if(emp != null) {
			String code2 = emp.getCode();
			String newpw = emp.getCode()+birtha;
			
			result = new HashMap<>();
			result.put("code", code2);
			result.put("newpw", newpw);
		}
		
		Gson gson = new GsonBuilder().create();
		
		return gson.toJson(result);
		
	}
	
	
}
