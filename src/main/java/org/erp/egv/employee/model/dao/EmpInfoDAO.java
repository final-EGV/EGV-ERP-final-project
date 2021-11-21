package org.erp.egv.employee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

@Repository
public class EmpInfoDAO {
	
	@PersistenceContext	
	private EntityManager em;
	
	public List<EmployeeDTO> empListRequest() {
		String jpql = "SELECT e FROM EmployeeDTO as e";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();

		for (EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
		return empList;

	}
	
}
