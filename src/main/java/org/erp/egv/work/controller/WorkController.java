package org.erp.egv.work.controller;

import java.util.List;

import org.erp.egv.work.model.dto.WorkDTO;
import org.erp.egv.work.model.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/emp")
public class WorkController {

	private WorkService workService;
	
	@Autowired
	public WorkController(WorkService workService) {
		this.workService = workService;
	}
	
	@GetMapping("/work")
	public ModelAndView workList(ModelAndView mv) {
		List<WorkDTO> workList = workService.workList();
	
		mv.addObject("workList", workList);
		mv.setViewName("/emp/work/workList");
		
		return mv;
	}
}
