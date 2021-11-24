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
		
		String jpql = "SELECT a FROM TemplateDTO as a";
		List<TemplateDTO> tempList = em.createQuery(jpql, TemplateDTO.class).getResultList();
		
		return tempList;
	}

}
