package org.erp.egv.theater.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.entity.Event;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Event> inquireAllEventList() {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		String jpql = "SELECT e FROM Event e LEFT JOIN e.movie m ORDER BY e.startDatetime DESC";
		
		List<Event> eventEntityList = em.createQuery(jpql, Event.class)
				 				  .getResultList();
		
		return eventEntityList;
	}
	
	public Event inquireSingleEventByCode(int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		Event event = em.find(Event.class, code);
		
		return event;
	}

	public void registEvent(Event event) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		em.persist(event);
	}

	public void modifyEvent(Event eventToUpdate) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		Event eventFromPC = em.find(Event.class, eventToUpdate.getCode());
		
		eventFromPC.update(eventToUpdate.getName(),
							eventToUpdate.getStartDatetime(),
							eventToUpdate.getEndDatetime(),
							eventToUpdate.getRentalCompany(),
							eventToUpdate.getMovie(),
							eventToUpdate.getProduct());
	}

	public void deleteEventByCode(int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		Event event = em.find(Event.class, code);
		
		if (event == null) {
			System.out.println("[Error] " + code + "번 이벤트는 현재 데이터베이스에 존재하지 않습니다.");
			
			return;
		}
		
		em.remove(em.contains(event) ? event : em.merge(event));
	}
	
}
