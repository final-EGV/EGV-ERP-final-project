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
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		String jpql = "SELECT s FROM ScreeningSchedule s";
		
		List<ScreeningSchedule> scheduleList = em.createQuery(jpql, ScreeningSchedule.class)
													.getResultList();

		return scheduleList;
	}
	
	public void registSchedule(ScreeningSchedule schedule) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		em.persist(schedule);
	}

	public void modifySchedule(ScreeningSchedule scheduleToUpdate) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		ScreeningSchedule scheduleFromPC = em.find(ScreeningSchedule.class, scheduleToUpdate.getCode());
		
		scheduleFromPC.update(scheduleToUpdate.getMovie(),
				scheduleToUpdate.getTheater(),
				scheduleToUpdate.getScreeningStart(),
				scheduleToUpdate.getScreeningEnd());
	}

	public void deleteScreeningScheduleByCode(int code) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		ScreeningSchedule schedule = em.find(ScreeningSchedule.class, code);
		
		if (schedule == null) {
			System.out.println("[Error] " + code + "번 상영 스케줄은 현재 데이터베이스에 존재하지 않습니다.");
			
			return;
		}
		
		em.remove(em.contains(schedule) ? schedule : em.merge(schedule));
	}

}
