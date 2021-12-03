package org.erp.egv.main.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCHEDULE_CATEGORY")
public class ScheduleCategoryDTO implements Serializable {
	private static final long serialVersionUID = 2850409091391665477L;

	@Id
	@Column(name = "SCHEDULE_CATEGORY_CODE")
	private int schCatCode;
	
	@Column(name = "SCHEDULE_NAME")
	private String schCatName;

	public ScheduleCategoryDTO() {
	}
	public ScheduleCategoryDTO(int schCatCode, String schCatName) {
		this.schCatCode = schCatCode;
		this.schCatName = schCatName;
	}

	public int getSchCatCode() {
		return schCatCode;
	}
	public void setSchCatCode(int schCatCode) {
		this.schCatCode = schCatCode;
	}
	public String getSchCatName() {
		return schCatName;
	}
	public void setSchCatName(String schCatName) {
		this.schCatName = schCatName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ScheduleCategoryDTO [schCatCode=" + schCatCode + ", schCatName=" + schCatName + "]";
	}

	
}
