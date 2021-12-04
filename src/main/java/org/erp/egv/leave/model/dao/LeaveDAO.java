package org.erp.egv.leave.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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


}
