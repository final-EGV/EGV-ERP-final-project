package org.erp.egv.sign.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.erp.egv.sign.model.dao.SignInsertDAO;
import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.RefferrerDTO;
import org.erp.egv.sign.model.dto.SignDTO;
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

	/* 템플릿 전체 조회 */
	@Transactional
	public List<TemplateDTO> selectTempList() {
		return signInsertDAO.selectTempList();
	}

	/* 템플릿 코드에 해당하는 템플릿 조회 */
	@Transactional
	public TemplateDTO findTemplateByCode(int code) {
		
		TemplateDTO templateDTO = signInsertDAO.findTemplateByCode(code);
		
		return templateDTO;
	}

	/* 기안서 중 가장 큰 SIGN_CODE 조회 */
	@Transactional
	public int findMaxSignCode() {
		int maxSignCode = signInsertDAO.findMaxSignCode();
		
		return maxSignCode;
	}

	/* 신규 기안서 추가 */
	@Transactional
	public void insertSign(SignDTO newSignDTO) {

		signInsertDAO.insertSign(newSignDTO);
	}
	
	/* 가장 큰 APPROVER_CODE 조회 */
	@Transactional
	public int findMaxApproverCode() {
		int maxApproverCode = signInsertDAO.findMaxApproverCode();
		
		return maxApproverCode;
	}

	/* 신규 기안서의 결재자 추가 */
	@Transactional
	public void insertApprover(ApproverDTO approverDTO) {
		
		signInsertDAO.insertApprover(approverDTO);
	}
	
	/* 가장 큰 REFFERRER_CODE 조회 */
	@Transactional
	public int findMaxReferrerCode() {
		int maxReferrerCode = signInsertDAO.findMaxReferrerCode();
		
		return maxReferrerCode;
	}

	/* 신규 기안서의 참조자 추가 */
	@Transactional
	public void insertReferrer(RefferrerDTO refferrerDTO) {
		
		signInsertDAO.insertReferrer(refferrerDTO);
	}

}
