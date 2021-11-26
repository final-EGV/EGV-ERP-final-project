package org.erp.egv.employee.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EMP_RANK")
@SequenceGenerator(name = "EMP_RANK_SEQ_GENERATOR",
				   sequenceName = "SEQ_EMP_RANK_CODE",
				   initialValue = 700,
				   allocationSize = 100)	
public class EmpRankDTO implements Serializable{
	private static final long serialVersionUID = -2874782020541650674L;

	@Id
	@Column(name = "RANK_CODE")
	private int code;
	
	@Column(name = "RANK_NAME")
	private String name;

	@Column(name = "RANK_SALARY")
	private int salary;
	
	@Column(name = "RANK_YN")
	private String yn;

	public EmpRankDTO() {
	}
	public EmpRankDTO(int code, String name, int salary, String yn) {
		this.code = code;
		this.name = name;
		this.salary = salary;
		this.yn = yn;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getYn() {
		return yn;
	}
	public void setYn(String yn) {
		this.yn = yn;
	}
	
	@Override
	public String toString() {
		return "EmpRankDTO [code=" + code + ", name=" + name + ", salary=" + salary + ", yn=" + yn + "]";
	}
}
