package org.erp.egv.main.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.main.model.dto.ScheduleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class MainDAO {
	
	@PersistenceContext
	EntityManager em;

	public List<ScheduleDTO> selectScheduleList() {
		
		String jpql = "SELECT s FROM ScheduleDTO as s ORDER BY s.schCode";
		
		List<ScheduleDTO> scheduleList = em.createQuery(jpql, ScheduleDTO.class).getResultList();
		
		return scheduleList;
	}

}
