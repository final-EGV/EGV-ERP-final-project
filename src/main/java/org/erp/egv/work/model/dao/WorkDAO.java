package org.erp.egv.work.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.erp.egv.work.model.dto.WorkDTO;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDAO {

	@PersistenceContext
	private EntityManager em;

	public List<WorkDTO> workList() {
		String jpql = "SELECT w FROM WorkDTO as w";
		TypedQuery<WorkDTO> query = em.createQuery(jpql, WorkDTO.class);
		List<WorkDTO> workList = query.getResultList();
		
		return workList;
	}
}
