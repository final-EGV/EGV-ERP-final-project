package org.erp.egv.sign.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.sign.model.dao.SignInsertDAO;
import org.erp.egv.sign.model.dto.TemplateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInsertService {
	
private SignInsertDAO signInsertDAO;
	
	@Autowired
	public SignInsertService(SignInsertDAO signInsertDAO) {
		this.signInsertDAO = signInsertDAO;
	}

	@Transactional
	public List<TemplateDTO> selectTempList() {
		return signInsertDAO.selectTempList();
	}

	@Transactional
	public TemplateDTO findTemplateByCode(int code) {
		
		TemplateDTO templateDTO = signInsertDAO.findTemplateByCode(code);
		
		return templateDTO;
	}

}
