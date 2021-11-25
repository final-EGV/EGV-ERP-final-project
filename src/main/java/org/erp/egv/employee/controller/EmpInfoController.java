package org.erp.egv.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.service.EmpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		
		mv.addObject("empList", empList);
		mv.setViewName("emp/emplist");
		
		return mv;
	}
	
	@GetMapping("/empInfor")
	public ModelAndView empOneRequest(ModelAndView mv, @RequestParam String empCode) {
		System.out.println("콘트롤러 one 오나요?");
		
		EmployeeDTO empInfor = empInfoService.empOneRequest(empCode);
		
		mv.addObject("empInfor", empInfor);
		mv.setViewName("emp/empInfor");
		
		return mv;
	}
	
	@GetMapping(value="departmentList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<DepartmentDTO> findDepartmentList(){
		return empInfoService.findDepartmentList();
	}
	
	@GetMapping(value="empRankList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<EmpRankDTO> findEmpRankList(){
		return empInfoService.findEmpRankList();
	}
	
	
	/* Date : 2021/11/25
	 * Writer : Hansoo Lee
	 * 
	 * 사번조합기를 사용하여 사번등록, 비밀번호는 '사번 + 생년월일'로 조합할 것이므로 새로 세팅해준다.  
	 * 서비스에서 시큐리티로 비밀번호를 암호화 해주어야 한다.
	 * 
	 * 조인컬럼은 새로 객체를 만들어서 set 해주자!
	 * */
	
	@PostMapping("/registEmp")
	public ModelAndView empRegistRequest(ModelAndView mv, EmployeeDTO newEmp, @RequestParam int rankRegist, @RequestParam int deptRegist,  RedirectAttributes rttr) {
		System.out.println("콘트롤러 regist 오나요?");
		
		DepartmentDTO departmentDTO = new DepartmentDTO();
		EmpRankDTO empRankDTO = new EmpRankDTO();
		
		departmentDTO.setCode(deptRegist);
		empRankDTO.setCode(rankRegist);

		newEmp.setDept(departmentDTO);
		newEmp.setRank(empRankDTO);
		
		newEmp.setCode(empInfoService.findNextEmpNum());
		newEmp.setPwd(empInfoService.findNextEmpNum() + newEmp.getRrn().substring(0,6));

		empInfoService.empRegistRequest(newEmp);
		rttr.addFlashAttribute("registSuccessMessage", "사원 등록 성공!!");
		mv.setViewName("redirect:/emp/list");
		
		return mv;
	}
	
	@GetMapping("/dept")
	public ModelAndView departmentList(ModelAndView mv) {
		List<DepartmentDTO> deptList = empInfoService.empDeptList();
		
		mv.addObject("deptList", deptList);
		mv.setViewName("emp/dept/deptList");
		
		return mv;
	}
	
	@GetMapping("/dept/{code}")
	public ModelAndView findDeptByCode(ModelAndView mv, @PathVariable int code) {
		DepartmentDTO dept = empInfoService.findDeptByCode(code);
		
		System.out.println(dept);
		
		mv.addObject("dept", dept);
		mv.setViewName("/emp/dept/deptOne");
		
		return mv;
	}
	
	@PostMapping("/dept/modify")
	public String modifyDept(@ModelAttribute DepartmentDTO dept) {
		empInfoService.modifyDept(dept);

		return "redirect:/emp/dept";
	}
	
	@PostMapping("/dept/add")
	public ModelAndView addNewDept(ModelAndView mv, DepartmentDTO newDept, Locale locale) {
		
		empInfoService.addNewDept(newDept);
		mv.setViewName("redirect:/emp/dept");
		
		return mv;
	}
	
	@GetMapping("/rank")
	public ModelAndView rankList(ModelAndView mv) {
		List<EmpRankDTO> rankList = empInfoService.empRankList();
		
		mv.addObject("rankList", rankList);
		mv.setViewName("emp/rank/rankList");
		
		return mv;
	}
	
	@GetMapping("/rank/{code}")
	public ModelAndView findRankByCode(ModelAndView mv, @PathVariable int code) {
		EmpRankDTO rank = empInfoService.findRankByCode(code);
		
		System.out.println(rank);
		
		mv.addObject("rank", rank);
		mv.setViewName("/emp/rank/rankOne");
		
		return mv;
	}
	
	@PostMapping("/rank/modify")
	public String modifyRank(@ModelAttribute EmpRankDTO rank) {
		empInfoService.modifyRank(rank);
		
		return "redirect:/emp/rank";
	}
	
	@GetMapping("/login")
	public void empLogin() { }
	
	@GetMapping("/findid")
	public void findId() { }
	
	@PostMapping(value="findid", produces="application/json; charset=UTF-8")
	@ResponseBody
	public EmployeeDTO findId2(@RequestParam("name") String name,@RequestParam("birth") String birth,@RequestParam(
			"email") String email) {
		String birtha = birth.substring(2, 4) + birth.substring(5, 7) + birth.substring(8, 10);
		EmployeeDTO emp = empInfoService.finId(email);
				
		if(emp != null) {
			System.out.println("이메일 일치");
			if (emp.getName().equals(name)) {
				System.out.println("이름 일치");
				if(emp.getRrn().substring(0, 6).equals(birtha)) {
					System.out.println("생일 일치");
					emp.setEmployeeRoleList(null);
				} else {
					emp = null;
				}
			} else {
				emp = null;
			}
		} else {
			emp = null;
		}
		
		return emp;
	}
	
	@GetMapping("/pwreset")
	public void pwreset() { }
	
	
	@PostMapping(value="repw", produces="application/json; charset=UTF-8")
	@ResponseBody
	public EmployeeDTO resetPw(@RequestParam("code") String code, @RequestParam("name") String name,@RequestParam("birth") String birth,@RequestParam(
			"email") String email) {
		String birtha = birth.substring(2, 4) + birth.substring(5, 7) + birth.substring(8, 10);
		EmployeeDTO emp = empInfoService.empOneRequest(code);
		
		if((emp != null)) {
			if (!(emp.getName().equals(name) && emp.getRrn().substring(0, 6).equals(birtha) && emp.getEmail().equals(email))) {
				emp = null;
			} else {
				String repw = emp.getCode() + birtha;
				emp = empInfoService.resetPw(code, repw);
				emp.setEmployeeRoleList(null);
			}
		} 
		
		return emp;
		
	}
	
	
	
	
}
