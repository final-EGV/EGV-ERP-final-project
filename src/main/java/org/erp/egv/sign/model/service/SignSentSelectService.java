package org.erp.egv.sign.model.service;

import java.util.List;

import org.erp.egv.sign.model.dao.SignSentSelectDAO;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignSentSelectService {
	
	SignSentSelectDAO signDAO;
	
	@Autowired
	public SignSentSelectService(SignSentSelectDAO signDAO) {
		this.signDAO = signDAO;
	}

	public List<SignDTO> selectcompletedSignList(String code) {
		return signDAO.selectcompletedSignList(code);
	}

	public List<SignDTO> selectProgresssignSignList(String code) {
		return signDAO.selectProgresssignSignList(code);
	}

	public List<SignDTO> selectRejectsignSignList(String code) {
		return signDAO.selectRejectsignSignList(code);
	}

	public List<SignDTO> selectSaveSignList(String code) {
		return signDAO.selectSaveSignList(code);
	}

}
