package org.erp.egv.employee.model.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.erp.egv.employee.model.dto.DepartmentDTO;
import org.erp.egv.employee.model.dto.EmpRankDTO;
import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.erp.egv.employee.model.dto.EmployeeRoleDTO;
import org.springframework.stereotype.Repository;

@Repository
public class EmpInfoDAO {
	
	@PersistenceContext	
	private EntityManager em;
	
	public List<EmployeeDTO> empListRequest() {
		String jpql = "SELECT e FROM EmployeeDTO as e";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();

		for (EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		
		return empList;

	}
	
	public EmployeeDTO empOneRequest(String empCode) {
		EmployeeDTO emp = em.find(EmployeeDTO.class, empCode);
		
		System.out.println(emp);
		
		return emp;
	}
	
	public List<DepartmentDTO> findDepartmentList() {
		String jpql = "SELECT a FROM DepartmentDTO as a ORDER BY a.code ASC";
		
		TypedQuery<DepartmentDTO> query = em.createQuery(jpql, DepartmentDTO.class);
		List<DepartmentDTO> departmentList = query.getResultList();
		
		return departmentList;
	}

	/* Date : 2021/11/24
	 * Writer : Hansoo Lee
	 * 
	 * 사번 조합기 다음 사번 확인하기
	 * 
	 * jpql로 그전 MAX넘버를 조건식에 따라 가지고 온뒤 잘라서 이어붙여 식별코드로 사용할 것이다.
	 * 자료형이 varchar2이지만 쿼리문에서 + 1 을 해주어 number형으로 바뀌었으므로 Integer.class를 사용해야 오류가 나지 않는다
	 * 하지만 정작 DTO에서는 식별코드를 String자료형을 사용해야하므로 다시 String으로 바꿔 주어 사용해야 한다. 
	 * 사원의 식별코드는 "해당 년도 + (MAX넘버 + 1)"을 이어붙여 사용해야 하므로, int 자료형을 변형하여 반환, 사용할 것이다. 
	 * */
	public int findNextEmpNum() {
		String jpql = "SELECT nvl(MAX(a.code), 1000100) + 1 FROM EmployeeDTO a WHERE a.code LIKE TO_CHAR(SYSDATE,'YYYY') ||'%'";
		int preNextEmpNum = em.createQuery(jpql, Integer.class).getSingleResult();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		int nextEmpNum = year*1000 + preNextEmpNum%1000; 
		
		return nextEmpNum;
	}
	
	/*사원 등록*/
	public void empRegistRequest(EmployeeDTO newEmp) {
		em.persist(newEmp);
	}
	
	/* Date : 2021/11/25
	 * Writer : Hansoo Lee
	 * 
	 * jpa는 update 쿼리가 없는 대신에 영속성 컨텍스트의 변화를 인식하여
	 * 변화가 있을시 트랙젝션이 끝났을때 업데이트를 시작한다.
	 * 그러므로, em.find로 영속성 컨택스트를 불러오고 
	 * setter를 통하여 영속성 컨택스트에 변화를 주면 update가 된다.
	 * 
	 * 그냥 엔티티를 덮어 써 보았으나. update 쿼리가 발생하지 않을걸 보아서는
	 * 변화로 인식하지 못하는듯 하다.
	 * 
	 * 사원 정보수정*/
	public void modifyInforRequest(EmployeeDTO modifyInfor) {
		EmployeeDTO selectedEmp = em.find(EmployeeDTO.class, modifyInfor.getCode());
		
		selectedEmp.setCareerYN(modifyInfor.getCareerYN());
		selectedEmp.setEmail(modifyInfor.getEmail());
		selectedEmp.setEmpPosition(modifyInfor.getEmpPosition());
		selectedEmp.setPhoneHome(modifyInfor.getPhoneHome());
		selectedEmp.setPhoneMobile(modifyInfor.getPhoneMobile());
		selectedEmp.setAddress(modifyInfor.getAddress());
		selectedEmp.setNote(modifyInfor.getNote());
		
		selectedEmp.setRank(modifyInfor.getRank());
		selectedEmp.setDept(modifyInfor.getDept());

		System.out.println(selectedEmp);

	}
	
	/* 퇴사사유와 퇴사신청 날짜 입력 */
	public void empOUTRequest(String code, String reason) {
		EmployeeDTO selectedEmp = em.find(EmployeeDTO.class, code);
		
		LocalDate currentDate = LocalDate.now(); 
		Date sqlDate = Date.valueOf( currentDate );

		selectedEmp.setOutDate(sqlDate);
		selectedEmp.setOutReason(reason);
		
	}
	
	
	
	public List<EmpRankDTO> findEmpRankList() {
		String jpql = "SELECT a FROM EmpRankDTO as a ORDER BY a.code ASC";
		
		TypedQuery<EmpRankDTO> query = em.createQuery(jpql, EmpRankDTO.class);
		List<EmpRankDTO> empRankList = query.getResultList();
		
		return empRankList;
	}
	
	public List<EmployeeDTO> salaryRequest() {
		String jpql = "SELECT e FROM EmployeeDTO e ORDER BY e.entDate";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();
		
		for (EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		return empList;

	}
	
	public EmployeeDTO findMemberById(String name) {
		EmployeeDTO emp = em.find(EmployeeDTO.class, name);
		
		String jpql = "SELECT a FROM EmployeeRoleDTO as a WHERE a.employee.code = :code";
		emp.setEmployeeRoleList(em.createQuery(jpql, EmployeeRoleDTO.class).setParameter("code", emp.getCode()).getResultList());
		
		return emp;
	}

	public EmployeeDTO finId(String email) throws javax.persistence.NoResultException {
		String jpql = "SELECT a FROM EmployeeDTO as a WHERE a.email = :email";
		
		EmployeeDTO emp = null;
		
		try{
			emp = em.createQuery(jpql, EmployeeDTO.class).setParameter("email", email).getSingleResult();
		}catch (NoResultException e){
			
		}
		
		return emp;
	}


	public List<DepartmentDTO> empDeptList() {
		String jpql = "SELECT d FROM DepartmentDTO as d";
		TypedQuery<DepartmentDTO> query = em.createQuery(jpql, DepartmentDTO.class);
		List<DepartmentDTO> deptList = query.getResultList();
		
		return deptList;
	}

	public List<EmpRankDTO> empRankList() {
		String jpql = "SELECT r FROM EmpRankDTO as r";
		TypedQuery<EmpRankDTO> query = em.createQuery(jpql, EmpRankDTO.class);
		List<EmpRankDTO> rankList = query.getResultList();
		
		for (EmpRankDTO rank : rankList) {
			System.out.println(rank);
		}
		
		return rankList;
	}
	
	public EmployeeDTO findEmpByCode(String code) throws javax.persistence.NoResultException {
		EmployeeDTO emp = em.find(EmployeeDTO.class, code);
		
		return emp;
	}

	@Transactional
	public EmployeeDTO resetPw(String code, String repw) throws javax.persistence.NoResultException {
		EmployeeDTO emp = em.find(EmployeeDTO.class, code);
		emp.setPwd(repw);
		return emp;
		
	}

	public List<EmployeeDTO> severancePayRequest() {
		String jpql = "SELECT e FROM EmployeeDTO as e";	
		TypedQuery<EmployeeDTO> query = em.createQuery(jpql, EmployeeDTO.class);
		List<EmployeeDTO> empList = query.getResultList();
		
		for (EmployeeDTO emp : empList) {
			System.out.println(emp);
		}
		return empList;

	}

	public void addNewDept(DepartmentDTO newDept) {
		em.persist(newDept);
	}

	public DepartmentDTO findDeptByCode(int code) {
		return em.find(DepartmentDTO.class, code);
	}

	public void modifyDept(DepartmentDTO dept) {
		DepartmentDTO selectedDept = em.find(DepartmentDTO.class, dept.getCode());
		selectedDept.setYn(dept.getYn());
	}

	public EmpRankDTO findRankByCode(int code) {
		return em.find(EmpRankDTO.class, code);
	}

	public void modifyRank(EmpRankDTO rank) {
		EmpRankDTO selectedRank = em.find(EmpRankDTO.class, rank.getCode());
		selectedRank.setYn(rank.getYn());
	}




	


    
}
