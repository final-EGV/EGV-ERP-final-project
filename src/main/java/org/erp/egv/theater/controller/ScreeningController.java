package org.erp.egv.theater.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.theater.model.dto.MovieDTO;
import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;
import org.erp.egv.theater.model.dto.TheaterDTO;
import org.erp.egv.theater.model.service.MovieService;
import org.erp.egv.theater.model.service.ScreeningService;
import org.erp.egv.theater.model.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/theater/schedule/screening")
public class ScreeningController {

	private ScreeningService screeningService;
	private MovieService movieService;
	private TheaterService theaterService;
	
	@Autowired
	public ScreeningController(ScreeningService screeningService, MovieService movieService, TheaterService theaterService) {
		this.screeningService = screeningService;
		this.movieService = movieService;
		this.theaterService = theaterService;
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
		
		List<ScreeningScheduleDTO> scheduleList = screeningService.inquireAllScreeningScheduleList();
		
		System.out.println("---< SCHEDULE data from DB >---");
		for (ScreeningScheduleDTO schedule : scheduleList) {
			System.out.println(schedule);
		}
		
		return scheduleList;
	}
	
	@PostMapping("/modify")
	public String modifyScreeningScheduleInfo(HttpServletRequest request, RedirectAttributes rAttr)
			throws UnsupportedEncodingException, ParseException {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		int scheduleCode = Integer.valueOf(request.getParameter("code"));
		int movieCode = Integer.valueOf(request.getParameter("movieAndScreening"));
		int theaterCode = Integer.valueOf(request.getParameter("theater"));
		String screeningStart = request.getParameter("screeningStart");
		String screeningEnd = request.getParameter("screeningEnd");
		
		System.out.println("---< SCHEDULE data from Client >---");
		System.out.println("scheduleCode: " + scheduleCode);
		System.out.println("movieCode: " + movieCode);
		System.out.println("theaterCode: " + theaterCode);
		System.out.println("screeningStart: " + screeningStart);
		System.out.println("screeningEnd: " + screeningEnd);
		
		MovieDTO movie = movieService.inquireSingleMovieByCode(movieCode);
		TheaterDTO theater = theaterService.inquireSingleTheaterByCode(theaterCode);
		
		ScreeningScheduleDTO scheduleFromClient = new ScreeningScheduleDTO();
		
		scheduleFromClient.setCode(scheduleCode);
		scheduleFromClient.setMovieAndScreening(movie);
		scheduleFromClient.setTheater(theater);
		scheduleFromClient.setScreeningStart(screeningStart);
		scheduleFromClient.setScreeningEnd(screeningEnd);
		
		screeningService.modifySchedule(scheduleFromClient);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 상영 스케줄 정보 수정을 성공했습니다.");
		
		return "redirect:/theater/scheduleScreening";
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ModelAndView deleteScreeningScheduleByCode(ModelAndView mv, RedirectAttributes rAttr, @RequestParam int code) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		System.out.println("delete code: " + code);
		screeningService.deleteScreeningScheduleByCode(code);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] " + code + "번 상영 스케줄 삭제를 성공했습니다.");
		mv.setViewName("redirect:/theater/schedule/screening");
		
		return mv;
	}
	
}
