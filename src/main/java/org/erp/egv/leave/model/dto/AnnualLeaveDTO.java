package org.erp.egv.leave.model.dto;

import java.io.Serializable;
import java.util.Date;

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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "ANNUAL_LEAVE")
@SequenceGenerator(name = "ANNUAL_LEAVE_SEQ_GENERATOR",
sequenceName = "SEQ_AL_CODE",
initialValue = 1, 
allocationSize = 1)
public class AnnualLeaveDTO implements Serializable{
	private static final long serialVersionUID = -8778880747799272707L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "ANNUAL_LEAVE_SEQ_GENERATOR")	
	@Column(name = "AL_CODE")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO empCode;
	
	@Column(name = "AL_YEAR")
	private int year;
	
	@Column(name = "AL_COUNT")
	private int count;
	
	@Column(name = "AL_USE_COUNT")
	private int useCount;

	public AnnualLeaveDTO() {
	}

	public AnnualLeaveDTO(int code, EmployeeDTO empCode, int year, int count,
			int useCount) {
		this.code = code;
		this.empCode = empCode;
		this.year = year;
		this.count = count;
		this.useCount = useCount;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getUseCount() {
		return useCount;
	}

	public void setUseCount(int useCount) {
		this.useCount = useCount;
	}

	@Override
	public String toString() {
		return "AnnualLeaveDTO [code=" + code + ", empCode=" + empCode + ", year="
				+ year + ", count=" + count + ", useCount=" + useCount + "]";
	}
	
}
