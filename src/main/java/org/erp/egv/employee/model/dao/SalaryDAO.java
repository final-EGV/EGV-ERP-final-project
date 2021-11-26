package org.erp.egv.employee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.EmployeeRoleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SalaryDAO {
	
	@PersistenceContext	
	private EntityManager em;
		
	public List<EmployeeDTO> salaryRequest() {
		String jpql = "SELECT e FROM EmployeeDTO e ORDER BY e.entDate";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();
		
		return empList;

	}
		
	public List<EmployeeDTO> bankBookListRequest() {
		String jpql = "SELECT e FROM EmployeeDTO as e WHERE e.outYN = 'N'";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();

		for (EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
		return empList;
	}
	
	
}
