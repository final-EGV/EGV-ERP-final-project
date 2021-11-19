package org.erp.egv.employee.model.dto;

import java.io.Serializable;

public class AuthorityMenuPk implements Serializable {
	
	private static final long serialVersionUID = -8510042467255090942L;
	
	private int authority;
	private int menu;
	public AuthorityMenuPk() {
	}
	public AuthorityMenuPk(int authority, int menu) {
		this.authority = authority;
		this.menu = menu;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public int getMenu() {
		return menu;
	}
	public void setMenu(int menu) {
		this.menu = menu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AuthorityMenuPk [authority=" + authority + ", menu=" + menu + "]";
	}
	
}
