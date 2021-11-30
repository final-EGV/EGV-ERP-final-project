package org.erp.egv.theater.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public String registEvent(HttpServletRequest request, RedirectAttributes rAttr)
			throws UnsupportedEncodingException, ParseException {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		String eventName = request.getParameter("name");
		Timestamp startDatetime =
				new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("startDatetime").replace("T", " ")).getTime());
		Timestamp endDatetime =
				new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("endDatetime").replace("T", " ")).getTime());
		String movieName = request.getParameter("movieAndEvent");
		String rentalCompany = request.getParameter("rentalCompany");
		String product = request.getParameter("product");
		
		System.out.println("eventName: " + eventName);
		System.out.println("startDatetime: " + startDatetime);
		System.out.println("endDatetime: " + endDatetime);
		System.out.println("movieName: " + movieName + ", length: " + movieName.length());
		System.out.println("rentalCompany: " + rentalCompany);
		System.out.println("product: " + product);
		
		EventDTO event = new EventDTO();
		
		event.setName(eventName);
		event.setStartDatetime(startDatetime);
		event.setEndDatetime(endDatetime);
		event.setRentalCompany(rentalCompany);
		event.setProduct(product);

		if (!movieName.isEmpty()) {
			event.setMovieAndEvent(movieService.inquireSingleMovieByName(movieName));
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
		
		// type casting: from Timestampt to String, from server to client.
		String startDatetime = event.getStartDatetime()
									.toString()
									.substring(0, 16)
									.replace(' ', 'T');
		String endDatetime = event.getEndDatetime()
									.toString()
									.substring(0, 16)
									.replace(' ', 'T');
		
		mv.addObject("movieList", movieList);
		mv.addObject("event", event);
		mv.addObject("startDatetime", startDatetime);
		mv.addObject("endDatetime", endDatetime);
		mv.setViewName("theater/eventDetails");
		
		return mv;
	}
	
	@PostMapping("/modify")
	public String modifyEvent(HttpServletRequest request, RedirectAttributes rAttr)
			throws UnsupportedEncodingException, ParseException {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		request.setCharacterEncoding("UTF-8");
		
		int eventCode = Integer.valueOf(request.getParameter("code"));
		String eventName = request.getParameter("name");
		Timestamp startDatetime =
				new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("startDatetime").replace("T", " ")).getTime());
		Timestamp endDatetime =
				new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(request.getParameter("endDatetime").replace("T", " ")).getTime());
		String movieName = request.getParameter("movieAndEvent");
		String rentalCompany = request.getParameter("rentalCompany");
		String product = request.getParameter("product");
		
		System.out.println("eventCode: " + eventCode);
		System.out.println("eventName: " + eventName);
		System.out.println("startDatetime: " + startDatetime);
		System.out.println("endDatetime: " + endDatetime);
		System.out.println("movieName: " + movieName + ", length: " + movieName.length());
		System.out.println("rentalCompany: " + rentalCompany);
		System.out.println("product: " + product);
		
		EventDTO eventToModify = new EventDTO();
		
		eventToModify.setCode(eventCode);
		eventToModify.setName(eventName);
		eventToModify.setStartDatetime(startDatetime);
		eventToModify.setEndDatetime(endDatetime);
		eventToModify.setRentalCompany(rentalCompany);
		eventToModify.setProduct(product);

		if (!movieName.isEmpty()) {
			eventToModify.setMovieAndEvent(movieService.inquireSingleMovieByName(movieName));
		}
		
		eventService.modifyEvent(eventToModify);
		
		rAttr.addFlashAttribute("flashMessage", "[Success] 이벤트 정보 수정을 성공했습니다.");

		return "redirect:list";
	}
	
}
