package org.erp.egv.employee.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.erp.egv.employee.model.dao.EmpInfoDAO;
import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpInfoService {

	private EmpInfoDAO empInfoDAO;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public EmpInfoService(EmpInfoDAO empInfoDAO, PasswordEncoder passwordEncoder) {
		this.empInfoDAO = empInfoDAO;
		this.passwordEncoder = passwordEncoder;
	}

	public List<EmployeeDTO> empListRequest() {
		System.out.println("서비스로 오나요?");
		return empInfoDAO.empListRequest();
	}
	
	public List<EmployeeDTO> empOutListRequest() {
		return empInfoDAO.empOutListRequest();
	}

	
	public EmployeeDTO empOneRequest(String empCode) {
		return empInfoDAO.empOneRequest(empCode);
	}

	/* Date : 2021/11/24
	 * Writer : Hansoo Lee
	 * 사원코드 설정 전용 메소드
	 * 
	 * EmployeeDTO 엔티티의 식별 코드는 String 형이므로 String형으로 파싱하여 사용하자.
	 * */
	public String findNextEmpNum() {
		System.out.println("서비스로 오나요?");
		System.out.println(empInfoDAO.findNextEmpNum());
		
		return String.valueOf(empInfoDAO.findNextEmpNum());
	}
	
	/* Date : 2021/11/25
	 * Writer : Hansoo Lee
	 * 
	 *  사원 등록시 비밀번호 암호화를 위해 getPwd르 비밀번호를 빼오고 암호화 한 뒤
	 *  setPwd로 다시 넣어주고 DB에 저장한다.
	 * */
	@Transactional
	public void empRegistRequest(EmployeeDTO newEmp) {
		String newEmpPwd = newEmp.getPwd();
		System.out.println(newEmpPwd);
		newEmp.setPwd(passwordEncoder.encode(newEmpPwd));
		
		empInfoDAO.empRegistRequest(newEmp);
	}
	
	/* 사원정보 수정 */
	@Transactional
	public void modifyInforRequest(EmployeeDTO modifyInfor) {
		empInfoDAO.modifyInforRequest(modifyInfor);
	}
	
	/* 퇴사사유와 퇴사신청 날짜 입력 */
	@Transactional
	public void empOUTRequest(String code, String reason) {
		empInfoDAO.empOUTRequest(code, reason);
	}
	
	/* 부서목록 리스트 */
	@Transactional
	public List<DepartmentDTO> findDepartmentList() {
		return empInfoDAO.findDepartmentList();
	}

	/* 부서 등록 */
	@Transactional
	public void addNewDept(DepartmentDTO newDept) {
		empInfoDAO.addNewDept(newDept);
	}
	
	/* 부서 상세 확인 */
	@Transactional
	public DepartmentDTO findDeptByCode(int code) {
		return empInfoDAO.findDeptByCode(code);
	}
	
	/* 부서 활성화/비활성화 */
	@Transactional
	public void modifyDept(DepartmentDTO dept) {
		empInfoDAO.modifyDept(dept);
	}
	
	/* 직위/직급 리스트 */
	@Transactional
	public List<EmpRankDTO> findEmpRankList() {
		return empInfoDAO.findEmpRankList();
	}
	
	/* 직위/직급 상세 확인 */
	@Transactional
	public EmpRankDTO findRankByCode(int code) {
		return empInfoDAO.findRankByCode(code);
	}
	
	/* 직위/직급 활성화/비활성화 */
	@Transactional
	public void modifyRank(EmpRankDTO rank) {
		empInfoDAO.modifyRank(rank);
	}
	
	
	public List<EmployeeDTO> salaryRequest() {
		return empInfoDAO.salaryRequest();
	}
	
	public List<DepartmentDTO> empDeptList() {
		return empInfoDAO.empDeptList();
	}
	
	public List<EmpRankDTO> empRankList() {
		return empInfoDAO.empRankList();
	}

	@Transactional
	public EmployeeDTO finId(String email) {
		return empInfoDAO.finId(email);
	}
    
	public List<EmployeeDTO> severancePayRequest() {
		return empInfoDAO.severancePayRequest();
	}

	@Transactional
	public EmployeeDTO resetPw(String code, String repw) {
		return empInfoDAO.resetPw(code, passwordEncoder.encode(repw));
	}

}
