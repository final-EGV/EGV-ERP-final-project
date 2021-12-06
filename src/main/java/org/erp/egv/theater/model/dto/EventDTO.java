package org.erp.egv.theater.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT")
@SequenceGenerator(name = "EVENT_SEQ_GENERATOR",
					sequenceName = "SEQ_EVENT_CODE",
					initialValue = 1,
					allocationSize = 1)
public class EventDTO implements Serializable {
	private static final long serialVersionUID = -4503439553474230425L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "EVENT_SEQ_GENERATOR")
	@Column(name = "EVENT_CODE")
	private int code;
	
	@Column(name = "EVENT_NAME")
	private String name;

	@Column(name = "START_DATETIME")
	private String startDatetime;

	@Column(name = "END_DATETIME")
	private String endDatetime;

	@Column(name = "RENTAL_COMPANY", nullable = true)
	private String rentalCompany;

	@ManyToOne
	@JoinColumn(name = "MOVIE_CODE", nullable = true)
	private MovieDTO movieAndEvent;

	@Column(name = "EVENT_PRODUCT", nullable = true)
	private String product;

	public EventDTO() {
	}

	public EventDTO(int code, String name, String startDatetime, String endDatetime, String rentalCompany,
			MovieDTO movieAndEvent, String product) {
		this.code = code;
		this.name = name;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.rentalCompany = rentalCompany;
		this.movieAndEvent = movieAndEvent;
		this.product = product;
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

	public String getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(String startDatetime) {
		this.startDatetime = startDatetime;
	}

	public String getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(String endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getRentalCompany() {
		return rentalCompany;
	}

	public void setRentalCompany(String rentalCompany) {
		this.rentalCompany = rentalCompany;
	}

	public MovieDTO getMovieAndEvent() {
		return movieAndEvent;
	}

	public void setMovieAndEvent(MovieDTO movieAndEvent) {
		this.movieAndEvent = movieAndEvent;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "EventDTO [code=" + code + ", name=" + name + ", startDatetime=" + startDatetime + ", endDatetime="
				+ endDatetime + ", rentalCompany=" + rentalCompany + ", movieAndEvent=" + movieAndEvent.getName() + ", product="
				+ product + "]";
	}

}
