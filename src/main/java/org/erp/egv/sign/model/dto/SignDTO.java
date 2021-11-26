package org.erp.egv.sign.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "sign")
	private List<ApproverDTO> approver;
	
	@OneToMany(mappedBy = "sign")
	private List<RefferrerDTO> refferrer;

	public SignDTO() {
	}

	public SignDTO(int code, Date date, String status, String title, String contents, TemplateDTO temp,
			EmployeeDTO employee, List<ApproverDTO> approver, List<RefferrerDTO> refferrer) {
		this.code = code;
		this.date = date;
		this.status = status;
		this.title = title;
		this.contents = contents;
		this.temp = temp;
		this.employee = employee;
		this.approver = approver;
		this.refferrer = refferrer;
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

	public List<ApproverDTO> getApprover() {
		return approver;
	}

	public void setApprover(List<ApproverDTO> approver) {
		this.approver = approver;
	}

	public List<RefferrerDTO> getRefferrer() {
		return refferrer;
	}

	public void setRefferrer(List<RefferrerDTO> refferrer) {
		this.refferrer = refferrer;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SignDTO [code=" + code + ", date=" + date + ", status=" + status + ", title=" + title + ", contents="
				+ contents + ", temp=" + temp + ", employee=" + employee + ", approver=" + approver + ", refferrer="
				+ refferrer + "]";
	}

}
