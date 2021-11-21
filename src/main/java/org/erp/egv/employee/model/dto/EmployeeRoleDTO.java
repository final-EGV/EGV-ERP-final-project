package org.erp.egv.employee.model.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_AUTHORITY")
@IdClass(EmployeeRolePk.class)
public class EmployeeRoleDTO implements Serializable{
	private static final long serialVersionUID = 5606061018437828147L;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO employee;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "AUTHORITY_CODE")
	private AuthorityDTO authority;

	public EmployeeRoleDTO() {
	}

	public EmployeeRoleDTO(EmployeeDTO employee, AuthorityDTO authority) {
		this.employee = employee;
		this.authority = authority;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeeRoleDTO [employee=" + employee.getName() + ", authority=" + authority.getName() + "]";
	}


}
