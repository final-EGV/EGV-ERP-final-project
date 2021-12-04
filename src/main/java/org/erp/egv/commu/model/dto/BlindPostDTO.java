package org.erp.egv.commu.model.dto;

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
@Table(name = "BLIND_POST")
public class BlindPostDTO implements Serializable {
	private static final long serialVersionUID = 8253210832028247964L;

	@Id
	@Column(name = "POST_CODE")
	private int code;
	
	@Column(name = "POST_DATE")
	private java.sql.Date date;
	
	@Column(name = "POST_TITLE")
	private String title;
	
	@Column(name = "POST_CONTENT")
	private String content;
	
	@ManyToOne()
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO emp;
	
	@OneToMany(mappedBy = "post")
	private List<CmtDTO> cmt;

	public BlindPostDTO() {
	}

	public BlindPostDTO(int code, Date date, String title, String content, EmployeeDTO emp, List<CmtDTO> cmt) {
		this.code = code;
		this.date = date;
		this.title = title;
		this.content = content;
		this.emp = emp;
		this.cmt = cmt;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EmployeeDTO getEmp() {
		return emp;
	}

	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}

	public List<CmtDTO> getCmt() {
		return cmt;
	}

	public void setCmt(List<CmtDTO> cmt) {
		this.cmt = cmt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BlindPostDTO [code=" + code + ", date=" + date + ", title=" + title + ", content=" + content + ", emp="
				+ emp + ", cmt=" + cmt + "]";
	}

}
