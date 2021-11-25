package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SignSentSelectDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<SignDTO> selectcompletedSignList(String code) {
		String jpql = "SELECT a FROM SignDTO as a WHERE a.employee.code = :code AND a.status='결재완료'";
		List<SignDTO> tempList = em.createQuery(jpql, SignDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

	public List<SignDTO> selectProgresssignSignList(String code) {
		String jpql = "SELECT a FROM SignDTO as a WHERE a.employee.code = :code AND a.status='결재중'";
		List<SignDTO> tempList = em.createQuery(jpql, SignDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
		}

	public List<SignDTO> selectRejectsignSignList(String code) {
		String jpql = "SELECT a FROM SignDTO as a WHERE a.employee.code = :code AND a.status='반려'";
		List<SignDTO> tempList = em.createQuery(jpql, SignDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

	public List<SignDTO> selectSaveSignList(String code) {
		String jpql = "SELECT a FROM SignDTO as a WHERE a.employee.code = :code AND a.status='임시저장'";
		List<SignDTO> tempList = em.createQuery(jpql, SignDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

}
