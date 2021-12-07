package org.erp.egv.official.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.official.model.dto.OfficialDTO;
import org.springframework.stereotype.Repository;

@Repository
public class OfficialDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<OfficialDTO> officialListRequest() {
		String jpql = "SELECT a FROM OfficialDTO AS a";
		List<OfficialDTO> officialList = em.createQuery(jpql, OfficialDTO.class).getResultList();
		return officialList;
	}

	public void officialContentWrite(OfficialDTO officialDTO) {
		em.persist(officialDTO);
	}

	public OfficialDTO officialDetailRequest(int code) {
		OfficialDTO official = em.find(OfficialDTO.class, code);
		return official;
	}

	public int findNextCode() {
		String jpql = "SELECT nvl(MAX(a.code), 0) + 1 FROM OfficialDTO AS a";
		int nextCode = em.createQuery(jpql, Integer.class).getSingleResult();
		return nextCode;
	}


}
