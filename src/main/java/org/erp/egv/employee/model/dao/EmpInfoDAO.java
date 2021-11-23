package org.erp.egv.employee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
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

	public List<DepartmentDTO> empDeptList() {
		String jpql = "SELECT d FROM DepartmentDTO as d";
		TypedQuery<DepartmentDTO> query = em.createQuery(jpql, DepartmentDTO.class);
		List<DepartmentDTO> deptList = query.getResultList();
		
		for (DepartmentDTO dept : deptList) {
			System.out.println(dept);
		}
		
		return deptList;
	}

	public List<EmpRankDTO> empRankList() {
		String jpql = "SELECT r FROM EmpRankDTO as r";
		TypedQuery<EmpRankDTO> query = em.createQuery(jpql, EmpRankDTO.class);
		List<EmpRankDTO> rankList = query.getResultList();
		
		for (EmpRankDTO rank : rankList) {
			System.out.println(rank);
		}
		
		return rankList;
	}
	
}
