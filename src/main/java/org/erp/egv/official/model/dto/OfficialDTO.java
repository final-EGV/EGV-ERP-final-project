package org.erp.egv.official.model.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.erp.egv.employee.model.dto.EmployeeDTO;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "OFFICIAL")
//@SequenceGenerator(name = "OFFICIAL_SEQ_GENERATOR",
//sequenceName = "SEQ_OFFICIAL_CODE",
//initialValue = 1, 
//allocationSize = 1)
public class OfficialDTO implements Serializable {
	private static final long serialVersionUID = -5402181677522229937L;

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE,
//	generator = "OFFICIAL_SEQ_GENERATOR")	
	@Column(name = "OFFICIAL_CODE")
	private int code;
	
	@Column(name = "OFFICIAL_TITLE")
	private String title;
	
	@Column(name = "OFFICIAL_CONTENT")
	private String content;
	
	@Column(name = "OFFICIAL_DATE")
	private java.sql.Date date;

	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private EmployeeDTO emp;
	
	@OneToMany(mappedBy = "official", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OfficialFileDTO> officialFiles  = new ArrayList<>();

	public OfficialDTO() {
	}

	public OfficialDTO(int code, String title, String content, Date date, EmployeeDTO emp,
			List<OfficialFileDTO> officialFiles) {
		this.code = code;
		this.title = title;
		this.content = content;
		this.date = date;
		this.emp = emp;
		this.officialFiles = officialFiles;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public EmployeeDTO getEmp() {
		return emp;
	}

	public void setEmp(EmployeeDTO emp) {
		this.emp = emp;
	}

	public List<OfficialFileDTO> getOfficialFiles() {
		return officialFiles;
	}

	public void setOfficialFiles(List<OfficialFileDTO> officialFiles) {
		this.officialFiles = officialFiles;
	}

	@Override
	public String toString() {
		return "OfficialDTO [code=" + code + ", title=" + title + ", content=" + content + ", date=" + date + ", emp="
				+ emp + ", officialFiles=" + officialFiles + "]";
	}

	
}
