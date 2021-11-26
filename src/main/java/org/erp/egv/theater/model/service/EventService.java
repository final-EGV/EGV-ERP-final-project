package org.erp.egv.theater.model.service;

import java.util.List;

import org.erp.egv.theater.model.dao.EventDAO;
import org.erp.egv.theater.model.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

	private EventDAO eventDAO;

	@Autowired
	public EventService(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	public List<EventDTO> inquireAllEventList() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return eventDAO.inquireAllEventList();
	}
	
	
}
