package org.erp.egv.sign.model.dto;

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
@Table(name="SIGN")
public class SignDTO implements Serializable {
	private static final long serialVersionUID = -8150553449121095708L;
	
	@Id
	@Column(name="SIGN_CODE")
	private int code;
	
	@Column(name="SIGN_DATE")
	private java.sql.Date date;
	
	@Column(name="SIGN_STATUS")
	private String status;
	
	@Column(name="SIGN_TITLE")
	private String title;
	
	@Column(name="FILE_CONTENTS")
	private String contents;
	
	@ManyToOne()
	@JoinColumn(name="TEMP_CODE")
	private TemplateDTO temp;
	
	@ManyToOne()
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO employee;

	public SignDTO() {
	}

	public SignDTO(int code, Date date, String status, String title, String contents, TemplateDTO temp,
			EmployeeDTO employee) {
		this.code = code;
		this.date = date;
		this.status = status;
		this.title = title;
		this.contents = contents;
		this.temp = temp;
		this.employee = employee;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public TemplateDTO getTemp() {
		return temp;
	}

	public void setTemp(TemplateDTO temp) {
		this.temp = temp;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SignDTO [code=" + code + ", date=" + date + ", status=" + status + ", title=" + title + ", contents="
				+ contents + ", temp=" + temp + ", employee=" + employee + "]";
	}
	
	
}
