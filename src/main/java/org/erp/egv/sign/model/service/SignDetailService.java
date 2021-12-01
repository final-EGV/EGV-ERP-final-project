package org.erp.egv.sign.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.sign.model.dao.SignDetailDAO;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignCommentDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Service;

@Service
public class SignDetailService {
	
	private SignDetailDAO signDAO;
	
	public SignDetailService(SignDetailDAO signDAO) {
		this.signDAO = signDAO;
	}
	
	@Transactional
	public SignDTO selectSign(int code) {
		return signDAO.selectSign(code);
	}

	@Transactional
	public void cancleSign(int code) {
		signDAO.cancleSign(code);
	}

	@Transactional
	public void approvalSign(int signCode, String empCode) {
		List<ApproverDTO> appList = signDAO.approvalSign(signCode, empCode);
		for (ApproverDTO app : appList) {
			if(app.getEmp().getCode().equals(empCode)) {
				if(appList.size() == app.getOrder()) {
					signDAO.doneSign(signCode);
				}
				break;
			}
		}
	}

	@Transactional
	public void returnSign(int signCode, String empCode) {
		signDAO.returnSign(signCode, empCode);
	}
	
	@Transactional
	public void insertComment(SignCommentDTO signComment) {
		signDAO.insertComment(signComment);
	}

	@Transactional
	public void deleteComment(int code) {
		signDAO.deleteComment(code);
		
	}

}
