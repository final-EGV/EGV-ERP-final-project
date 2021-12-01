package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpdateDAO {
	
	@PersistenceContext
	private EntityManager em;

	public SignDTO selectSavedSign(int signCode) {
		SignDTO savedSignDTO = em.find(SignDTO.class, signCode);
		
		return savedSignDTO;
	}

	public List<ApproverDTO> selectSignApproverList(int signCode) {
		
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.sign.code = :signCode ORDER BY a.code";
		List<ApproverDTO> approverList = em.createQuery(jpql, ApproverDTO.class).setParameter("signCode", signCode).getResultList();
		
		return approverList;
	}

	public List<RefferrerDTO> selectSignRefferrerList(int signCode) {
		
		String jpql = "SELECT r FROM RefferrerDTO as r WHERE r.sign.code = :signCode ORDER BY r.code";
		List<RefferrerDTO> referrerList = em.createQuery(jpql, RefferrerDTO.class).setParameter("signCode", signCode).getResultList();
		
		return referrerList;
	}


}
