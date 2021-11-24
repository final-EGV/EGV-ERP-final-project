package org.erp.egv.sign.model.service;

import javax.transaction.Transactional;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.sign.model.dao.StampDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StampService {
	
	private StampDAO stampDAO;
	
	@Autowired
	public StampService(StampDAO stampDAO) {
		this.stampDAO = stampDAO;
	}
	
	@Transactional
	public EmployeeDTO selectEmpStampInfo(String empCode) {

		EmployeeDTO empStampinfo = stampDAO.selectEmpStampInfo(empCode);
		
		return empStampinfo;
	}
	
	@Transactional
	public void setStamp(EmployeeDTO employee) {
		stampDAO.setStamp(employee);
	}





}
