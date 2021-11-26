package org.erp.egv.work.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WORKTYPE_CATEGORY")
public class WorkTypeCategoryDTO implements Serializable{
	private static final long serialVersionUID = 1279986817183845485L;

	@Id
	@Column(name = "WORKCATEGORY_CODE")
	private int code;
	
	@Column(name = "WORK_TYPE")
	private String type;

	public WorkTypeCategoryDTO() {
	}

	public WorkTypeCategoryDTO(int code, String type) {
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
		return "WorkTypeCategoryDTO [code=" + code + ", type=" + type + "]";
	}
	
}
