package org.erp.egv.employee.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "DEPARTMENT")
@DynamicInsert
@SequenceGenerator(name = "DEPARTMENT_SEQ_GENERATOR",
					sequenceName = "SEQ_DEPARTMENT_CODE",
					initialValue = 600,
					allocationSize = 100)
public class DepartmentDTO implements Serializable{
	private static final long serialVersionUID = 8505484750906149300L;

	@Id
	@Column(name = "DEPT_CODE")
	private int code;
	
	@Column(name = "DEPT_NAME")
	private String name;
	
	@Column(name = "DEPT_YN")
	private String Yn;

	public DepartmentDTO() {
	}
	public DepartmentDTO(int code, String name, String yn) {
		this.code = code;
		this.name = name;
		Yn = yn;
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

	public String getYn() {
		return Yn;
	}

	public void setYn(String yn) {
		Yn = yn;
	}

	@Override
	public String toString() {
		return "DepartmentDTO [code=" + code + ", name=" + name + ", Yn=" + Yn + "]";
	}
	
	
}
