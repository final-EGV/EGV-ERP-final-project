package org.erp.egv.theater.controller;

import java.util.List;

import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;
import org.erp.egv.theater.model.service.MovieService;
import org.erp.egv.theater.model.service.ScreeningService;
import org.erp.egv.theater.model.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/theater/schedule/screening")
public class ScreeningController {

	private ScreeningService screeningService;
	
	@Autowired
	public ScreeningController(ScreeningService screeningService) {
		this.screeningService = screeningService;
	}
	
	@GetMapping("")
	public String goScreeningSchedule() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return "theater/scheduleScreening";
	}
	
	@GetMapping(value = "/drawCalendar", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ScreeningScheduleDTO> getScreeningScheduleList() {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return screeningService.inquireAllScreeningScheduleList();
	}
	
}
