package org.erp.egv.employee.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserImpl extends User{
	private static final long serialVersionUID = 6655734623911193439L;
	
	private String code;
	private String pwd;
	private String name;
	private String engName;
	private String rrn;
	private java.sql.Date entDate;
	private String careerYN;
	private String phoneHome;
	private String phoneMobile;
	private String email;
	private String accountBank;
	private String accountNum;
	private String accountHolder;
	private String address;
	private String outYN;
	private String empPosition;
	private String outDATE;
	private String outReason;
	private String note;
	private String profileOrigName;
	private String profileUuidName;
	private String profileImgName;
	private String stampOrigName;
	private String stampUuidName;
	private String stampImgPath;
	
	private List<EmployeeRoleDTO> memberRoleList = new ArrayList<>();		// 회원별권한리스트
	
	public UserImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	/* MemberDTO 객체를 전달 받아 필드를 다 초기화 해주는 메소드 작성 */
	public void setDetails(EmployeeDTO employee) {
		this.code = employee.getCode();
		this.pwd = employee.getPwd();
		this.name = employee.getName();
		this.engName = employee.getEngName();
		this.rrn = employee.getRrn();
		this.entDate = employee.getEntDate();
		this.careerYN = employee.getCareerYN();
		this.phoneHome = employee.getPhoneHome();
		this.phoneMobile = employee.getPhoneMobile();
		this.email = employee.getEmail();
		this.accountBank = employee.getAccountBank();
		this.accountNum = employee.getAccountNum();
		this.accountHolder = employee.getAccountHolder();
		this.address = employee.getAddress();
		this.outYN = employee.getOutYN();
		this.empPosition = employee.getEmpPosition();
		this.outDATE = employee.getOutDATE();
		this.outReason = employee.getOutReason();
		this.note = employee.getNote();
		this.profileOrigName = employee.getProfileOrigName();
		this.profileUuidName = employee.getProfileUuidName();
		this.profileImgName = employee.getProfileImgName();
		this.stampOrigName = employee.getStampOrigName();
		this.stampUuidName = employee.getStampUuidName();
		this.stampImgPath = employee.getStampImgPath();
		
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

	public String getEmpPosition() {
		return empPosition;
	}

	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
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

	public List<EmployeeRoleDTO> getMemberRoleList() {
		return memberRoleList;
	}

	public void setMemberRoleList(List<EmployeeRoleDTO> memberRoleList) {
		this.memberRoleList = memberRoleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserImpl [code=" + code + ", pwd=" + pwd + ", name=" + name + ", engName=" + engName + ", rrn=" + rrn
				+ ", entDate=" + entDate + ", careerYN=" + careerYN + ", phoneHome=" + phoneHome + ", phoneMobile="
				+ phoneMobile + ", email=" + email + ", accountBank=" + accountBank + ", accountNum=" + accountNum
				+ ", accountHolder=" + accountHolder + ", address=" + address + ", outYN=" + outYN + ", empPosition="
				+ empPosition + ", outDATE=" + outDATE + ", outReason=" + outReason + ", note=" + note
				+ ", profileOrigName=" + profileOrigName + ", profileUuidName=" + profileUuidName + ", profileImgName="
				+ profileImgName + ", stampOrigName=" + stampOrigName + ", stampUuidName=" + stampUuidName
				+ ", stampImgPath=" + stampImgPath + ", memberRoleList=" + memberRoleList + "]";
	}

}
