package org.erp.egv.sign.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.sign.model.dao.SignUpdateDAO;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpdateService {
	
	private SignUpdateDAO signUpdateDAO;
	
	@Autowired
	public SignUpdateService(SignUpdateDAO signUpdateDAO) {
		this.signUpdateDAO = signUpdateDAO;
	}

	@Transactional
	public SignDTO selectSavedSign(int signCode) {
		SignDTO savedSignDTO = signUpdateDAO.selectSavedSign(signCode);
		
		return savedSignDTO;
	}

	@Transactional
	public List<ApproverDTO> selectSignApproverList(int signCode) {
		List<ApproverDTO> approverList = signUpdateDAO.selectSignApproverList(signCode);
		
		return approverList;
	}

	@Transactional
	public List<RefferrerDTO> selectSignRefferrerList(int signCode) {
		List<RefferrerDTO> refferrerList = signUpdateDAO.selectSignRefferrerList(signCode);
		
		return refferrerList;
	}


	

}
