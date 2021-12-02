package org.erp.egv.commu.controller;

import java.util.List;

import org.erp.egv.commu.model.dto.BlindPostDTO;
import org.erp.egv.commu.model.service.CommuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/community/*")
public class CommuController {
	
	private CommuService commuService;
	
	@Autowired
	public CommuController(CommuService commuService) {
		this.commuService = commuService;
	}
	
	@GetMapping("blind")
	public ModelAndView anonymousList(ModelAndView mv) {
		List<BlindPostDTO> blindList = commuService.selectBlindList();
		
		mv.addObject("blindList", blindList);
		mv.setViewName("/community/blind");
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView postDetailSelect(ModelAndView mv, @RequestParam("code")String code) {
		System.out.println(code);
		BlindPostDTO blindPost = commuService.selectBlindPost(Integer.valueOf(code));
		System.out.println(blindPost.getCmt().get(0).getContents());
		
		mv.addObject("blindPost", blindPost);
		mv.setViewName("community/detail");
		return mv;
	}

}
