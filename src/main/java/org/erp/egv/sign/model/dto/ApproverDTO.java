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
@Table(name = "APPROVER")
public class ApproverDTO implements Serializable, Comparable<ApproverDTO> {
	private static final long serialVersionUID = -403266508485015035L;
	
	@Id
	@Column(name = "APPROVER_CODE")
	private int code;
	
	@Column(name = "APPROVER_ORDER")
	private int order;
	
	@Column(name = "APPROVER_STATUS")
	private String status;
	
	@Column(name = "APPROVER_DATE")
	private java.sql.Date date;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO emp; 
	
	@ManyToOne
	@JoinColumn(name="SIGN_CODE")
	private SignDTO sign;

	public ApproverDTO() {
	}

	public ApproverDTO(int code, int order, String status, Date date, EmployeeDTO emp, SignDTO sign) {
		this.code = code;
		this.order = order;
		this.status = status;
		this.date = date;
		this.emp = emp;
		this.sign = sign;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public EmployeeDTO getEmp() {
		return emp;
	}

	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}

	public SignDTO getSign() {
		return sign;
	}

	public void setSign(SignDTO sign) {
		this.sign = sign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ApproverDTO [code=" + code + ", order=" + order + ", status=" + status + ", date=" + date + ", emp="
				+ emp + ", sign=" + sign + "]";
	}

	@Override
	public int compareTo(ApproverDTO o) {
		if (this.order < o.getOrder()) {
		    return -1;
		} else if (this.order > o.getOrder()) {
		    return 1;
		}
		return 0;
	}
	
}
