package org.erp.egv.main.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.main.model.dao.MainDAO;
import org.erp.egv.main.model.dto.ScheduleCategoryDTO;
import org.erp.egv.main.model.dto.ScheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	
	private MainDAO mainDAO;
	
	@Autowired
	public MainService(MainDAO mainDAO) {
		this.mainDAO = mainDAO;
	}

	@Transactional
	public List<ScheduleDTO> selectScheduleList(String empCode) {
		
		return mainDAO.selectScheduleList(empCode);
	}
	
	@Transactional
	public List<ScheduleCategoryDTO> scheduleCategoryList() {
		List<ScheduleCategoryDTO> schCatDTOList = mainDAO.scheduleCategoryList();
		
		return schCatDTOList;
	}

	@Transactional
	public ScheduleDTO selectSchedule(int schCode) {
		ScheduleDTO scheduleDTO = mainDAO.selectSchedule(schCode);
		
		return scheduleDTO;
	}

	@Transactional
	public ScheduleCategoryDTO selectScheduleCategory(int schCatCode) {
		ScheduleCategoryDTO schCatDTO = mainDAO.selectScheduleCategory(schCatCode);
		
		return schCatDTO;
	}

	@Transactional
	public void insertSchedule(ScheduleDTO newSchedule) {
		
		mainDAO.insertSchedule(newSchedule);
	}

	@Transactional
	public void updateSchedule(ScheduleDTO updateSchedule) {
		
		mainDAO.updateSchedule(updateSchedule);
	}

	@Transactional
	public void deleteSchedule(int schCode) {
		
		mainDAO.deleteSchedule(schCode);
	}



}
