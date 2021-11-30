package org.erp.egv.employee.model.dto;

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

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "PARTTIME_SCHEDULE")
@DynamicInsert
@SequenceGenerator(name = "PARTTIME_SCHEDULE_SEQ_GENERATOR",
				   sequenceName = "SEQ_PARTTIME_CODE",
				   initialValue = 101, 
				   allocationSize = 1)
public class ParttimeScheduleDTO implements Serializable {

	private static final long serialVersionUID = 5273924235332184054L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "PARTTIME_SCHEDULE_SEQ_GENERATOR")	
	@Column(name = "PARTTIME_CODE")
	private int code;

	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO emp;

	@Column(name = "PARTTIME_START")
	private java.sql.Date start;

	@Column(name = "PARTTIME_END")
	private java.sql.Date end;

	/* 오픈, 마감 */
	@Column(name = "PARTTIME_DIVISION")
	private String parttimeDivision;
	
	@Column(name = "PARTTIME_TITLE")
	private String title;

	public ParttimeScheduleDTO() {
	}

	public ParttimeScheduleDTO(int code, EmployeeDTO emp, Date start, Date end, String parttimeDivision,
			String title) {
		this.code = code;
		this.emp = emp;
		this.start = start;
		this.end = end;
		this.parttimeDivision = parttimeDivision;
		this.title = title;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public EmployeeDTO getemp() {
		return emp;
	}

	public void setemp(EmployeeDTO emp) {
		this.emp = emp;
	}

	public java.sql.Date getStart() {
		return start;
	}

	public void setStart(java.sql.Date start) {
		this.start = start;
	}

	public java.sql.Date getEnd() {
		return end;
	}

	public void setEnd(java.sql.Date end) {
		this.end = end;
	}

	public String getParttimeDivision() {
		return parttimeDivision;
	}

	public void setParttimeDivision(String parttimeDivision) {
		this.parttimeDivision = parttimeDivision;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "ParttimeScheduleDTO [code=" + code + ", emp=" + emp.getCode() + ", start=" + start + ", end="
				+ end + ", parttimeDivision=" + parttimeDivision + ", title=" + title + "]";
	}

}
