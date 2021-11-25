package org.erp.egv.sign.model.dto;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SIGN_TEMPLATE")
public class TemplateDTO implements Serializable {
	private static final long serialVersionUID = 2388870438303032980L;
	
	@Id
	@Column(name="TEMP_CODE")
	private int code;
	
	@Column(name="TEMP_NAME")
	private String name;
	
	@Column(name="TEMP_CONTENTS")
	private String contents;
	
	@Column(name="TEMP_EX")
	private String ex;

	@OneToMany(mappedBy = "temp")
	private List<SignDTO> signList;

	public TemplateDTO() {
	}

	public TemplateDTO(int code, String name, String contents, String ex, List<SignDTO> signList) {
		this.code = code;
		this.name = name;
		this.contents = contents;
		this.ex = ex;
		this.signList = signList;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getEx() {
		return ex;
	}

	public void setEx(String ex) {
		this.ex = ex;
	}

	public List<SignDTO> getSignList() {
		return signList;
	}

	public void setSignList(List<SignDTO> signList) {
		this.signList = signList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TemplateDTO [code=" + code + ", name=" + name + ", contents=" + contents + ", ex=" + ex + "]";
	}
	
	
}