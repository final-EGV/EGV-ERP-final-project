package org.erp.egv.work.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.work.model.dto.WorkDTO;
import org.erp.egv.work.model.dto.WorkTypeCategoryDTO;
import org.erp.egv.work.model.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp")
public class WorkController {

	private WorkService workService;
	
	@Autowired
	public WorkController(WorkService workService) {
		this.workService = workService;
	}
	
	/* Date : 2021/11/26
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 근무/근태 조회 컨트롤러
	 */
	@GetMapping("/work")
	public ModelAndView workList(ModelAndView mv) {
		List<WorkDTO> workList = workService.workList();
	
		mv.addObject("workList", workList);
		mv.setViewName("/emp/work/workList");
		
		return mv;
	}
	
	/* Date : 2021/11/26
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 근무/근태 상세 조회 컨트롤러
	 */
	@GetMapping("/work/{code}")
	public ModelAndView findWorkByCode(ModelAndView mv, @PathVariable int code) {
		WorkDTO work = workService.findWorkByCode(code);
		
		mv.addObject("work", work);
		mv.setViewName("/emp/work/workOne");
		
		return mv;
	}
	
	/* Date : 2021/11/26
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 근무/근태 상세 조회후 수정 컨트롤러
	 * [인사관리자는 초과근무 시간을 직접 보고 계산하여 수정후 적용]
	 */
	@PostMapping("/work/modify")
	public String modifyWorkOver(@RequestParam("code") int code, @RequestParam("workOver") int workOver) {
		workService.modifyWorkOver(code, workOver);
		
		return "redirect:/emp/work";
	}
	
	/* Date : 2021/11/30
	 * Writer : JunWoo Kim
	 * 
	 * 로그인 한 사원(객체)의 code를 가져와 사원 개인의 근무/근태를 조회하는 컨트롤러
	 * 로그인 하지않으면 principal()을 불러올 수 없으니 꼭 로그인을 해야한다.
	 * 혹은 로그인을 하지않았으면 aside.html에서 근무근태 버튼이 안보이게 수정예정.
	 */
	@GetMapping("/Work")
	public ModelAndView empWorkList(ModelAndView mv, Principal principal) {
		String code = (((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		List<WorkDTO> workList = workService.workListEmp(code);
		
		mv.addObject("workList", workList);
		mv.setViewName("emp/work/workListEmp");
		
		return mv;
	}
	
	/* Date : 2021/11/30
	 * Writer : JunWoo Kim
	 * 
	 * 로그인 한 사원(객체)의 근무/근태를 상세 조회하는 컨트롤러
	 */
	@GetMapping("/Work/{code}")
	public ModelAndView findWorkByCodeEmp(ModelAndView mv, @PathVariable int code) {
		WorkDTO work = workService.findWorkByCode(code);
		
		mv.addObject("work", work);
		mv.setViewName("/emp/work/workOneEmp");
		
		return mv;
	}
	
	/* Date : 2021/12/01
	 * Writer : JunWoo Kim
	 * 
	 * 로그인 한 사원(객체)의 근무/근태를 새로 추가하는 컨트롤러
	 */
	@PostMapping("/Work/add")
	public ModelAndView addWorkEmp(ModelAndView mv, WorkDTO work, Principal principal, @Param("workType") int workType) {
		WorkTypeCategoryDTO workTypeCategory = workService.findWorkType(workType);
		String code = (((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		EmployeeDTO employee = workService.findEmployee(code);
		
		System.out.println("사원의 정보 : " + employee + ", 초과근무 타입은 : " + workTypeCategory);
		
		WorkDTO newWork = new WorkDTO();
		newWork.setEmpCode(employee);
		newWork.setCategoryCode(workTypeCategory);
		newWork.setWorkDate(new Date());
		newWork.setWorkStart(new Timestamp(System.currentTimeMillis()));
		
		workService.addNewWork(newWork);
		
		List<WorkDTO> workList = workService.workListEmp(code);
		
		mv.addObject("workList", workList);
		mv.setViewName("emp/work/workListEmp");
		
		return mv;
	}
}
