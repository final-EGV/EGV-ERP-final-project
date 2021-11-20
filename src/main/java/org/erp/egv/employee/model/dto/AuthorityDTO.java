package org.erp.egv.employee.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
public class AuthorityDTO implements Serializable{
	private static final long serialVersionUID = 3870131319328620875L;
	
	@Id
	@Column(name = "AUTHORITY_CODE")
	private int code;		// 권한코드(PK)
	
	@Column(name = "AUTHORITY_NAME")
	private String name;	// 권한명
	
	@Column(name = "AUTHORITY_DESC")
	private String desc;	// 권한설명
	
	@OneToMany(mappedBy = "authority")
	private List<AuthorityMenuDTO> authenticatedMenuList = new ArrayList<>();
	
	@OneToMany(mappedBy = "authority")
	private List<EmployeeRoleDTO> memberRoleList = new ArrayList<>();

	public AuthorityDTO() {
	}

	public AuthorityDTO(int code, String name, String desc, List<AuthorityMenuDTO> authenticatedMenuList,
			List<EmployeeRoleDTO> memberRoleList) {
		this.code = code;
		this.name = name;
		this.desc = desc;
		this.authenticatedMenuList = authenticatedMenuList;
		this.memberRoleList = memberRoleList;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<AuthorityMenuDTO> getAuthenticatedMenuList() {
		return authenticatedMenuList;
	}

	public void setAuthenticatedMenuList(List<AuthorityMenuDTO> authenticatedMenuList) {
		this.authenticatedMenuList = authenticatedMenuList;
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
		return "AuthorityDTO [code=" + code + ", name=" + name + ", desc=" + desc + ", authenticatedMenuList="
				+ authenticatedMenuList + ", memberRoleList=" + memberRoleList + "]";
	}

}
