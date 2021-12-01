package org.erp.egv.work.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.work.model.dto.WorkDTO;
import org.erp.egv.work.model.dto.WorkTypeCategoryDTO;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDAO {

	@PersistenceContext
	private EntityManager em;

	public List<WorkDTO> workList() {
		String jpql = "SELECT w FROM WorkDTO as w";
		TypedQuery<WorkDTO> query = em.createQuery(jpql, WorkDTO.class);
		List<WorkDTO> workList = query.getResultList();
		
		for (WorkDTO work : workList) {
			System.out.println(work);
		}
		
		return workList;
	}

	public WorkDTO findWorkByCode(int code) {
		return em.find(WorkDTO.class, code);
	}

	public void modifyWorkOver(int code, int workOver) {
		WorkDTO selectedWork = em.find(WorkDTO.class, code);
		selectedWork.setWorkOver(workOver);
	}

	
	public List<WorkDTO> workListEmp(String code) {
		String jpql = "SELECT a FROM WorkDTO as a WHERE EMP_CODE is " + code;
		TypedQuery<WorkDTO> query = em.createQuery(jpql, WorkDTO.class);
		List<WorkDTO> workList = query.getResultList();
		
		return workList;
	}

	public EmployeeDTO findEmployee(String code) {
		EmployeeDTO employee = em.find(EmployeeDTO.class, code);
		
		return employee;
	}

	public WorkTypeCategoryDTO findWorkType(int workType) {
		WorkTypeCategoryDTO workTypeCategory = em.find(WorkTypeCategoryDTO.class, workType);
		
		return workTypeCategory;
	}

	public void addNewWork(WorkDTO newWork) {
		em.persist(newWork);
	}
}
