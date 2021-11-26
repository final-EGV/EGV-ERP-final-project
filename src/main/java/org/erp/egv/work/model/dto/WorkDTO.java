package org.erp.egv.work.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.erp.egv.employee.model.dto.EmployeeDTO;

@Entity
@Table(name = "EGV_WORK")
public class WorkDTO implements Serializable{
	private static final long serialVersionUID = 8366612674038350038L;

	@Id
	@Column(name = "WORK_CODE")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "WORKCATEGORY_CODE")
	private WorkTypeCategoryDTO categoryCode;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO empCode;
	
	@Column(name = "WORK_DATE")
	private Date workDate;
	
	@Column(name = "WORK_START")
	private Timestamp workStart;
	
	@Column(name = "WORK_END")
	private Timestamp workEnd;
	
	@Column(name = "WORK_OVER")
	private String workOver;

	public WorkDTO() {
	}

	public WorkDTO(int code, WorkTypeCategoryDTO categoryCode, EmployeeDTO empCode, Date workDate, Timestamp workStart,
			Timestamp workEnd, String workOver) {
		this.code = code;
		this.categoryCode = categoryCode;
		this.empCode = empCode;
		this.workDate = workDate;
		this.workStart = workStart;
		this.workEnd = workEnd;
		this.workOver = workOver;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public WorkTypeCategoryDTO getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(WorkTypeCategoryDTO categoryCode) {
		this.categoryCode = categoryCode;
	}

	public EmployeeDTO getEmpCode() {
		return empCode;
	}

	public void setEmpCode(EmployeeDTO empCode) {
		this.empCode = empCode;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public Timestamp getWorkStart() {
		return workStart;
	}

	public void setWorkStart(Timestamp workStart) {
		this.workStart = workStart;
	}

	public Timestamp getWorkEnd() {
		return workEnd;
	}

	public void setWorkEnd(Timestamp workEnd) {
		this.workEnd = workEnd;
	}

	public String getWorkOver() {
		return workOver;
	}

	public void setWorkOver(String workOver) {
		this.workOver = workOver;
	}

	@Override
	public String toString() {
		return "WorkDTO [code=" + code + ", categoryCode=" + categoryCode + ", empCode=" + empCode + ", workDate="
				+ workDate + ", workStart=" + workStart + ", workEnd=" + workEnd + ", workOver=" + workOver + "]";
	}

}
