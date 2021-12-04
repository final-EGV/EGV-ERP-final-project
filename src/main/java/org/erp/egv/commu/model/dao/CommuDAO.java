package org.erp.egv.commu.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.commu.model.dto.BlindPostDTO;
import org.springframework.stereotype.Repository;

@Repository
public class CommuDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<BlindPostDTO> selectBlindList() {
		String jpql = "SELECT a FROM BlindPostDTO AS a";
		List<BlindPostDTO> List = em.createQuery(jpql, BlindPostDTO.class).getResultList();
		return List;
	}

	public BlindPostDTO selectBlindPost(int code) {
		return em.find(BlindPostDTO.class, code);
	}
	
	
	

}
