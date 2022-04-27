package org.erp.egv.theater.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.theater.entity.ScreeningSchedule;
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
		
		List<ScreeningSchedule> screeningScheduleEntityList = screeningDAO.inquireAllScreeningScheduleList();
		List<ScreeningScheduleDTO> screeningScheduleDtoList = new ArrayList<>();
		
		for (ScreeningSchedule entity : screeningScheduleEntityList) {
			screeningScheduleDtoList.add(entity.toDto());
		}
		
		return screeningScheduleDtoList;
	}
	
	@Transactional
	public void registSchedule(ScreeningScheduleDTO scheduleDto) {
		
		screeningDAO.registSchedule(scheduleDto.toEntity());
	}

	@Transactional
	public void modifySchedule(ScreeningScheduleDTO scheduleToUpdate) {
		
		screeningDAO.modifySchedule(scheduleToUpdate.toEntityWithId());
	}

	@Transactional
	public void deleteScreeningScheduleByCode(int code) {
		
		screeningDAO.deleteScreeningScheduleByCode(code);
	}

}
