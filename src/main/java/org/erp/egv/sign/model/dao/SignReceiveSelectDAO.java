package org.erp.egv.sign.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.erp.egv.sign.model.dto.ApproverDTO;
import org.erp.egv.sign.model.dto.SignDTO;
import org.springframework.stereotype.Repository;

@Repository
public class SignReceiveSelectDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<SignDTO> selectwatingsignList(String code) {
//		String jpql = "SELECT a FROM SignDTO as a WHERE a.approver.emp.code = :code AND a.status='결재중'";
//		List<SignDTO> tempList = em.createQuery(jpql, SignDTO.class).setParameter("code", code).getResultList();
		
//		String sql = "SELECT *\r\n"
//				+ "  FROM SIGN A\r\n"
//				+ "  RIGHT OUTER JOIN approver B ON A.sign_code = B.sign_code\r\n"
//				+ "  RIGHT OUTER JOIN employee C ON B.emp_code = C.emp_code\r\n"
//				+ "  WHERE b.emp_code = ?\r\n"
//				+ "  AND a.sign_status = '결재중'";
		
		String sql = "SELECT \r\n"
				+ "       A.SIGN_CODE\r\n"
				+ "     , A.SIGN_DATE\r\n"
				+ "     , A.SIGN_STATUS\r\n"
				+ "     , A.SIGN_TITLE\r\n"
				+ "     , A.FILE_CONTENTS\r\n"
				+ "     , A.TEMP_CODE\r\n"
				+ "     , A.EMP_CODE\r\n"
				+ "     , B.APPROVER_CODE\r\n"
				+ "     , B.EMP_CODE\r\n"
				+ "     , B.SIGN_CODE\r\n"
				+ "     , B.APPROVER_ORDER\r\n"
				+ "     , B.APPROVER_STATUS\r\n"
				+ "     , C.EMP_CODE\r\n"
				+ "     , C.EMP_NAME\r\n"
				+ "  FROM SIGN A\r\n"
				+ "  RIGHT OUTER JOIN APPROVER B ON A.SIGN_CODE = B.SIGN_CODE\r\n"
				+ "  RIGHT OUTER JOIN EMPLOYEE C ON B.EMP_CODE = C.EMP_CODE\r\n"
				+ "  WHERE B.EMP_CODE = ?\r\n"
				+ "  AND A.SIGN_STATUS = '결재중'";
		List<SignDTO> tempList = em.createNativeQuery(sql, SignDTO.class).setParameter(1, code).getResultList();
		
		return tempList;
	}

	public List<ApproverDTO> selectwatingsignList2(int code) {
		String jpql = "SELECT a FROM ApproverDTO as a WHERE a.sign.code = :code ORDER BY a.order";
		List<ApproverDTO> tempList = em.createQuery(jpql, ApproverDTO.class).setParameter("code", code).getResultList();
		
		return tempList;
	}

	public List<SignDTO> selectSignHestory(String code) {
		String sql = "SELECT \r\n"
				+ "       A.SIGN_CODE\r\n"
				+ "     , A.SIGN_DATE\r\n"
				+ "     , A.SIGN_STATUS\r\n"
				+ "     , A.SIGN_TITLE\r\n"
				+ "     , A.FILE_CONTENTS\r\n"
				+ "     , A.TEMP_CODE\r\n"
				+ "     , A.EMP_CODE\r\n"
				+ "     , B.APPROVER_CODE\r\n"
				+ "     , B.EMP_CODE\r\n"
				+ "     , B.SIGN_CODE\r\n"
				+ "     , B.APPROVER_ORDER\r\n"
				+ "     , B.APPROVER_STATUS\r\n"
				+ "     , C.EMP_CODE\r\n"
				+ "     , C.EMP_NAME\r\n"
				+ "  FROM SIGN A\r\n"
				+ "  RIGHT OUTER JOIN APPROVER B ON A.SIGN_CODE = B.SIGN_CODE\r\n"
				+ "  RIGHT OUTER JOIN EMPLOYEE C ON B.EMP_CODE = C.EMP_CODE\r\n"
				+ "  WHERE B.EMP_CODE = ?\r\n"
				+ "  AND A.SIGN_STATUS = '결재완료'";
		List<SignDTO> signList = em.createNativeQuery(sql, SignDTO.class).setParameter(1, code).getResultList();
		return signList;
	}

}
