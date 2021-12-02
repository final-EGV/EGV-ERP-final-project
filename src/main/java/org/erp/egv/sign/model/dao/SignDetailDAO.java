package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignCommentDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SignDetailDAO {
	
	@PersistenceContext
	private EntityManager em;

	public SignDTO selectSign(int code) {
		return em.find(SignDTO.class, code);
	}

	public void cancleSign(int code) {
		SignDTO sign = em.find(SignDTO.class, code);
		sign.setStatus("임시저장");
	}

	public List<ApproverDTO> approvalSign(int signCode, String empCode) {
		
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.emp.code = :empcode AND a.sign.code = :signcode";
		ApproverDTO app = em.createQuery(jpql, ApproverDTO.class).setParameter("empcode", empCode).setParameter("signcode", signCode).getSingleResult();
		app.setStatus("승인");
		app.setDate(new java.sql.Date(System.currentTimeMillis()));
		
		String jpql2 = "SELECT a FROM ApproverDTO as a WHERE a.sign.code = :signcode";
		List<ApproverDTO> appList = em.createQuery(jpql2, ApproverDTO.class).setParameter("signcode", signCode).getResultList();
		
		return appList;
		
	}

	public void doneSign(int signCode) {
		SignDTO sign = em.find(SignDTO.class, signCode);
		sign.setStatus("결재완료");
		
	}

	public void returnSign(int signCode, String empCode) {
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.emp.code = :empcode AND a.sign.code = :signcode";
		ApproverDTO app = em.createQuery(jpql, ApproverDTO.class).setParameter("empcode", empCode).setParameter("signcode", signCode).getSingleResult();
		app.setStatus("반려");
		app.getSign().setStatus("반려");
		
	}

	public void insertComment(SignCommentDTO signComment) {
		em.persist(signComment);
	}

	public void deleteComment(int code) {
		em.remove(em.find(SignCommentDTO.class, code));
	}

}
