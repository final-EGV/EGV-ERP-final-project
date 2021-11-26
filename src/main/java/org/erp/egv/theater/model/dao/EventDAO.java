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
		
		String jpql = "SELECT e FROM EventDTO e ORDER BY e.date";
		
		List<EventDTO> eventList = em.createQuery(jpql, EventDTO.class)
									 .getResultList();
		
		for (EventDTO event : eventList) {
			System.out.println(event);
		}
		
		return eventList;
	}
	
}
