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
	
	/* 사원의 stamp 정보 조회 */
	@Transactional
	public EmployeeDTO selectEmpStampInfo(String empCode) {

		EmployeeDTO empStampinfo = stampDAO.selectEmpStampInfo(empCode);
		
		return empStampinfo;
	}
	
	/* 사원에게 stamp 정보 입력 */
	@Transactional
	public void setStamp(EmployeeDTO employee) {
		stampDAO.setStamp(employee);
	}

}
