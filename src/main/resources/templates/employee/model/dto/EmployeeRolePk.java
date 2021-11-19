package org.erp.egv.employee.model.dto;

import java.io.Serializable;

public class EmployeeRolePk implements Serializable {
	private static final long serialVersionUID = 5424695695970367988L;
	
	private String employee;				
	private int authority;
	public EmployeeRolePk() {
	}
	public EmployeeRolePk(String employee, int authority) {
		this.employee = employee;
		this.authority = authority;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EmployeeRolePk [employee=" + employee + ", authority=" + authority + "]";
	}
	
}
