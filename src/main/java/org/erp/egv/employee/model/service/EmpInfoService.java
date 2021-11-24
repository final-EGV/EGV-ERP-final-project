package org.erp.egv.employee.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.erp.egv.employee.model.dao.EmpInfoDAO;
import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpInfoService {

	private EmpInfoDAO empInfoDAO;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmpInfoService(EmpInfoDAO empInfoDAO, PasswordEncoder passwordEncoder) {
		this.empInfoDAO = empInfoDAO;
		this.passwordEncoder = passwordEncoder;
	}

	public List<EmployeeDTO> empListRequest() {
		System.out.println("서비스로 오나요?");
		return empInfoDAO.empListRequest();
	}
	

	public EmployeeDTO empOneRequest(String empCode) {
		return empInfoDAO.empOneRequest(empCode);
	}

	/* 부서목록 리스트 */
	@Transactional
	public List<DepartmentDTO> findDepartmentList() {
		return empInfoDAO.findDepartmentList();
	}

	/* 부서 등록 */
	@Transactional
	public void addNewDept(DepartmentDTO newDept) {
		empInfoDAO.addNewDept(newDept);
	}
	
	/* 직위/직급 리스트 */
	@Transactional
	public List<EmpRankDTO> findEmpRankList() {
		return empInfoDAO.findEmpRankList();
	}
	
	
	public List<EmployeeDTO> salaryRequest() {
		return empInfoDAO.salaryRequest();
	}
	
	public List<DepartmentDTO> empDeptList() {
		return empInfoDAO.empDeptList();
	}
	
	public List<EmpRankDTO> empRankList() {
		return empInfoDAO.empRankList();
	}

	public Map<String, String> finId(String name, String birtha, String email) {
		EmployeeDTO emp = empInfoDAO.finId(email);
		String code = null;
		Map<String, String> result = new HashMap<>(); 
		
		if(emp != null) {
			System.out.println("이메일 일치");
			if (emp.getName().equals(name)) {
				System.out.println("이름 일치");
				if(emp.getRrn().substring(0, 6).equals(birtha)) {
					System.out.println("생일 일치");
					code = emp.getCode();
				}
			}
		}
		
		if(code != null) {
			result.put("code", code);
			result.put("date", String.valueOf(emp.getEntDate()));
		}
		
		return result;
	}

	public EmployeeDTO pwReset(String code, String name, String birtha, String email) {
		EmployeeDTO emp = empInfoDAO.findEmpByCode(code);
		String repw = null;
		
		
		if((emp != null)) {
			if (!(emp.getName().equals(name) && emp.getRrn().substring(0, 6).equals(birtha) && emp.getEmail().equals(email))) {
				emp = null;
			} else {
				repw = emp.getCode() + birtha;
				emp = empInfoDAO.resetPw(code, passwordEncoder.encode(repw));
			}
		} 
		
		return emp;
	}
    
	public List<EmployeeDTO> severancePayRequest() {
		return empInfoDAO.severancePayRequest();
	}



}
