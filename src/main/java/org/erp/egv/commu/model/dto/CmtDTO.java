package org.erp.egv.commu.model.dto;

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
		name = "CMT_SEQ_GEN",
		sequenceName = "SEQ_CMT_CODE",
		initialValue = 1,
		allocationSize = 1
		)
@Table(name = "CMT")
public class CmtDTO implements Serializable {
	private static final long serialVersionUID = 4789865755002225593L;
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "CMT_SEQ_GEN"
			)
	@Column(name="CMT_CODE")
	private int code;
	
	@Column(name="CMT_DATE")
	private java.sql.Date date;
	
	@Column(name="CMT_CONTENTS")
	private String contents;
	
	@ManyToOne()
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO emp;
	
	@ManyToOne()
	@JoinColumn(name="POST_CODE")
	private BlindPostDTO post;

	public CmtDTO() {
	}

	public CmtDTO(int code, Date date, String contents, EmployeeDTO emp, BlindPostDTO post) {
		this.code = code;
		this.date = date;
		this.contents = contents;
		this.emp = emp;
		this.post = post;
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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public EmployeeDTO getEmp() {
		return emp;
	}

	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}

	public BlindPostDTO getPost() {
		return post;
	}

	public void setPost(BlindPostDTO post) {
		this.post = post;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CmtDTO [code=" + code + ", date=" + date + ", contents=" + contents + ", emp=" + emp + ", post=" + post
				+ "]";
	}

}
