package org.erp.egv.theater.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.theater.model.dto.TheaterDTO;
import org.springframework.stereotype.Repository;

@Repository
public class TheaterDAO {
	
	@PersistenceContext
	private EntityManager em;

	public TheaterDTO inquireSingleTheaterByCode(int theaterCode) {
		
		System.out.println(Thread.currentThread().getStackTrace()[2].getClassName());
		
		return em.find(TheaterDTO.class, theaterCode);
	}

}
