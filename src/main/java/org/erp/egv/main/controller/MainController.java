package org.erp.egv.main.controller;

import java.security.Principal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.employee.model.dto.UserImpl;
import org.erp.egv.main.model.dto.ScheduleCategoryDTO;
import org.erp.egv.main.model.dto.ScheduleDTO;
import org.erp.egv.main.model.service.MainService;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.service.SignSentSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
	
	private MainService mainService;
	private SignSentSelectService signService;
	
	@Autowired
	public MainController(MainService mainService, SignSentSelectService signService) {
		this.mainService = mainService;
		this.signService = signService;
	}
	
	@GetMapping(value = {"/", "/main"})
	public ModelAndView main(ModelAndView mv, Principal principal) {
		
		if ( principal != null ) {
			String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		}
		
		List<SignDTO> signList = signService.selectProgresssignSignList(((UserImpl)((Authentication)principal).getPrincipal()).getCode());
		
		mv.addObject("signList", signList);
		mv.setViewName("main/main");
		return mv;
	}
	
	@PostMapping("/")
	public String redirectMain() {
		return "redirect:/";
	}
	
	@GetMapping(value = "/schedule/scheduleList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ScheduleDTO> selectScheduleList(Principal principal) {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
				
		return mainService.selectScheduleList(empCode);
	}
	
	@GetMapping(value = "/schedule/scheduleCategoryList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ScheduleCategoryDTO> scheduleCategoryList() {
		
		List<ScheduleCategoryDTO> schCatDTOList = mainService.scheduleCategoryList();
		
		return schCatDTOList;
	}
	
	@GetMapping(value = "/schedule/selectSchedule", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public ScheduleDTO selectSchedule(@RequestParam int schCode) {
		
		ScheduleDTO scheduleDTO = mainService.selectSchedule(schCode);
		
		return scheduleDTO;
	}
	
	@PostMapping("/schedule/insertSchedule")
	public ModelAndView insertSchedule(ModelAndView mv, HttpServletRequest request, RedirectAttributes rAttr, Principal principal) throws ParseException {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		int schCatCode = Integer.valueOf(request.getParameter("addSchCat"));
		ScheduleCategoryDTO schCatDTO = mainService.selectScheduleCategory(schCatCode);
		
		String startDateString = request.getParameter("addStartDate");
		String endDateString = request.getParameter("addEndDate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startUtilDate = format.parse(startDateString);
		java.util.Date endUtilDate = format.parse(endDateString);
		java.sql.Date startDate = new java.sql.Date(startUtilDate.getTime());
		java.sql.Date endDate = new java.sql.Date(endUtilDate.getTime());
		
		String schLocation = request.getParameter("addSchLocation");
		String schDesc = request.getParameter("addSchDesc");
		
		ScheduleDTO newSchedule = new ScheduleDTO();
		newSchedule.setEmpCode(empCode);
		newSchedule.setSchCat(schCatDTO);
		newSchedule.setStartDate(startDate);
		newSchedule.setEndDate(endDate);
		newSchedule.setSchLocation(schLocation);
		newSchedule.setSchDesc(schDesc);
		
		/* 신규 일정 등록 */
		mainService.insertSchedule(newSchedule);

		mv.setViewName("redirect:/main");
		return mv;
	}
	
	@PostMapping("/schedule/updateSchedule")
	public ModelAndView updateSchedule(ModelAndView mv, HttpServletRequest request, RedirectAttributes rAttr, Principal principal) throws ParseException {
		
		String empCode = ((UserImpl)((Authentication)principal).getPrincipal()).getCode();
		String deleteYn = request.getParameter("deleteSchYn");
		int schCode = Integer.valueOf(request.getParameter("selectSchCode"));
		
		int schCatCode = Integer.valueOf(request.getParameter("selectSchCat"));
		ScheduleCategoryDTO schCatDTO = mainService.selectScheduleCategory(schCatCode);
		
		String startDateString = request.getParameter("selectStartDate");
		String endDateString = request.getParameter("selectEndDate");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date startUtilDate = format.parse(startDateString);
		java.util.Date endUtilDate = format.parse(endDateString);
		java.sql.Date startDate = new java.sql.Date(startUtilDate.getTime());
		java.sql.Date endDate = new java.sql.Date(endUtilDate.getTime());
		
		String schLocation = request.getParameter("selectSchLocation");
		String schDesc = request.getParameter("selectSchDesc");
		
		if ("N".equals(deleteYn)) {
		
			ScheduleDTO updateSchedule = new ScheduleDTO();
			updateSchedule.setSchCode(schCode);
			updateSchedule.setEmpCode(empCode);
			updateSchedule.setSchCat(schCatDTO);
			updateSchedule.setStartDate(startDate);
			updateSchedule.setEndDate(endDate);
			updateSchedule.setSchLocation(schLocation);
			updateSchedule.setSchDesc(schDesc);
			System.out.println(updateSchedule);
			
			/* 일정 수정 */
			mainService.updateSchedule(updateSchedule);
		
		} else {

			/* 일정 삭제 */
			mainService.deleteSchedule(schCode);
		}

		mv.setViewName("redirect:/main");
		return mv;
	}
	
}
