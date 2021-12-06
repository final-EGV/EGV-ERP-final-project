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

	public void modifySchedule(ScreeningScheduleDTO scheduleFromClient) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		ScreeningScheduleDTO scheduleFromEntity = em.find(ScreeningScheduleDTO.class, scheduleFromClient.getCode());
		
		scheduleFromEntity.setMovieAndScreening(scheduleFromClient.getMovieAndScreening());
		scheduleFromEntity.setTheater(scheduleFromClient.getTheater());
		scheduleFromEntity.setScreeningStart(scheduleFromClient.getScreeningStart());
		scheduleFromEntity.setScreeningEnd(scheduleFromClient.getScreeningEnd());
		
	}

	public void deleteScreeningScheduleByCode(int code) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		ScreeningScheduleDTO schedule = em.find(ScreeningScheduleDTO.class, code);
		
		if (schedule == null) {
			System.out.println("[Error] " + code + "번 상영 스케줄은 현재 데이터베이스에 존재하지 않습니다.");
			
			return;
		}
		
		em.remove(em.contains(schedule) ? schedule : em.merge(schedule));
		
	}

}
