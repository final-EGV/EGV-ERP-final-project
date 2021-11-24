package org.erp.egv.sign.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class SignInsertDAO {
	
	@PersistenceContext
	private EntityManager em;

}
