package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
