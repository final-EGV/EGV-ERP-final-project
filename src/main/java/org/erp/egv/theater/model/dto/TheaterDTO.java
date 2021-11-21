package org.erp.egv.theater.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "THEATER")
@SequenceGenerator(name = "THEATER_SEQ_GENERATOR",
					sequenceName = "SEQ_THEATER_CODE",
					initialValue = 1,
					allocationSize = 1)
public class TheaterDTO implements Serializable {
	private static final long serialVersionUID = -5999045906549542109L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "THEATER_SEQ_GENERATOR")
	@Column(name = "THEATER_CODE")
	private int code;
	
	@Column(name = "THEATER_NAME")
	private String name;
	
	@Column(name = "OCCUPANCY")
	private int occupancy;

	public TheaterDTO() {
	}

	public TheaterDTO(int code, String name, int occupancy) {
		this.code = code;
		this.name = name;
		this.occupancy = occupancy;
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

	public int getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(int occupancy) {
		this.occupancy = occupancy;
	}

	@Override
	public String toString() {
		return "TheaterDTO [code=" + code + ", name=" + name + ", occupancy=" + occupancy + "]";
	}

}
