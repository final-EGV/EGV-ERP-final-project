package org.erp.egv.employee.model.service;

import java.util.List;

import org.erp.egv.employee.model.dao.EmpInfoDAO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpInfoService {

	private EmpInfoDAO empInfoDAO;
	@Autowired
	public EmpInfoService(EmpInfoDAO empInfoDAO) {
		this.empInfoDAO = empInfoDAO;
	}

	public List<EmployeeDTO> empListRequest() {
		System.out.println("서비스로 오나요?");
		return empInfoDAO.empListRequest();
	}


}
