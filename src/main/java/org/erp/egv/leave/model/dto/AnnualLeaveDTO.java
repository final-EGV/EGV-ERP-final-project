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
@Table(name = "ANNUAL_LEAVE")
public class AnnualLeaveDTO implements Serializable{
	private static final long serialVersionUID = -8778880747799272707L;

	@Id
	@Column(name = "AL_CODE")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "AL_CATEGORY_CODE")
	private AnnualLeaveCategoryDTO categoryCode;
	
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

	public AnnualLeaveDTO(int code, AnnualLeaveCategoryDTO categoryCode, EmployeeDTO empCode, int year, int count,
			int useCount) {
		this.code = code;
		this.categoryCode = categoryCode;
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

	public AnnualLeaveCategoryDTO getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(AnnualLeaveCategoryDTO categoryCode) {
		this.categoryCode = categoryCode;
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
		return "AnnualLeaveDTO [code=" + code + ", categoryCode=" + categoryCode + ", empCode=" + empCode + ", year="
				+ year + ", count=" + count + ", useCount=" + useCount + "]";
	}
	
}
