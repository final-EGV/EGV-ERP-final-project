package org.erp.egv.sign.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.sign.model.dao.SignReceiveSelectDAO;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignReceiveSelectService {
	
	private SignReceiveSelectDAO signDAO;
	
	@Autowired
	public SignReceiveSelectService(SignReceiveSelectDAO signDAO) {
		this.signDAO = signDAO;
	}
	
	@Transactional
	public List<ApproverDTO> selectwatingsignList(String code) {
		return signDAO.selectwatingsignList(code);
	}

	@Transactional
	public List<ApproverDTO> selectwatingsignList2(int code) {
		return signDAO.selectwatingsignList2(code);
	}

	@Transactional
	public List<ApproverDTO> selectSignHestory(String code) {
		return signDAO.selectSignHestory(code);
	}

	@Transactional
	public List<RefferrerDTO> selectReferenceSign(String code) {
		return signDAO.selectReferenceSign(code);
	}

}
