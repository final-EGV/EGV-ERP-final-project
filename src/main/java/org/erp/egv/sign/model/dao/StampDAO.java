package org.erp.egv.sign.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

@Repository
public class StampDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EmployeeDTO selectEmpStampInfo(String empCode) {
		EmployeeDTO empStampinfo = em.find(EmployeeDTO.class, empCode);
		
		return empStampinfo;
	}
	
	public void setStamp(EmployeeDTO employee) {
		
		EmployeeDTO employeeAddStamp = em.find(EmployeeDTO.class, employee.getCode());
		
		employeeAddStamp.setStampImgPath(employee.getStampImgPath());
		employeeAddStamp.setStampOrigName(employee.getStampOrigName());
		employeeAddStamp.setStampUuidName(employee.getStampUuidName());
	}

}
