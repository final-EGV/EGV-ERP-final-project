package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SignReceiveSelectDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<ApproverDTO> selectwatingsignList(String code) {
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.emp.code = :code AND a.sign.status='결재중'";
		List<ApproverDTO> tempList = em.createQuery(jpql, ApproverDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

	public List<ApproverDTO> selectwatingsignList2(int code) {
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.sign.code = :code ORDER BY a.order";
		List<ApproverDTO> tempList = em.createQuery(jpql, ApproverDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

	public List<ApproverDTO> selectSignHestory(String code) {
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.emp.code = :code AND a.sign.status='결재완료'";
		List<ApproverDTO> tempList = em.createQuery(jpql, ApproverDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

	public List<RefferrerDTO> selectReferenceSign(String code) {
		String jpql = "SELECT a FROM RefferrerDTO as a WHERE a.emp.code = :code AND a.sign.status IN ('결재중','결재완료')";
		List<RefferrerDTO> tempList = em.createQuery(jpql, RefferrerDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

}
