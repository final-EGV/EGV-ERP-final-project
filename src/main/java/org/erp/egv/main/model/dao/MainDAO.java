package org.erp.egv.main.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.main.model.dto.ScheduleCategoryDTO;
import org.erp.egv.main.model.dto.ScheduleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class MainDAO {
	
	@PersistenceContext
	EntityManager em;

	public List<ScheduleDTO> selectScheduleList(String empCode) {
		
		String jpql = "SELECT s FROM ScheduleDTO as s WHERE empCode = :empCode ORDER BY s.schCode";
		
		List<ScheduleDTO> scheduleList = em.createQuery(jpql, ScheduleDTO.class).setParameter("empCode", empCode).getResultList();
		
		return scheduleList;
	}
	
	public List<ScheduleCategoryDTO> scheduleCategoryList() {
		
		String jpql = "SELECT c FROM ScheduleCategoryDTO as c ORDER BY c.schCatCode";
		
		List<ScheduleCategoryDTO> schCatDTOList = em.createQuery(jpql, ScheduleCategoryDTO.class).getResultList();
		
		return schCatDTOList;
	}

	public ScheduleDTO selectSchedule(int schCode) {
		ScheduleDTO scheduleDTO = em.find(ScheduleDTO.class, schCode);
		return scheduleDTO;
	}



}
