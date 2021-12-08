package org.erp.egv.employee.controller;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.SalaryDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.erp.egv.work.model.dto.WorkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/salary/salary");
		
		return mv;
	}
	
	@PostMapping("/salary/search")
	public ModelAndView addNewDept(ModelAndView mv, SalaryDTO newSal, Locale locale) {
		
		empInfoService.addNewSal(newSal);
		mv.setViewName("redirect:/emp/salary");
		
		return mv;
	}
	
	/* Date : 2021/11/29
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 근무자 급여 상세 조회 컨트롤러
	 */
	@GetMapping("/salary/{code}")
	public ModelAndView findSalByCode(ModelAndView mv, @PathVariable("code") String code) {
		EmployeeDTO emp = empInfoService.findSalByCode(code);
		List<WorkDTO> worklist = empInfoService.findWorkByCode(code);
		int monthSalary = (emp.getRank().getSalary())/12;
		
		int hourPay = (((emp.getRank().getSalary())/12)/30)/8;
		
		int overWork = 0;
		for (WorkDTO work : worklist) {
			if(work.getWorkOver() != null) {
			overWork += work.getWorkOver();
			}
		}
		
		int totalPay = 0;
		if(overWork != 0) {
			totalPay = (monthSalary + (hourPay * overWork) * 2) * 10000;
		} else {
			totalPay = monthSalary * 10000;
		}
		
		String announce = monthSalary + "만원 + " + overWork + "시간 추가수당";
		
		mv.addObject("totalPay", totalPay);
		mv.addObject("work", worklist);
		mv.addObject("announce", announce);
		mv.addObject("salary", monthSalary);
		mv.addObject("empInfor", emp);
		mv.setViewName("emp/salary/salaryDetail");
		return mv;
	}
	
	/* Date : 2021/12/04
	 * Writer : JunWoo Kim
	 * 
	 * 사원 개인의 월급 확인용 컨트롤러
	 * (추가 근무를 한 경우가 있다면 수당이 지급되었는지 확인할수도 있다.)
	 */
	@GetMapping("/personal")
	public ModelAndView findSalPersonal(ModelAndView mv, Principal principal) {
		String code = (((UserImpl)((Authentication)principal).getPrincipal()).getCode());;
		EmployeeDTO emp = empInfoService.findSalByCode(code);
		List<WorkDTO> worklist = empInfoService.findWorkByCode(code);
		int monthSalary = (emp.getRank().getSalary())/12;
		
		int hourPay = (((emp.getRank().getSalary())/12)/30)/8;
		
		int overWork = 0;
		for (WorkDTO work : worklist) {
			if(work.getWorkOver() != null) {
			overWork += work.getWorkOver();
			}
		}
		
		int totalPay = 0;
		if(overWork != 0) {
			totalPay = (monthSalary + (hourPay * overWork)) * 10000;
		} else {
			totalPay = monthSalary * 10000;
		}
		
		String announce = monthSalary + "만원 + " + overWork + "시간 추가수당";
		
		mv.addObject("totalPay", totalPay);
		mv.addObject("work", worklist);
		mv.addObject("announce", announce);
		mv.addObject("salary", monthSalary);
		mv.addObject("empInfor", emp);
		mv.setViewName("emp/salary/salaryDetailPersonal");
		return mv;
	}

	@GetMapping("/severancePay/{code}")
	public ModelAndView findEntireCode(ModelAndView mv, @PathVariable("code") String code) {
		EmployeeDTO emp = empInfoService.findEntireCode(code);
		List<EmployeeDTO> outWorkerList = empInfoService.findOutWorkerByCode(code);
		
		int dayOfSalary = (emp.getRank().getSalary())/365;		// 일일급여
		Date outDay = emp.getOutDate();							// 퇴직일자
		Date joinDay = emp.getEntDate();						// 입사일자
		
		long calDate = outDay.getTime() - joinDay.getTime();
		
		long calDatedays = calDate / (24 * 60 * 60 * 1000);
		
		calDatedays = Math.abs(calDatedays);
		
		int severancePay = (dayOfSalary * 30) * ((int)calDatedays) / 365;
		
		System.out.println("사원의 직급은 : " + emp.getRank());
		System.out.println("근무 일자는 : " + (int)(calDatedays));
		System.out.println("일일 임금은 : " + dayOfSalary);
		System.out.println("퇴직금은 : " + severancePay);
		
		String announce = severancePay + "0000";
		
		mv.addObject("empInfor", emp);
		mv.addObject("work", outWorkerList);
		mv.addObject("payAnnounce", announce);
		mv.addObject("dayOfSalary", dayOfSalary);
		mv.addObject("severancePay", severancePay);
		
		mv.setViewName("emp/salary/entireDetail");
		return mv;
	}
	
	@GetMapping("/severancePay/modify")
	public ModelAndView modifyServerance(ModelAndView mv, @RequestParam("code") String code) {
		EmployeeDTO emp = empInfoService.empOneRequest(code);
		emp.setOutYN("Y");
		
		List<EmployeeDTO> empList = empInfoService.severancePayRequest();
		
		System.out.println("hi" + code);
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/salary/severancePay");
		
		return mv;
		
	}
	
	@GetMapping("/severancePay")	
	public ModelAndView severancePayRequest(ModelAndView mv) {
				
		List<EmployeeDTO> empList = empInfoService.severancePayRequest();
		
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
	public ModelAndView modifyBankBookInfor(ModelAndView mv, EmployeeDTO modifyInfor,  RedirectAttributes rttr) {
		System.out.println("콘트롤러 modify 오나요?");

		empInfoService.modifyBankBookInfor(modifyInfor);
		
		rttr.addFlashAttribute("successMessage", "사원 정보 수정 성공!!");
		mv.setViewName("redirect:bankBook");
		
		return mv;
	}
	
	@GetMapping("/login")
	public void memberLogin() { }
	
	
}

