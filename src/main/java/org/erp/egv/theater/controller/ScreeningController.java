package org.erp.egv.theater.controller;

import java.util.List;

import org.erp.egv.theater.model.dto.MovieDTO;
import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;
import org.erp.egv.theater.model.service.MovieService;
import org.erp.egv.theater.model.service.ScreeningService;
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
	private MovieService movieService;
	
	@Autowired
	public ScreeningController(ScreeningService screeningService, MovieService movieService) {
		this.screeningService = screeningService;
		this.movieService = movieService;
	}
	
	@GetMapping("")
	public ModelAndView goScreeningSchedule(ModelAndView mv) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/scheduleScreening");
		
		return mv;
	}
	
	@GetMapping(value = "/drawCalendar", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ScreeningScheduleDTO> getScreeningScheduleList() {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return screeningService.inquireAllScreeningScheduleList();
	}
	
}
