package org.erp.egv.theater.model.dto;

import java.io.Serializable;
import java.sql.Date;

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

	@Column(name = "EVENT_DATE")
	private java.sql.Date date;

	@Column(name = "EVENT_START")
	private java.sql.Date eventStart;

	@Column(name = "EVENT_END")
	private java.sql.Date eventEnd;

	@Column(name = "RENTAL_COMPANY", nullable = true)
	private String rentalCompany;

	@ManyToOne
	@JoinColumn(name = "MOVIE_CODE", nullable = true)
	private MovieDTO movieAndEvent;

	@Column(name = "PRODUCT_CODE", nullable = true)
	private Integer productCode;

	public EventDTO() {
	}

	public EventDTO(int code, String name, Date date, Date eventStart, Date eventEnd, String rentalCompany,
			MovieDTO movieAndEvent, Integer productCode) {
		this.code = code;
		this.name = name;
		this.date = date;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.rentalCompany = rentalCompany;
		this.movieAndEvent = movieAndEvent;
		this.productCode = productCode;
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

	public java.sql.Date getDate() {
		return date;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public java.sql.Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(java.sql.Date eventStart) {
		this.eventStart = eventStart;
	}

	public java.sql.Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(java.sql.Date eventEnd) {
		this.eventEnd = eventEnd;
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

	public Integer getProductCode() {
		return productCode;
	}

	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}

	@Override
	public String toString() {
		return "EventDTO [code=" + code + ", name=" + name + ", date=" + date + ", eventStart=" + eventStart
				+ ", eventEnd=" + eventEnd + ", rentalCompany=" + rentalCompany + ", movieAndEvent=" + movieAndEvent
				+ ", productCode=" + productCode + "]";
	}

}
