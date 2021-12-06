package org.erp.egv.theater.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.theater.model.dao.ScreeningDAO;
import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScreeningService {
	
	private ScreeningDAO screeningDAO;
	
	@Autowired
	public ScreeningService(ScreeningDAO screeningDAO) {
		this.screeningDAO = screeningDAO;
	}

	@Transactional
	public List<ScreeningScheduleDTO> inquireAllScreeningScheduleList() {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return screeningDAO.inquireAllScreeningScheduleList();
	}

	@Transactional
	public void modifySchedule(ScreeningScheduleDTO scheduleFromClient) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		screeningDAO.modifySchedule(scheduleFromClient);
	}

	@Transactional
	public void deleteScreeningScheduleByCode(int code) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		screeningDAO.deleteScreeningScheduleByCode(code);
	}

	@Transactional
	public void registSchedule(ScreeningScheduleDTO schedule) {
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		screeningDAO.registSchedule(schedule);
	}

}
