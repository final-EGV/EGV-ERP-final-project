package org.erp.egv.sign.model.service;

import java.util.List;

import javax.transaction.Transactional;

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

	@Transactional
	public List<SignDTO> selectcompletedSignList(String code) {
		return signDAO.selectcompletedSignList(code);
	}

	@Transactional
	public List<SignDTO> selectProgresssignSignList(String code) {
		return signDAO.selectProgresssignSignList(code);
	}

	@Transactional
	public List<SignDTO> selectRejectsignSignList(String code) {
		return signDAO.selectRejectsignSignList(code);
	}

	@Transactional
	public List<SignDTO> selectSaveSignList(String code) {
		return signDAO.selectSaveSignList(code);
	}

}
