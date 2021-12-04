package org.erp.egv.main.model.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SCHEDULE")
public class ScheduleDTO implements Serializable {
	private static final long serialVersionUID = -197097943120510956L;

	@Id
	@Column(name = "SCHEDULE_CODE")
	private int schCode;
	
	@ManyToOne
	@JoinColumn(name = "SCHEDULE_CATEGORY_CODE")
	private ScheduleCategoryDTO schCat;
	
	@Column(name = "EMP_CODE")
	private String empCode;
	
//	@Column(name = "SCHEDULE_CATEGORY_CODE")
//	private int schCatCode;
	
	@Column(name = "SCHEDULE_START_DATE")
	private java.sql.Date startDate;
	
	@Column(name = "SCHEDULE_END_DATE")
	private java.sql.Date endDate;
	
	@Column(name = "SCHEDULE_LOCATION")
	private String schLocation;
	
	@Column(name = "SCHEDULE_DESC")
	private String schDesc;

	public ScheduleDTO() {
	}
	public ScheduleDTO(int schCode, ScheduleCategoryDTO schCat, String empCode, Date startDate, Date endDate,
			String schLocation, String schDesc) {
		this.schCode = schCode;
		this.schCat = schCat;
		this.empCode = empCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.schLocation = schLocation;
		this.schDesc = schDesc;
	}

	public int getSchCode() {
		return schCode;
	}
	public void setSchCode(int schCode) {
		this.schCode = schCode;
	}
	public ScheduleCategoryDTO getSchCat() {
		return schCat;
	}
	public void setSchCat(ScheduleCategoryDTO schCat) {
		this.schCat = schCat;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public java.sql.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public java.sql.Date getEndDate() {
		return endDate;
	}
	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}
	public String getSchLocation() {
		return schLocation;
	}
	public void setSchLocation(String schLocation) {
		this.schLocation = schLocation;
	}
	public String getSchDesc() {
		return schDesc;
	}
	public void setSchDesc(String schDesc) {
		this.schDesc = schDesc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [schCode=" + schCode + ", schCat=" + schCat + ", empCode=" + empCode + ", startDate="
				+ startDate + ", endDate=" + endDate + ", schLocation=" + schLocation + ", schDesc=" + schDesc + "]";
	}

	
	

}
