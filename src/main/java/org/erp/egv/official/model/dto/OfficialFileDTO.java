package org.erp.egv.official.model.dto;

import java.io.Serializable;

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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "OFFICIAL_FILE")
@SequenceGenerator(name = "OFFICIAL_FILE_SEQ_GENERATOR",
sequenceName = "SEQ_OFFICIAL_FILE_CODE",
initialValue = 1, 
allocationSize = 1)
public class OfficialFileDTO implements Serializable {
	private static final long serialVersionUID = 2311303620165729257L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	generator = "OFFICIAL_FILE_SEQ_GENERATOR")	
	@Column(name = "OFFICIAL_FILE_CODE")
	private int code;
	
	@Column(name = "OFFICIAL_FILE_ORIG_NAME")
	private String officialOrigName;
	
	@Column(name = "OFFICIAL_FILE_UUID_NAME")
	private String officialUuidName;
	
	@Column(name = "OFFICIAL_FILE_IMG_PATH")
	private String officialImgName;
	
	@ManyToOne
	@JoinColumn(name="OFFICIAL_CODE")
	private OfficialDTO official;

	public OfficialFileDTO() {
	}

	public OfficialFileDTO(int code, String officialOrigName, String officialUuidName, String officialImgName,
			OfficialDTO official) {
		this.code = code;
		this.officialOrigName = officialOrigName;
		this.officialUuidName = officialUuidName;
		this.officialImgName = officialImgName;
		this.official = official;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getOfficialOrigName() {
		return officialOrigName;
	}

	public void setOfficialOrigName(String officialOrigName) {
		this.officialOrigName = officialOrigName;
	}

	public String getOfficialUuidName() {
		return officialUuidName;
	}

	public void setOfficialUuidName(String officialUuidName) {
		this.officialUuidName = officialUuidName;
	}

	public String getOfficialImgName() {
		return officialImgName;
	}

	public void setOfficialImgName(String officialImgName) {
		this.officialImgName = officialImgName;
	}

	public OfficialDTO getOfficial() {
		return official;
	}

	public void setOfficial(OfficialDTO official) {
		this.official = official;
	}

	@Override
	public String toString() {
		return "OfficialFileDTO [code=" + code + ", officialOrigName=" + officialOrigName + ", officialUuidName="
				+ officialUuidName + ", officialImgName=" + officialImgName + ", official=" + official.getCode() + "]";
	}

	
}
