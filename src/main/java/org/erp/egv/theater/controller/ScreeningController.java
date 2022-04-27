package org.erp.egv.theater.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.theater.model.dto.MovieDTO;
import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;
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
		
		List<MovieDTO> movieList = movieService.inquireOnlyYMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/scheduleScreening");
		
		return mv;
	}
	
	@GetMapping(value = "/drawCalendar", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<ScreeningScheduleDTO> getScreeningScheduleList() {
		
		List<ScreeningScheduleDTO> scheduleList = screeningService.inquireAllScreeningScheduleList();
		
		return scheduleList;
	}
	
	@GetMapping("/regist")
	public ModelAndView goScreeningScheduleRegist(ModelAndView mv) {
		
		List<MovieDTO> movieList = movieService.inquireOnlyYMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/scheduleScreeningRegist");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String registScreeningSchedule(HttpServletRequest request, RedirectAttributes rAttr)
			throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		
		int movieCode = Integer.valueOf(request.getParameter("movie"));
		int theaterCode = Integer.valueOf(request.getParameter("theater"));
		String screeningStart = request.getParameter("screeningStart");
		String screeningEnd = request.getParameter("screeningEnd");
		
		ScreeningScheduleDTO scheduleDto = new ScreeningScheduleDTO();
		
		scheduleDto.setMovie(movieService.inquireSingleMovieByCode(movieCode));
		scheduleDto.setTheater(theaterService.inquireSingleTheaterByCode(theaterCode));
		scheduleDto.setScreeningStart(screeningStart);
		scheduleDto.setScreeningEnd(screeningEnd);
		
		screeningService.registSchedule(scheduleDto);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 신규 상영 스케줄 등록을 성공했습니다.");
		
		return "redirect:/theater/schedule/screening";
	}
	
	@PostMapping("/modify")
	public String modifyScreeningScheduleInfo(HttpServletRequest request, RedirectAttributes rAttr)
			throws UnsupportedEncodingException, ParseException {
		
		request.setCharacterEncoding("UTF-8");
		
		int scheduleCode = Integer.valueOf(request.getParameter("code"));
		int movieCode = Integer.valueOf(request.getParameter("movie"));
		int theaterCode = Integer.valueOf(request.getParameter("theater"));
		String screeningStart = request.getParameter("screeningStart");
		String screeningEnd = request.getParameter("screeningEnd");
		
		ScreeningScheduleDTO scheduleToUpdate = new ScreeningScheduleDTO();
		
		scheduleToUpdate.setCode(scheduleCode);
		scheduleToUpdate.setMovie(movieService.inquireSingleMovieByCode(movieCode));
		scheduleToUpdate.setTheater(theaterService.inquireSingleTheaterByCode(theaterCode));
		scheduleToUpdate.setScreeningStart(screeningStart);
		scheduleToUpdate.setScreeningEnd(screeningEnd);
		
		screeningService.modifySchedule(scheduleToUpdate);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 상영 스케줄 정보 수정을 성공했습니다.");
		
		return "redirect:/theater/schedule/screening";
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public ModelAndView deleteScreeningScheduleByCode(ModelAndView mv, RedirectAttributes rAttr, @RequestParam int code) {
		
		screeningService.deleteScreeningScheduleByCode(code);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] " + code + "번 상영 스케줄 삭제를 성공했습니다.");
		mv.setViewName("redirect:/theater/schedule/screening");
		
		return mv;
	}
	
}
