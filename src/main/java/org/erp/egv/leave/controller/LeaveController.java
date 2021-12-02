package org.erp.egv.leave.controller;

import java.util.List;

import org.erp.egv.leave.model.dto.UseAnnualLeaveDTO;
import org.erp.egv.leave.model.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
