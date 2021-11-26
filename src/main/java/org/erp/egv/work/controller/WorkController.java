package org.erp.egv.work.controller;

import java.util.List;

import org.erp.egv.work.model.dto.WorkDTO;
import org.erp.egv.work.model.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/work/{code}")
	public ModelAndView findWorkByCode(ModelAndView mv, @PathVariable int code) {
		WorkDTO work = workService.findWorkByCode(code);
		
		mv.addObject("work", work);
		mv.setViewName("/emp/work/workOne");
		
		return mv;
	}
	
	@PostMapping("/work/modify")
	public String modifyWorkOver(@ModelAttribute WorkDTO work) {
		workService.modifyWorkOver(work);
		
		return "redirect:/emp/work";
	}
}
