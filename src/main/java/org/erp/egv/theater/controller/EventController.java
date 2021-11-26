package org.erp.egv.theater.controller;

import java.util.List;

import org.erp.egv.theater.model.dto.EventDTO;
import org.erp.egv.theater.model.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/theater/event")
public class EventController {

	private EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@GetMapping
	public ModelAndView inquireAllEventList(ModelAndView mv) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		List<EventDTO> eventList = eventService.inquireAllEventList();
		
		mv.addObject("eventList", eventList);
		mv.setViewName("theater/eventList");
		
		return mv;
	}
	
}
