package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SignInsertDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<TemplateDTO> selectTempList() {
		
		String jpql = "SELECT a FROM TemplateDTO as a ORDER BY a.code";
		List<TemplateDTO> tempList = em.createQuery(jpql, TemplateDTO.class).getResultList();
		
		return tempList;
	}

	public TemplateDTO findTemplateByCode(int code) {
		
		TemplateDTO templateDTO = em.find(TemplateDTO.class, code);
		
		return templateDTO;
	}

	public int findMaxSignCode() {
		String jpql = "SELECT MAX(s.code) FROM SignDTO s";
		int maxSignCode = em.createQuery(jpql, Integer.class).getSingleResult();

		return maxSignCode;
	}

	public void insertSign(SignDTO newSignDTO) {

		em.persist(newSignDTO);
	}
	
	public int findMaxApproverCode() {
		String jpql = "SELECT MAX(a.code) FROM ApproverDTO a";
		int maxApproverCode = em.createQuery(jpql, Integer.class).getSingleResult();
		
		return maxApproverCode;
	}

	public void insertApprover(ApproverDTO approverDTO) {

		em.persist(approverDTO);
	}
	
	public int findMaxReferrerCode() {
		String jpql = "SELECT MAX(r.code) FROM RefferrerDTO r";
		int maxReferrerCode = em.createQuery(jpql, Integer.class).getSingleResult();
		
		return maxReferrerCode;
	}

	public void insertReferrer(RefferrerDTO refferrerDTO) {

		em.persist(refferrerDTO);
	}

}
