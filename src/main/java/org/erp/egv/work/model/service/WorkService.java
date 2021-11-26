package org.erp.egv.work.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.work.model.dao.WorkDAO;
import org.erp.egv.work.model.dto.WorkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkService {

	private WorkDAO workDAO;
	
	@Autowired
	public WorkService(WorkDAO workDAO) {
		this.workDAO = workDAO;
	}

	@Transactional
	public List<WorkDTO> workList() {
		return workDAO.workList();
	}

	@Transactional
	public WorkDTO findWorkByCode(int code) {
		return workDAO.findWorkByCode(code);
	}

	@Transactional
	public void modifyWorkOver(WorkDTO work) {
		workDAO.modifyWorkOver(work);
	}
}
