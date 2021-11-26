package org.erp.egv.sign.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.erp.egv.employee.model.dto.EmployeeDTO;

@Entity
@Table(name = "REFERRER")
public class RefferrerDTO implements Serializable{
	private static final long serialVersionUID = 8063916782864597154L;
	
	@Id
	@Column(name = "REFERRER_CODE")
	private int code;
	
	@Column(name = "REFERRER_YN")
	private String readYN;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO emp; 
	
	@ManyToOne
	@JoinColumn(name="SIGN_CODE")
	private SignDTO sign;

	public RefferrerDTO() {
	}

	public RefferrerDTO(int code, String readYN, EmployeeDTO emp, SignDTO sign) {
		this.code = code;
		this.readYN = readYN;
		this.emp = emp;
		this.sign = sign;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getReadYN() {
		return readYN;
	}

	public void setReadYN(String readYN) {
		this.readYN = readYN;
	}

	public EmployeeDTO getEmp() {
		return emp;
	}

	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}

	public SignDTO getSign() {
		return sign;
	}

	public void setSign(SignDTO sign) {
		this.sign = sign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RefferrerDTO [code=" + code + ", readYN=" + readYN + ", emp=" + emp + ", sign=" + sign + "]";
	}

}
