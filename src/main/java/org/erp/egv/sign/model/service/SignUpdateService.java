package org.erp.egv.sign.model.service;

import javax.transaction.Transactional;

import org.erp.egv.sign.model.dao.SignUpdateDAO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Service;

@Service
public class SignUpdateService {
	
	private SignUpdateDAO signUpdateDAO;
	
	public SignUpdateService(SignUpdateDAO signUpdateDAO) {
		this.signUpdateDAO = signUpdateDAO;
	}

	@Transactional
	public SignDTO selectSavedSign(int signCode) {
		// TODO Auto-generated method stub
		return null;
	}

}
