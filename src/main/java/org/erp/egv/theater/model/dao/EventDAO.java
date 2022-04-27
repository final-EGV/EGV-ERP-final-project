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
		
		String jpql = "SELECT e FROM Event e LEFT JOIN e.movie m ORDER BY e.startDatetime DESC";
		
		List<Event> eventEntityList = em.createQuery(jpql, Event.class)
				 				  .getResultList();
		
		return eventEntityList;
	}
	
	public Event inquireSingleEventByCode(int code) {
		
		Event event = em.find(Event.class, code);
		
		return event;
	}

	public void registEvent(Event event) {
		
		em.persist(event);
	}

	public void modifyEvent(Event eventToUpdate) {
		
		Event eventFromPC = em.find(Event.class, eventToUpdate.getCode());
		
		eventFromPC.update(eventToUpdate.getName(),
							eventToUpdate.getStartDatetime(),
							eventToUpdate.getEndDatetime(),
							eventToUpdate.getRentalCompany(),
							eventToUpdate.getMovie(),
							eventToUpdate.getProduct());
	}

	public void deleteEventByCode(int code) {
		
		Event event = em.find(Event.class, code);
		
		/*
		 * Return and terminate current transaction, if there is no entity in
		 * the persistence context with given @Id. No futhur process is required
		 * if entity to delete does not exist.
		 */
		if (event == null) {
			return;
		}
		
		em.remove(em.contains(event) ? event : em.merge(event));
	}
	
}
