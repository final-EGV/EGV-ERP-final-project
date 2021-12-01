package org.erp.egv.leave.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ANNUAL_LEAVE_CATEGORY")
public class AnnualLeaveCategoryDTO implements Serializable{
	private static final long serialVersionUID = 3865438068028934987L;

	@Id
	@Column(name = "ANNUAL_LEAVE_CODE")
	private int code;
	
	@Column(name = "ANNUAL_LEAVE_TYPE")
	private String type;

	public AnnualLeaveCategoryDTO() {
	}

	public AnnualLeaveCategoryDTO(int code, String type) {
		this.code = code;
		this.type = type;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "AnnualLeaveCategoryDTO [code=" + code + ", type=" + type + "]";
	}
	
}
