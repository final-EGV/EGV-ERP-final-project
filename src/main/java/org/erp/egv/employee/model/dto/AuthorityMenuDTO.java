package org.erp.egv.employee.model.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITY_MENU")
@IdClass(AuthorityMenuPk.class)
public class AuthorityMenuDTO {
	
	@Id
	@ManyToOne
	@JoinColumn(name="MENU_CODE")
	private MenuDTO menu;
	
	@Id
	@ManyToOne
	@JoinColumn(name="AUTHORITY_CODE")
	private AuthorityDTO authority;

	public AuthorityMenuDTO() {
	}

	public AuthorityMenuDTO(MenuDTO menu, AuthorityDTO authority) {
		this.menu = menu;
		this.authority = authority;
	}

	public MenuDTO getMenu() {
		return menu;
	}

	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}

	public AuthorityDTO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityDTO authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "AuthorityMenuDTO [menu=" + menu + ", authority=" + authority + "]";
	}
	
	

}
