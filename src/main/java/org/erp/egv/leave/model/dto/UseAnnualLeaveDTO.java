package org.erp.egv.leave.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "USE_ANNUAL_LEAVE")
@SequenceGenerator(name = "UAL_SEQ_GENERATOR",
sequenceName = "SEQ_UAL_CODE",
allocationSize = 1)
public class UseAnnualLeaveDTO implements Serializable{
	private static final long serialVersionUID = 4957149843892985761L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "UAL_SEQ_GENERATOR")	
	@Column(name = "UAL_CODE")
	private int code;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO empCode;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ANNUAL_LEAVE_CODE")
	private AnnualLeaveCategoryDTO categoryCode;
	
	@Column(name = "UAL_START_DATE")
	private Date start;
	
	@Column(name = "UAL_END_DATE")
	private Date end;
	
	@Column(name = "TOTAL_DATE")
	private int total;
	
	@Column(name = "UAL_CONTENT")
	private String content;

	public UseAnnualLeaveDTO() {
	}

	public UseAnnualLeaveDTO(int code, EmployeeDTO empCode, AnnualLeaveCategoryDTO categoryCode, Date start, Date end,
			int total, String content) {
		this.code = code;
		this.empCode = empCode;
		this.categoryCode = categoryCode;
		this.start = start;
		this.end = end;
		this.total = total;
		this.content = content;
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

	public AnnualLeaveCategoryDTO getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(AnnualLeaveCategoryDTO categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public void setTotalDate(int total) {
		this.total = total;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "UseAnnualLeaveDTO [code=" + code + ", empCode=" + empCode + ", categoryCode=" + categoryCode
				+ ", start=" + start + ", end=" + end + ", total=" + total + ", content=" + content + "]";
	}

	
}
