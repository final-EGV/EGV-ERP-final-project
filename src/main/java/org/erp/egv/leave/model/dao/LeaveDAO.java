package org.erp.egv.leave.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.ParttimeScheduleDTO;
import org.erp.egv.leave.model.dto.AnnualLeaveCategoryDTO;
import org.erp.egv.leave.model.dto.AnnualLeaveDTO;
import org.erp.egv.leave.model.dto.UseAnnualLeaveDTO;
import org.springframework.stereotype.Repository;

@Repository
public class LeaveDAO {

	@PersistenceContext
	private EntityManager em;

	public List<AnnualLeaveDTO> EmpLeaveList() {
		String jpql = "SELECT a FROM AnnualLeaveDTO as a";
		TypedQuery<AnnualLeaveDTO> query = em.createQuery(jpql, AnnualLeaveDTO.class);
		List<AnnualLeaveDTO> leaveList = query.getResultList();

		for (AnnualLeaveDTO leave : leaveList) {
			System.out.println(leave);
		}
				
		return leaveList;
	}

	public List<UseAnnualLeaveDTO> EmpUsedLeave() {
		String jpql = "SELECT u FROM UseAnnualLeaveDTO as u";
		TypedQuery<UseAnnualLeaveDTO> query = em.createQuery(jpql, UseAnnualLeaveDTO.class);
		List<UseAnnualLeaveDTO> usedLeaveList = query.getResultList();
		
		for (UseAnnualLeaveDTO usedLeave : usedLeaveList) {
			System.out.println(usedLeave);
		}
		
		return usedLeaveList;
	}

	public List<UseAnnualLeaveDTO> EmpUsedLeaveList(String code) {
		String jpql = "SELECT u FROM UseAnnualLeaveDTO as u WHERE EMP_CODE is " + code;
		TypedQuery<UseAnnualLeaveDTO> query = em.createQuery(jpql, UseAnnualLeaveDTO.class);
		List<UseAnnualLeaveDTO> usedLeaveList = query.getResultList();
		
		return usedLeaveList;
	}

	public EmployeeDTO findEmp(String code) {
		EmployeeDTO emp = em.find(EmployeeDTO.class, code);
		
		return emp;
	}

	public void addLeave(UseAnnualLeaveDTO leave) {
		em.persist(leave);
	}

	public AnnualLeaveCategoryDTO findCategory(int code) {
		AnnualLeaveCategoryDTO category = em.find(AnnualLeaveCategoryDTO.class, code);
		
		return category;
	}

	public void modifyLeave(String empCode, int total) {
		String jpql = "SELECT l FROM AnnualLeaveDTO as l WHERE EMP_CODE is " + empCode;
		TypedQuery<AnnualLeaveDTO> query = em.createQuery(jpql, AnnualLeaveDTO.class);
		AnnualLeaveDTO leave = query.getSingleResult();
		
		leave.setUseCount(leave.getUseCount() + total);
	}

	public AnnualLeaveDTO selectSingleLeave(String code) {
		String jpql = "SELECT l FROM AnnualLeaveDTO as l WHERE EMP_CODE is " + code;
		TypedQuery<AnnualLeaveDTO> query = em.createQuery(jpql, AnnualLeaveDTO.class);
		AnnualLeaveDTO leave = query.getSingleResult();
		
		return leave;
	}
	
	/* 연차 조회 */
	public List<UseAnnualLeaveDTO> findUseAnnualLeaveList() {
		String jpql = "SELECT u FROM UseAnnualLeaveDTO as u WHERE u.start IS NOT NULL";
		
		TypedQuery<UseAnnualLeaveDTO> query = em.createQuery(jpql, UseAnnualLeaveDTO.class);
		List<UseAnnualLeaveDTO> useAnnualLeaveList = query.getResultList();
		System.out.println(useAnnualLeaveList);
		
		return useAnnualLeaveList;
	}

}
