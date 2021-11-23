package org.erp.egv.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = -3593135330384171300L;
	
	@Id
	@Column(name = "EMP_CODE")
	private String code;
	
	@Column(name = "PWD")
	private String pwd;
	
	@Column(name = "EMP_NAME")
	private String name;
	
	@Column(name = "ENG_NAME")
	private String engName;
	
	@Column(name = "RRN")
	private String rrn;
	
	@Column(name = "ENT_DATE")
	private java.sql.Date entDate;
	
	@Column(name = "CAREER_YN")
	private String careerYN;
	
	@Column(name = "EMP_POSITION")
	private String empPosition;
	
	@Column(name = "PHONE_HOME")
	private String phoneHome;
	
	@Column(name = "PHONE_MOBILE")
	private String phoneMobile;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "ACCOUNT_BANK")
	private String accountBank;
	
	@Column(name = "ACCOUNT_NUM")
	private String accountNum;
	
	@Column(name = "ACCOUNT_HOLDER")
	private String accountHolder;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "OUT_YN")
	private String outYN;
	
	@Column(name = "OUT_DATE")
	private String outDATE;
	
	@Column(name = "OUT_REASON")
	private String outReason;
	
	@Column(name = "NOTE")
	private String note;
	
	@Column(name = "PROFILE_ORIG_NAME")
	private String profileOrigName;
	
	@Column(name = "PROFILE_UUID_NAME")
	private String profileUuidName;
	
	@Column(name = "PROFILE_IMG_PATH")
	private String profileImgName;
	
	@Column(name = "STAMP_ORIG_NAME")
	private String stampOrigName;
	
	@Column(name = "STAMP_UUID_NAME")
	private String stampUuidName;
	
	@Column(name = "STAMP_IMG_PATH")
	private String stampImgPath;

	@ManyToOne
	@JoinColumn(name = "DEPT_CODE")
	private DepartmentDTO dept;	
	
	@ManyToOne
	@JoinColumn(name = "RANK_CODE")
	private EmpRankDTO rank;	
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private List<EmployeeRoleDTO> employeeRoleList = new ArrayList<>();		// 회원별권한리스트(nullpointexceprion 방지를 위해 객체 선언)
	
	public EmployeeDTO() {
	}

	public EmployeeDTO(String code, String pwd, String name, String engName, String rrn, Date entDate, String careerYN,
			String empPosition, String phoneHome, String phoneMobile, String email, String accountBank,
			String accountNum, String accountHolder, String address, String outYN, String outDATE, String outReason,
			String note, String profileOrigName, String profileUuidName, String profileImgName, String stampOrigName,
			String stampUuidName, String stampImgPath, List<EmployeeRoleDTO> employeeRoleList, DepartmentDTO dept, EmpRankDTO rank) {
		this.code = code;
		this.pwd = pwd;
		this.name = name;
		this.engName = engName;
		this.rrn = rrn;
		this.entDate = entDate;
		this.careerYN = careerYN;
		this.empPosition = empPosition;
		this.phoneHome = phoneHome;
		this.phoneMobile = phoneMobile;
		this.email = email;
		this.accountBank = accountBank;
		this.accountNum = accountNum;
		this.accountHolder = accountHolder;
		this.address = address;
		this.outYN = outYN;
		this.outDATE = outDATE;
		this.outReason = outReason;
		this.note = note;
		this.profileOrigName = profileOrigName;
		this.profileUuidName = profileUuidName;
		this.profileImgName = profileImgName;
		this.stampOrigName = stampOrigName;
		this.stampUuidName = stampUuidName;
		this.stampImgPath = stampImgPath;
		this.employeeRoleList = employeeRoleList;
		this.dept = dept;
		this.rank = rank;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public java.sql.Date getEntDate() {
		return entDate;
	}

	public void setEntDate(java.sql.Date entDate) {
		this.entDate = entDate;
	}

	public String getCareerYN() {
		return careerYN;
	}

	public void setCareerYN(String careerYN) {
		this.careerYN = careerYN;
	}

	public String getEmpPosition() {
		return empPosition;
	}

	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}

	public String getPhoneHome() {
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome) {
		this.phoneHome = phoneHome;
	}

	public String getPhoneMobile() {
		return phoneMobile;
	}

	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOutYN() {
		return outYN;
	}

	public void setOutYN(String outYN) {
		this.outYN = outYN;
	}

	public String getOutDATE() {
		return outDATE;
	}

	public void setOutDATE(String outDATE) {
		this.outDATE = outDATE;
	}

	public String getOutReason() {
		return outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getProfileOrigName() {
		return profileOrigName;
	}

	public void setProfileOrigName(String profileOrigName) {
		this.profileOrigName = profileOrigName;
	}

	public String getProfileUuidName() {
		return profileUuidName;
	}

	public void setProfileUuidName(String profileUuidName) {
		this.profileUuidName = profileUuidName;
	}

	public String getProfileImgName() {
		return profileImgName;
	}

	public void setProfileImgName(String profileImgName) {
		this.profileImgName = profileImgName;
	}

	public String getStampOrigName() {
		return stampOrigName;
	}

	public void setStampOrigName(String stampOrigName) {
		this.stampOrigName = stampOrigName;
	}

	public String getStampUuidName() {
		return stampUuidName;
	}

	public void setStampUuidName(String stampUuidName) {
		this.stampUuidName = stampUuidName;
	}

	public String getStampImgPath() {
		return stampImgPath;
	}

	public void setStampImgPath(String stampImgPath) {
		this.stampImgPath = stampImgPath;
	}

	public List<EmployeeRoleDTO> getEmployeeRoleList() {
		return employeeRoleList;
	}

	public void setEmployeeRoleList(List<EmployeeRoleDTO> employeeRoleList) {
		this.employeeRoleList = employeeRoleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DepartmentDTO getDept() {
		return dept;
	}

	public EmpRankDTO getRank() {
		return rank;
	}

	public void setRank(EmpRankDTO rank) {
		this.rank = rank;
	}

	public void setDept(DepartmentDTO dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [code=" + code + ", pwd=" + pwd + ", name=" + name + ", engName=" + engName + ", rrn=" + rrn
				+ ", entDate=" + entDate + ", careerYN=" + careerYN + ", empPosition=" + empPosition + ", phoneHome="
				+ phoneHome + ", phoneMobile=" + phoneMobile + ", email=" + email + ", accountBank=" + accountBank
				+ ", accountNum=" + accountNum + ", accountHolder=" + accountHolder + ", address=" + address
				+ ", outYN=" + outYN + ", outDATE=" + outDATE + ", outReason=" + outReason + ", note=" + note
				+ ", profileOrigName=" + profileOrigName + ", profileUuidName=" + profileUuidName + ", profileImgName="
				+ profileImgName + ", stampOrigName=" + stampOrigName + ", stampUuidName=" + stampUuidName
				+ ", stampImgPath=" + stampImgPath + ", dept=" + dept + ", rank=" + rank + ", employeeRoleList="
				+ employeeRoleList + "]";
	}

}
