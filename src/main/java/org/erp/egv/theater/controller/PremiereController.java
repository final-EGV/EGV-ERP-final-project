package org.erp.egv.theater.controller;

import java.util.List;

import org.erp.egv.theater.model.dto.MovieDTO;
import org.erp.egv.theater.model.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/theater/schedule/premiere")
public class PremiereController {
	
	private MovieService movieService;
	
	@Autowired
	public PremiereController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("")
	public ModelAndView scheduleMoviePremiere(ModelAndView mv) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/schedulePremiere");
		
		return mv;
	}
	
	@GetMapping(value="/drawCalendar", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<MovieDTO> inquireOnlyYMovieList() {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		return movieService.inquireOnlyYMovieList();
	}
}
