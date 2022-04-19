package org.erp.egv.theater.model.service;

import javax.transaction.Transactional;

import org.erp.egv.theater.model.dao.TheaterDAO;
import org.erp.egv.theater.model.dto.TheaterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {

	private TheaterDAO theaterDAO;
	
	@Autowired
	public TheaterService(TheaterDAO theaterDAO) {
		this.theaterDAO = theaterDAO;
	}
	
	@Transactional
	public TheaterDTO inquireSingleTheaterByCode(int theaterCode) {
		
		System.out.println(new Throwable().getStackTrace()[0].getClassName() + "."
				+ new Throwable().getStackTrace()[0].getMethodName() + "is called");
				return theaterDAO.inquireSingleTheaterByCode(theaterCode).toDto();
	}

}
