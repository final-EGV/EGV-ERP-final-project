package org.erp.egv.theater.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.model.dto.EventDTO;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<EventDTO> inquireAllEventList() {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		String jpql = "SELECT e FROM EventDTO e LEFT JOIN e.movieAndEvent m ORDER BY e.startDatetime DESC";
		
		List<EventDTO> eventList = em.createQuery(jpql, EventDTO.class)
				 					 .getResultList();
		
		System.out.println("eventList's size: " + eventList.size());
		
		return eventList;
	}

	public void registEvent(EventDTO event) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		em.persist(event);
	}

	public EventDTO inquireSingleEventByCode(int code) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		EventDTO event = em.find(EventDTO.class, code);
		
		return event;
	}
	
}
