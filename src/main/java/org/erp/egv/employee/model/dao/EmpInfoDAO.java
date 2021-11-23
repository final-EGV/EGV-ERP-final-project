package org.erp.egv.employee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.EmployeeRoleDTO;
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
	
	public EmployeeDTO findMemberById(String name) {
		EmployeeDTO emp = em.find(EmployeeDTO.class, name);
		
		String jpql = "SELECT a FROM EmployeeRoleDTO as a WHERE a.employee.code = :code";
		emp.setEmployeeRoleList(em.createQuery(jpql, EmployeeRoleDTO.class).setParameter("code", emp.getCode()).getResultList());
		
		return emp;
	}

	public EmployeeDTO finId(String email) throws javax.persistence.NoResultException {
		String jpql = "SELECT a FROM EmployeeDTO as a WHERE a.email = :email";
		
		EmployeeDTO emp = null;
		
		try{
			emp = em.createQuery(jpql, EmployeeDTO.class).setParameter("email", email).getSingleResult();
		}catch (NoResultException e){
			
		}
		
		return emp;
	}

	public EmployeeDTO findEmpByCode(String code) throws javax.persistence.NoResultException {
		EmployeeDTO emp = em.find(EmployeeDTO.class, code);
		
		return emp;
	}

	@Transactional
	public EmployeeDTO resetPw(String code, String repw) throws javax.persistence.NoResultException {
		EmployeeDTO emp = em.find(EmployeeDTO.class, code);
		emp.setPwd(repw);
		return emp;
		
	}
	
}
