package org.erp.egv.theater.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.entity.ScreeningSchedule;
import org.springframework.stereotype.Repository;

@Repository
public class ScreeningDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<ScreeningSchedule> inquireAllScreeningScheduleList() {
		
		String jpql = "SELECT s FROM ScreeningSchedule s";
		
		List<ScreeningSchedule> scheduleList = em.createQuery(jpql, ScreeningSchedule.class)
													.getResultList();

		return scheduleList;
	}
	
	public void registSchedule(ScreeningSchedule schedule) {
		
		em.persist(schedule);
	}

	public void modifySchedule(ScreeningSchedule scheduleToUpdate) {
		
		ScreeningSchedule scheduleFromPC = em.find(ScreeningSchedule.class, scheduleToUpdate.getCode());
		
		scheduleFromPC.update(scheduleToUpdate.getMovie(),
				scheduleToUpdate.getTheater(),
				scheduleToUpdate.getScreeningStart(),
				scheduleToUpdate.getScreeningEnd());
	}

	public void deleteScreeningScheduleByCode(int code) {
		
		ScreeningSchedule schedule = em.find(ScreeningSchedule.class, code);
		
		/*
		 * Return and terminate current transaction, if there is no entity in
		 * the persistence context with given @Id. No futhur process is required
		 * if entity to delete does not exist.
		 */
		if (schedule == null) {
			return;
		}
		
		em.remove(em.contains(schedule) ? schedule : em.merge(schedule));
	}

}
