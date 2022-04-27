package org.erp.egv.theater.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.erp.egv.theater.model.dto.EventDTO;
import org.erp.egv.theater.model.dto.MovieDTO;
import org.erp.egv.theater.model.service.EventService;
import org.erp.egv.theater.model.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/theater/event")
public class EventController {

	private EventService eventService;
	private MovieService movieService;

	@Autowired
	public EventController(EventService eventService, MovieService movieService) {
		this.eventService = eventService;
		this.movieService = movieService;
	}
	
	@GetMapping("/list")
	public ModelAndView inquireAllEventList(ModelAndView mv) {
		
		List<EventDTO> eventList = eventService.inquireAllEventList();
		
		mv.addObject("eventList", eventList);
		mv.setViewName("theater/eventList");
		
		return mv;
	}
	
	@GetMapping("/details")
	public ModelAndView getDetailsOfSingleEvent(ModelAndView mv, @RequestParam int code) {
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		EventDTO event = eventService.inquireSingleEventByCode(code);
		
		mv.addObject("movieList", movieList);
		mv.addObject("event", event);
		mv.setViewName("theater/eventDetails");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public ModelAndView registEvent(ModelAndView mv) {
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/eventRegist");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String registEvent(HttpServletRequest request, RedirectAttributes rAttr,
			@RequestParam(defaultValue = "0", name = "movieCode") int movieCode,
			@RequestParam(required = false) String rentalCompany,
			@RequestParam(required = false) String product)
			throws UnsupportedEncodingException, ParseException {
		
		request.setCharacterEncoding("UTF-8");
		
		String eventName = request.getParameter("name");
		String startDatetime = request.getParameter("startDatetime");
		String endDatetime = request.getParameter("endDatetime");
		
		EventDTO eventDto = new EventDTO();
		
		eventDto.setName(eventName);
		eventDto.setStartDatetime(startDatetime);
		eventDto.setEndDatetime(endDatetime);
		eventDto.setRentalCompany(rentalCompany);
		eventDto.setProduct(product);

		// Inquire movie entity, if requested parameter is not empty
		if (movieCode != 0) {
			eventDto.setMovie(movieService.inquireSingleMovieByCode(movieCode));
		}

		eventService.registEvent(eventDto);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 신규 이벤트 정보 등록에 성공했습니다.");

		return "redirect:list";
	}
	
	@PostMapping("/modify")
	public String modifyEvent(HttpServletRequest request, RedirectAttributes rAttr,
			@RequestParam(defaultValue = "0") int code,
			@RequestParam(defaultValue = "0", name = "movieCode") int movieCode,
			@RequestParam(required = false) String rentalCompany,
			@RequestParam(required = false) String product)
			throws UnsupportedEncodingException, ParseException {
		
		request.setCharacterEncoding("UTF-8");
		
		String eventName = request.getParameter("name");
		String startDatetime = request.getParameter("startDatetime");
		String endDatetime = request.getParameter("endDatetime");
		
		EventDTO eventToUpdate = new EventDTO();
		
		eventToUpdate.setCode(code);
		eventToUpdate.setName(eventName);
		eventToUpdate.setStartDatetime(startDatetime);
		eventToUpdate.setEndDatetime(endDatetime);
		eventToUpdate.setRentalCompany(rentalCompany);
		eventToUpdate.setProduct(product);

		// Inquire movie entity, if requested parameter is not empty
		if (movieCode != 0) {
			eventToUpdate.setMovie(movieService.inquireSingleMovieByCode(movieCode));
		}
		
		eventService.modifyEvent(eventToUpdate);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 이벤트 정보 수정을 성공했습니다.");

		return "redirect:list";
	}
	
	@GetMapping("/delete")
	public String deleteEventByCode(RedirectAttributes rAttr, @RequestParam int code) {
		
		eventService.deleteEventByCode(code);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] " + code + "번 이벤트 삭제를 성공했습니다.");
		
		return "redirect:list";
	}
	
}
