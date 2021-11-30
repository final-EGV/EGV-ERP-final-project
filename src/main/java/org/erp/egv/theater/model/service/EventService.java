package org.erp.egv.theater.model.service;

import java.util.List;

import javax.transaction.Transactional;

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

	@Transactional
	public List<EventDTO> inquireAllEventList() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return eventDAO.inquireAllEventList();
	}

	@Transactional
	public void registEvent(EventDTO event) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		eventDAO.registEvent(event);
	}

	@Transactional
	public EventDTO inquireSingleEventByCode(int code) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return eventDAO.inquireSingleEventByCode(code);
	}

	@Transactional
	public void modifyEvent(EventDTO eventToModify) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		eventDAO.modifyEvent(eventToModify);
	}
	
}
