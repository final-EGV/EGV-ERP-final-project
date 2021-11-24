package org.erp.egv.sign.model.service;

import java.util.List;

import org.erp.egv.sign.model.dao.SignInsertDAO;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInsertService {
	
private SignInsertDAO signDAO;
	
	@Autowired
	public SignInsertService(SignInsertDAO signDAO) {
		this.signDAO = signDAO;
	}

	public List<TemplateDTO> selectTempList() {
		System.out.println("test1");
		return signDAO.selectTempList();
	}

}
