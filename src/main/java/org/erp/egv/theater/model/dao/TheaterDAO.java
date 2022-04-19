package org.erp.egv.theater.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.entity.Theater;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterDAO {
	
	@PersistenceContext
	private EntityManager em;

	public Theater inquireSingleTheaterByCode(int theaterCode) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
		
		return em.find(Theater.class, theaterCode);
	}

}
