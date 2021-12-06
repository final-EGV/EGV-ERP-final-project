package org.erp.egv.leave.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.leave.model.dto.AnnualLeaveCategoryDTO;
import org.erp.egv.leave.model.dto.AnnualLeaveDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.leave.model.dto.UseAnnualLeaveDTO;
import org.erp.egv.leave.model.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp")
public class LeaveController {

	private LeaveService leaveService;
	
	@Autowired
	public LeaveController(LeaveService leaveService) {
		this.leaveService = leaveService;
	}
	
	@GetMapping("/leave")
	public ModelAndView workList(ModelAndView mv) {
		List<UseAnnualLeaveDTO> leaveList = leaveService.leaveList();
	
		mv.addObject("leave", leaveList);
		mv.setViewName("/emp/work/annualLeave");
		
		return mv;
	}
	
	/* Date : 2021/12/03
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 기능
	 * 사원들의 연가조회 기능
	 */
	@GetMapping("/Leave")
	public ModelAndView EmpleaveList(ModelAndView mv) {
		List<AnnualLeaveDTO> leaveList = leaveService.EmpLeaveList();
		List<UseAnnualLeaveDTO> usedLeave = leaveService.EmpUsedLeave();
		
		
		mv.addObject("leave", leaveList);
		mv.addObject("usedLeave", usedLeave);
		mv.setViewName("/emp/leave/empLeave");
		
		return mv;
	}
	
	/* Date : 2021/12/05
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 기능
	 * 사원들의 연가세부조회 기능
	 */
	@GetMapping("/Leave/{code}")
	public ModelAndView EmpUsedLeaveList(ModelAndView mv, @PathVariable String code) {
		List<UseAnnualLeaveDTO> usedLeaveList = leaveService.EmpUsedLeaveList(code);
		EmployeeDTO emp = leaveService.findEmp(code);
		
		mv.addObject("emp", emp);
		mv.addObject("usedLeave", usedLeaveList);
		mv.setViewName("/emp/leave/leaveDetail");
		
		return mv;
	}
	
	/* Date : 2021/12/06
	 * Writer : JunWoo Kim
	 * 
	 * 인사관리자의 기능
	 * 사원의 연가 추가 기능 (사원의 사용한 연가 +)
	 */
	@PostMapping("/Leave/add")
	public ModelAndView addLeave(HttpServletRequest request, ModelAndView mv,
								@Param("category") int category) throws ParseException {
		UseAnnualLeaveDTO leave = new UseAnnualLeaveDTO();
		AnnualLeaveCategoryDTO categoryCode = leaveService.findCategory(category);
		leave.setCategoryCode(categoryCode);
		String startDate = request.getParameter("startDate");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREA);
		LocalDate dateStart = LocalDate.parse(startDate, format);
		Date parsedStartDate = java.sql.Date.valueOf(dateStart);
		leave.setStart(parsedStartDate);
		
		String endDate = request.getParameter("endDate");
		LocalDate dateEnd = LocalDate.parse(endDate, format);
		Date parsedEndDate = java.sql.Date.valueOf(dateEnd);
		leave.setEnd(parsedEndDate);
		
		String empCode = request.getParameter("employeeCode");
		EmployeeDTO emp = leaveService.findEmp(empCode);
		leave.setEmpCode(emp);
		
		int total = Integer.parseInt(request.getParameter("total"));
		leave.setTotalDate(total);
		
		String content = request.getParameter("content");
		leave.setContent(content);
		
		leaveService.addLeave(leave);
		leaveService.modifyLeave(empCode, total);
		
		mv.setViewName("redirect:/emp/Leave/" + empCode);
		
		return mv;
	}
	
}
