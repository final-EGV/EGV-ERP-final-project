package org.erp.egv.employee.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

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
	
	public EmployeeDTO empOneRequest(String empCode) {
		EmployeeDTO emp = em.find(EmployeeDTO.class, empCode);
		
		System.out.println(emp);
		
		return emp;
	}
	
	public List<DepartmentDTO> findDepartmentList() {
		String jpql = "SELECT a FROM DepartmentDTO as a ORDER BY a.code ASC";
		
		TypedQuery<DepartmentDTO> query = em.createQuery(jpql, DepartmentDTO.class);
		List<DepartmentDTO> departmentList = query.getResultList();
		
		return departmentList;
	}

	
	public List<EmpRankDTO> findEmpRankList() {
		String jpql = "SELECT a FROM EmpRankDTO as a ORDER BY a.code ASC";
		
		TypedQuery<EmpRankDTO> query = em.createQuery(jpql, EmpRankDTO.class);
		List<EmpRankDTO> empRankList = query.getResultList();
		
		return empRankList;
	}
	
	public List<EmployeeDTO> salaryRequest() {
		String jpql = "SELECT e FROM EmployeeDTO e ORDER BY e.entDate";	
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

	public List<EmployeeDTO> severancePayRequest() {
		String jpql = "SELECT e FROM EmployeeDTO as e";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();
		
		for (EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		return empList;

	}

	public void addNewDept(DepartmentDTO newDept) {
		em.persist(newDept);
	}

	public DepartmentDTO findDeptByCode(int code) {
		return em.find(DepartmentDTO.class, code);
	}

	public void modifyDept(DepartmentDTO dept) {
		DepartmentDTO selectedDept = em.find(DepartmentDTO.class, dept.getCode());
		selectedDept.setYn(dept.getYn());
	}

	public EmpRankDTO findRankByCode(int code) {
		return em.find(EmpRankDTO.class, code);
	}

	public void modifyRank(EmpRankDTO rank) {
		EmpRankDTO selectedRank = em.find(EmpRankDTO.class, rank.getCode());
		selectedRank.setYn(rank.getYn());
	}
	
    
}
