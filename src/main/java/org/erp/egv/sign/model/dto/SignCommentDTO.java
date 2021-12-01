package org.erp.egv.sign.model.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.erp.egv.employee.model.dto.EmployeeDTO;

@Entity
@SequenceGenerator(
		name = "SIGN_COM_SEQ_GEN",
		sequenceName = "SEQ_SIGN_COM_CODE",
		initialValue = 1,
		allocationSize = 1
		)

@Table(name = "SIGN_COMMENT")
public class SignCommentDTO implements Serializable, Comparable<SignCommentDTO> {
	private static final long serialVersionUID = -7320933195167218723L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "SIGN_COM_SEQ_GEN"
			)
	@Column(name = "SIGN_COM_CODE")
	private int code;
	
	@Column(name = "SIGN_COM_CONTENTS")
	private String contents;
	
	@Column(name = "SIGN_COM_DATE")
	private java.sql.Date date;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO emp;
	
	@ManyToOne
	@JoinColumn(name="SIGN_CODE")
	private SignDTO sign;

	public SignCommentDTO() {
	}

	public SignCommentDTO(int code, String contents, Date date, EmployeeDTO emp, SignDTO sign) {
		this.code = code;
		this.contents = contents;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	@Override
	public String toString() {
		return "SignCommentDTO [code=" + code + ", contents=" + contents + ", date=" + date + ", emp=" + emp + ", sign="
				+ sign + "]";
	}

	@Override
	public int compareTo(SignCommentDTO o) {
		if (this.code < o.getCode()) {
		    return -1;
		} else if (this.code > o.getCode()) {
		    return 1;
		}
		return 0;
	}

}
