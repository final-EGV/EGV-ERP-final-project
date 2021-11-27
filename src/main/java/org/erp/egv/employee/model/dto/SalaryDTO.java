package org.erp.egv.employee.model.dto;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SALARY")
public class SalaryDTO implements Serializable{

	private static final long serialVersionUID = 8269806595941716326L;
	
	@Id
	@Column(name = "SAL_CODE")
	private int salCode;				// 개인급여코드(PK)
	
	@Id
	@ManyToOne
	@JoinColumn(name = "EMP_CODE")
	private EmployeeDTO employee;		// 사번
	
	@Column(name = "SAL_MONTH")
	private java.sql.Date entDate;		// 해당달
	
	@Column(name = "SAL_DAY")
	private int day;					// 해당달 근속일
	
	@Column(name = "SAL_STANDARD")
	private int standard;				// 기준급여
	
	@Column(name = "SAL_OVER")
	private int over;					// 초과근무급여
	
	@Column(name = "SAL_FINAL")
	private int finalSal;				// 최종급여
	
	@Column(name = "SAL_YN")
	private String YN;					// 급여지급여부

	public SalaryDTO() {
		super();
	}

	public SalaryDTO(int salCode, EmployeeDTO employee, Date entDate, int day, int standard, int over, int finalSal,
			String yN) {
		super();
		this.salCode = salCode;
		this.employee = employee;
		this.entDate = entDate;
		this.day = day;
		this.standard = standard;
		this.over = over;
		this.finalSal = finalSal;
		this.YN = yN;
	}

	public int getSalCode() {
		return salCode;
	}

	public void setSalCode(int salCode) {
		this.salCode = salCode;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public java.sql.Date getEntDate() {
		return entDate;
	}

	public void setEntDate(java.sql.Date entDate) {
		this.entDate = entDate;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public int getOver() {
		return over;
	}

	public void setOver(int over) {
		this.over = over;
	}

	public int getFinalSal() {
		return finalSal;
	}

	public void setFinalSal(int finalSal) {
		this.finalSal = finalSal;
	}

	public String getyN() {
		return YN;
	}

	public void setyN(String yN) {
		this.YN = YN;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalaryDTO [salCode=" + salCode + ", employee=" + employee + ", entDate=" + entDate + ", day=" + day
				+ ", standard=" + standard + ", over=" + over + ", finalSal=" + finalSal + ", YN=" + YN + "]";
	}
	
	
}
