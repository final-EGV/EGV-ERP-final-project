package org.erp.egv.theater.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.theater.entity.Event;
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
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		List<Event> eventEntityList = eventDAO.inquireAllEventList();
		List<EventDTO> eventDtoList = new ArrayList<>();
		
		for (Event entity : eventEntityList) {
			eventDtoList.add(entity.toDto());
		}
		
		return eventDtoList;
	}
	
	@Transactional
	public EventDTO inquireSingleEventByCode(int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		return eventDAO.inquireSingleEventByCode(code).toDto();
	}

	@Transactional
	public void registEvent(EventDTO eventDto) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		eventDAO.registEvent(eventDto.toEntity());
	}

	@Transactional
	public void modifyEvent(EventDTO eventToUpdate) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		eventDAO.modifyEvent(eventToUpdate.toEntityWithId());
	}

	@Transactional
	public void deleteEventByCode(int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		eventDAO.deleteEventByCode(code);
	}
	
}
