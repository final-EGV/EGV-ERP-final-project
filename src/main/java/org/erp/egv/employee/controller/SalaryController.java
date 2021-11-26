package org.erp.egv.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	/* Date : 2021/11/26
	 * Writer : Hansoo Lee
	 * 전체 재직 사원 통장조회 
	 * 
	 * 전체재직사원을 조회환뒤 통장정보만 빼오자.
	 * */
	@GetMapping("/bankBook")
	public ModelAndView empListRequest(ModelAndView mv) {
		
		System.out.println("콘트롤러 오나요?");
		
		List<EmployeeDTO> empList = empInfoService.empListRequest();
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/salary/salaryEmpBankBookList");
		
		return mv;
	}
	
	/* 통장정보조회 (위와 동일) */
	@GetMapping("/bankBookInfor")
	public ModelAndView empOneRequest(ModelAndView mv, @RequestParam String empCode) {
		System.out.println("콘트롤러 one 오나요?");
		
		EmployeeDTO empInfor = empInfoService.empOneRequest(empCode);
		
		mv.addObject("empInfor", empInfor);
		mv.setViewName("emp/salary/salaryEmpBankBookInfor");
		
		return mv;
	}
	
	/* Date : 2021/11/26
	 * Writer : Hansoo Lee
	 * 
	 * 통장 정보수정
	 * 이건 수정항목이 일반정보수정과 상이하므로 다시 작성하자.
	 * */
	@PostMapping("/modifyBankBookInfor")
	public ModelAndView empModifyInforRequest(ModelAndView mv, EmployeeDTO modifyInfor, @RequestParam int rankRegist, @RequestParam int deptRegist,  RedirectAttributes rttr) {
		System.out.println("콘트롤러 modify 오나요?");
		DepartmentDTO departmentDTO = new DepartmentDTO();
		EmpRankDTO empRankDTO = new EmpRankDTO();
		
		empRankDTO.setCode(rankRegist);
		departmentDTO.setCode(deptRegist);

		modifyInfor.setDept(departmentDTO);
		modifyInfor.setRank(empRankDTO);

		empInfoService.modifyInforRequest(modifyInfor);
		
		rttr.addFlashAttribute("registSuccessMessage", "사원 정보 수정 성공!!");
		mv.setViewName("redirect:/emp/salary/salaryEmpBankBookList");
		
		return mv;
	}
	
	@GetMapping("/login")
	public void memberLogin() { }
	
	
}
