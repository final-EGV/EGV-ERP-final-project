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

	/* 기안서 세부내용 조회 */
	@Transactional
	public SignDTO selectSavedSign(int signCode) {
		SignDTO savedSignDTO = signUpdateDAO.selectSavedSign(signCode);
		
		return savedSignDTO;
	}

	/* 기안서의 결재자 조회 */
	@Transactional
	public List<ApproverDTO> selectSignApproverList(int signCode) {
		List<ApproverDTO> approverList = signUpdateDAO.selectSignApproverList(signCode);
		
		return approverList;
	}

	/* 기안서의 참조자 조회 */
	@Transactional
	public List<RefferrerDTO> selectSignRefferrerList(int signCode) {
		List<RefferrerDTO> refferrerList = signUpdateDAO.selectSignRefferrerList(signCode);
		
		return refferrerList;
	}

	/* 기안서 수정 */
	@Transactional
	public void updateSign(SignDTO updateSign) {
		
		signUpdateDAO.updateSign(updateSign);
	}

	/* 기존 기안서 결재자 정보 삭제 */
	@Transactional
	public void deleteSignApprover(int signCode) {
		
		signUpdateDAO.deleteSignApprover(signCode);
	}

	/* 기존 기안서 참조자 정보 삭제 */
	@Transactional
	public void deleteSignRefferrer(int signCode) {

		signUpdateDAO.deleteSignRefferrer(signCode);
	}

	/* 임시저장된 기안서 삭제 */
	@Transactional
	public void deleteSign(int signCode) {
		
		signUpdateDAO.deleteSign(signCode);
	}


	

}
