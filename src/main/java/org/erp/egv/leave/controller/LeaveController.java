package org.erp.egv.leave.controller;

import java.security.Principal;
import java.util.List;

import org.erp.egv.leave.model.dao.LeaveDAO;
import org.erp.egv.leave.model.dto.AnnualLeaveDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.leave.model.dto.UseAnnualLeaveDTO;
import org.erp.egv.leave.model.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
//		for (int i = 0 ; i < leaveList.size(); i ++) {
//			int rest = leaveList.get(i).getCount() - leaveList.get(i).getUseCount();
//			System.out.println(rest);
//		}
		
		mv.addObject("leave", leaveList);
		mv.addObject("usedLeave", usedLeave);
		mv.setViewName("/emp/leave/empLeave");
		
		return mv;
	}
	
	@GetMapping("/Leave/{code}")
	public ModelAndView EmpUsedLeaveList(ModelAndView mv, @PathVariable String code) {
		List<UseAnnualLeaveDTO> usedLeaveList = leaveService.EmpUsedLeaveList(code);
		
		mv.addObject("usedLeave", usedLeaveList);
		mv.setViewName("/emp/leave/leaveDetail");
		
		return mv;
	}
	
}
