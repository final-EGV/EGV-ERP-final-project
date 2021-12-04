package org.erp.egv.main.controller;

import java.security.Principal;
import java.util.List;

import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.main.model.dto.ScheduleDTO;
import org.erp.egv.main.model.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private MainService mainService;
	
	@Autowired
	public MainController(MainService mainService) {
		this.mainService = mainService;
	}
	
	@GetMapping(value = {"/", "/main"})
	public ModelAndView main(ModelAndView mv, Principal principal) {
		
		if ( principal != null ) {
			String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		}
		
		mv.setViewName("main/main");
		return mv;
	}
	
	@PostMapping(value="/")
	public String redirectMain() {
		return "redirect:/";
	}
	
	@GetMapping(value = "/schedule/scheduleList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ScheduleDTO> selectScheduleList(Principal principal) {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		
		System.out.println(mainService.selectScheduleList(empCode));
		
		return mainService.selectScheduleList(empCode);
	}
	
	@GetMapping(value = "/schedule/selectSchedule", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ScheduleDTO selectSchedule(@RequestParam int schCode) {
		
		ScheduleDTO scheduleDTO = mainService.selectSchedule(schCode);
		System.out.println(scheduleDTO);
		
		return scheduleDTO;
	}
}
