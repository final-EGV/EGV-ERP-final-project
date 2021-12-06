package org.erp.egv.commu.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.commu.model.dto.BlindPostDTO;
import org.erp.egv.commu.model.dto.CmtDTO;
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

	public void insertBlindPost(BlindPostDTO blindPost) {
		em.persist(blindPost);
	}

	public void insertPostCmt(CmtDTO postCmt) {
		em.persist(postCmt);
	}

	public void deleteCmt(int code) {
		em.remove(em.find(CmtDTO.class, code));
	}

	public void postDelete(int code) {
		em.remove(em.find(BlindPostDTO.class, code));
	}

	public void updateBlindPost(int code, String title, String content) {
		BlindPostDTO post = em.find(BlindPostDTO.class, code);
		post.setTitle(title);
		post.setContent(content);
	}
	
	
	

}
