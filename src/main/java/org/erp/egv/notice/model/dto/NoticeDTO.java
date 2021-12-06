package org.erp.egv.notice.model.dto;

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

@Entity(name="NoticeDTO")
@SequenceGenerator(name = "NOTICE_SEQ_GENERATOR",
					sequenceName = "SEQ_NOTICE_CODE",
					initialValue = 1,
					allocationSize = 1)
@Table(name="NOTICE")
public class NoticeDTO implements Serializable {

	private static final long serialVersionUID = -3486334902118956797L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "NOTICE_SEQ_GENERATOR")
	@Column(name="NOTICE_CODE")
	private int noticeCode;
	
	@Column(name="NOTICE_TITLE")
	private String title;
	
	@Column(name="NOTICE_CONTENT")
	private String content;
	
	@Column(name="NOTICE_DATE")
	private java.sql.Date date;
	
	@ManyToOne()
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO empCode;

	public NoticeDTO() {
	}

	public NoticeDTO(int noticeCode, String title, String content, Date date, EmployeeDTO empCode) {
		this.noticeCode = noticeCode;
		this.title = title;
		this.content = content;
		this.date = date;
		this.empCode = empCode;
	}

	public int getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(int noticeCode) {
		this.noticeCode = noticeCode;
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

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public EmployeeDTO getEmployee() {
		return empCode;
	}

	public void setEmployee(EmployeeDTO empCode) {
		this.empCode = empCode;
	}

	@Override
	public String toString() {
		return "NoticeDTO [noticeCode=" + noticeCode + ", title=" + title + ", content=" + content + ", date=" + date
				+ ", empCode=" + empCode + "]";
	}

	
}
