package org.erp.egv.leave.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class LeaveDAO {

	@PersistenceContext
	private EntityManager em;
}
