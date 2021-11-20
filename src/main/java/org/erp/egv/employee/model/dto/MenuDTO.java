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
@Table(name = "MENU")
public class MenuDTO implements Serializable {
	private static final long serialVersionUID = -1414895496395743756L;
	
	@Id
	@Column(name = "MENU_CODE")
	private int code;				// 메뉴코드
	
	@Column(name = "MENU_NAME")
	private String name;			// 메뉴명
	
	@Column(name = "MENU_URL")
	private String url;				// 메뉴URL
	
	@Column(name = "MENU_DESC")
	private String desc;			// 메뉴설명
	
	@OneToMany(mappedBy = "menu")
	private List<AuthorityMenuDTO> authenticatedMenu = new ArrayList<>();

	public MenuDTO() {
	}

	public MenuDTO(int code, String name, String url, String desc, List<AuthorityMenuDTO> authenticatedMenu) {
		this.code = code;
		this.name = name;
		this.url = url;
		this.desc = desc;
		this.authenticatedMenu = authenticatedMenu;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<AuthorityMenuDTO> getAuthenticatedMenu() {
		return authenticatedMenu;
	}

	public void setAuthenticatedMenu(List<AuthorityMenuDTO> authenticatedMenu) {
		this.authenticatedMenu = authenticatedMenu;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MenuDTO [code=" + code + ", name=" + name + ", url=" + url + ", desc=" + desc + ", authenticatedMenu="
				+ authenticatedMenu + "]";
	}
	
	
	

}
