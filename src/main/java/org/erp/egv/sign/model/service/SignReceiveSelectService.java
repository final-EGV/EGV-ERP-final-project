package org.erp.egv.sign.model.service;

import java.util.List;

import org.erp.egv.sign.model.dao.SignReceiveSelectDAO;
import org.erp.egv.sign.model.dto.ApproverDTO;
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
	
	public List<SignDTO> selectwatingsignList(String code) {
		return signDAO.selectwatingsignList(code);
	}

	public List<ApproverDTO> selectwatingsignList2(int code) {
		return signDAO.selectwatingsignList2(code);
	}

	public List<SignDTO> selectSignHestory(String code) {
		return signDAO.selectSignHestory(code);
	}

}
