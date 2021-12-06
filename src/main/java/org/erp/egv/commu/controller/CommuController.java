package org.erp.egv.commu.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.commu.model.dto.BlindPostDTO;
import org.erp.egv.commu.model.dto.CmtDTO;
import org.erp.egv.commu.model.service.CommuService;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
		BlindPostDTO blindPost = commuService.selectBlindPost(Integer.valueOf(code));
		
		mv.addObject("blindPost", blindPost);
		mv.setViewName("community/detail");
		return mv;
	}
	
	@GetMapping("blindinsert")
	public void blindPostInsert() {}
	
	@GetMapping("mine")
	public ModelAndView blindPostMineSelect(ModelAndView mv, Principal principal) {
		List<BlindPostDTO> blindList = commuService.selectMyBlindList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("blindList", blindList);
		mv.setViewName("/community/blind");
		return mv;
	}
	
	@PostMapping("blindinsert")
	public String blindPostInsert2(HttpServletRequest request, Principal principal) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		EmployeeDTO emp = new EmployeeDTO();
		emp.setCode(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		BlindPostDTO blindPost = new BlindPostDTO(0, date, title, content, emp, null);
		
		commuService.insertBlindPost(blindPost);
		
		return "redirect:/community/blind";
	}
	
	@PostMapping(value="cmt/insert", produces="application/json; charset=UTF-8")
	@ResponseBody
	public CmtDTO insertCmt(@RequestParam("code") String code, @RequestParam("comment") String comment, Principal principal) {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setCode(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		BlindPostDTO post = new BlindPostDTO();
		post.setCode(Integer.valueOf(code));
		
		CmtDTO postCmt = new CmtDTO(0, new java.sql.Date(System.currentTimeMillis()), comment, emp, post); 
		
		commuService.insertPostCmt(postCmt);
		
		return postCmt;
		
	}
	
	@GetMapping(value="cmt/delete", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String deleteCmt(@RequestParam("code") String code) {
		commuService.deleteCmt(Integer.valueOf(code));
		return code;
	}
	
	@GetMapping("postupdate")
	public ModelAndView blindPostUpdate(@RequestParam("code") String code, ModelAndView mv) {
		BlindPostDTO blindPost = commuService.selectBlindPost(Integer.valueOf(code));

		mv.setViewName("community/blindinsert");
		mv.addObject("post", blindPost);
		return mv;
	}
	
	@PostMapping("postupdate")
	public String postUpdate(HttpServletRequest request, Principal principal) {
		int code = Integer.valueOf(request.getParameter("code"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		commuService.updateBlindPost(code, title, content);
		
		return "redirect:/community/blind";
	}
	
	@GetMapping("post/delete")
	public String postDelete(@RequestParam("code") String code) {
		commuService.postDelete(Integer.valueOf(code));
		
		return "redirect:/community/blind";
	}

}
