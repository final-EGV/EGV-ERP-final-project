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
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		List<EventDTO> eventList = eventService.inquireAllEventList();
		
		mv.addObject("eventList", eventList);
		mv.setViewName("theater/eventList");
		
		return mv;
	}
	
	@GetMapping("/regist")
	public ModelAndView registEvent(ModelAndView mv) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		
		mv.addObject("movieList", movieList);
		mv.setViewName("theater/eventRegist");
		
		return mv;
	}
	
	@PostMapping("/regist")
	public String registEvent(HttpServletRequest request, RedirectAttributes rAttr,
			@RequestParam(defaultValue = "0") int movieCode,
			@RequestParam(required = false) String rentalCompany, 
			@RequestParam(required = false) String product)
			throws UnsupportedEncodingException, ParseException {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		String eventName = request.getParameter("name");
		String startDatetime = request.getParameter("startDatetime");
		String endDatetime = request.getParameter("endDatetime");
		
		System.out.println("eventName: " + eventName);
		System.out.println("startDatetime: " + startDatetime);
		System.out.println("endDatetime: " + endDatetime);
		System.out.println("movieCode: " + movieCode);
		System.out.println("rentalCompany: " + rentalCompany);
		System.out.println("product: " + product);
		
		EventDTO event = new EventDTO();
		
		event.setName(eventName);
		event.setStartDatetime(startDatetime);
		event.setEndDatetime(endDatetime);
		event.setRentalCompany(rentalCompany);
		event.setProduct(product);

		if (movieCode != 0) {
			event.setMovieAndEvent(movieService.inquireSingleMovieByCode(movieCode));
		}

		eventService.registEvent(event);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 신규 이벤트 정보 등록에 성공했습니다.");

		return "redirect:list";
	}
	
	@GetMapping("/details")
	public ModelAndView getDetailsOfSingleEvent(ModelAndView mv, @RequestParam int code) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		List<MovieDTO> movieList = movieService.inquireAllMovieList();
		EventDTO event = eventService.inquireSingleEventByCode(code);
		
		mv.addObject("movieList", movieList);
		mv.addObject("event", event);
		mv.setViewName("theater/eventDetails");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public String modifyEvent(HttpServletRequest request, RedirectAttributes rAttr,
			@RequestParam(defaultValue = "0") int code,
			@RequestParam(defaultValue = "0") int movieCode,
			@RequestParam(required = false) String rentalCompany, 
			@RequestParam(required = false) String product)
			throws UnsupportedEncodingException, ParseException {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		String eventName = request.getParameter("name");
		String startDatetime = request.getParameter("startDatetime");
		String endDatetime = request.getParameter("endDatetime");
		
		System.out.println("eventCode: " + code);
		System.out.println("eventName: " + eventName);
		System.out.println("startDatetime: " + startDatetime);
		System.out.println("endDatetime: " + endDatetime);
		System.out.println("movieCode: " + movieCode);
		System.out.println("rentalCompany: " + rentalCompany);
		System.out.println("product: " + product);
		
		EventDTO eventFromClient = new EventDTO();
		
		eventFromClient.setCode(code);
		eventFromClient.setName(eventName);
		eventFromClient.setStartDatetime(startDatetime);
		eventFromClient.setEndDatetime(endDatetime);
		eventFromClient.setRentalCompany(rentalCompany);
		eventFromClient.setProduct(product);

		if (movieCode != 0) {
			eventFromClient.setMovieAndEvent(movieService.inquireSingleMovieByCode(movieCode));
		}
		
		eventService.modifyEvent(eventFromClient);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 이벤트 정보 수정을 성공했습니다.");

		return "redirect:list";
	}
	
	@GetMapping("/delete")
	public String deleteEventByCode(RedirectAttributes rAttr, @RequestParam int code) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		eventService.deleteEventByCode(code);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] " + code + "번 이벤트 삭제를 성공했습니다.");
		
		return "redirect:list";
	}
	
}
