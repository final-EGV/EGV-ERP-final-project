package org.erp.egv.main.model.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.erp.egv.employee.model.dto.EmployeeDTO;

@Entity
@Table(name = "SCHEDULE")
public class ScheduleDTO implements Serializable {
	private static final long serialVersionUID = -197097943120510956L;

	@Id
	@Column(name = "SCHEDULE_CODE")
	private int schCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO emp;
	
	@ManyToOne
	@JoinColumn(name = "SCHEDULE_CATEGORY_CODE")
	private ScheduleCategoryDTO schCat;
	
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
	public ScheduleDTO(int schCode, EmployeeDTO emp, ScheduleCategoryDTO schCat, Date startDate, Date endDate,
			String schLocation, String schDesc) {
		this.schCode = schCode;
		this.emp = emp;
		this.schCat = schCat;
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
	public EmployeeDTO getEmp() {
		return emp;
	}
	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}
	public ScheduleCategoryDTO getSchCat() {
		return schCat;
	}
	public void setSchCat(ScheduleCategoryDTO schCat) {
		this.schCat = schCat;
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
		return "ScheduleDTO [schCode=" + schCode + ", emp=" + emp + ", schCat=" + schCat + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", schLocation=" + schLocation + ", schDesc=" + schDesc + "]";
	}

}
