package org.erp.egv.leave.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "USE_ANNUAL_LEAVE")
public class UseAnnualLeaveDTO implements Serializable{
	private static final long serialVersionUID = 4957149843892985761L;

	@Id
	@Column(name = "UAL_CODE")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO empCode;
	
	@Column(name = "UAL_DATE")
	private Date date;
	
	@Column(name = "UAL_CONTENT")
	private String content;

	public UseAnnualLeaveDTO() {
	}

	public UseAnnualLeaveDTO(int code, EmployeeDTO empCode, Date date, String content) {
		this.code = code;
		this.empCode = empCode;
		this.date = date;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public EmployeeDTO getEmpCode() {
		return empCode;
	}

	public void setEmpCode(EmployeeDTO empCode) {
		this.empCode = empCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "UseAnnualLeaveDTO [code=" + code + ", empCode=" + empCode + ", date=" + date + ", content=" + content
				+ "]";
	}
	
}
