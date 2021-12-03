package org.erp.egv.theater.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class ScreeningDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<ScreeningScheduleDTO> inquireAllScreeningScheduleList() {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		String jpql = "SELECT s FROM ScreeningScheduleDTO s";
		
		List<ScreeningScheduleDTO> scheduleList = em.createQuery(jpql, ScreeningScheduleDTO.class).getResultList();

		return scheduleList;
	}

}
