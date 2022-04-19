package org.erp.egv.theater.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.erp.egv.theater.model.dto.EventDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "EVENT")
@SequenceGenerator(name = "EVENT_SEQ_GENERATOR",
					sequenceName = "SEQ_EVENT_CODE",
					initialValue = 1,
					allocationSize = 1)
@Getter
@ToString(exclude = "movie")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {
	
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
	private Movie movie;

	@Column(name = "EVENT_PRODUCT", nullable = true)
	private String product;

	@Builder(builderMethodName = "converter", buildMethodName = "convertWithoutId")
	public Event(String name, String startDatetime, String endDatetime, String rentalCompany, Movie movie,
			String product) {
		this.name = name;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.rentalCompany = rentalCompany;
		this.movie = movie;
		this.product = product;
	}
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertWithId")
	public Event(int code, String name, String startDatetime, String endDatetime, String rentalCompany, Movie movie,
			String product) {
		this.code = code;
		this.name = name;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.rentalCompany = rentalCompany;
		this.movie = movie;
		this.product = product;
	}
	
	/**
	 * Updates the value of all field variables in this Entity instance. 
	 * <p>
	 * Notice that this method updates the entity's information only by taking over all field
	 * variables as parameters. The feature updating an entity by accessing each single field
	 * variable is not supported.
	 * 
	 * @param name name to update
	 * @param startDatetime startDatetime
	 * @param endDatetime endDatetime
	 * @param rentalCompany rentalCompany
	 * @param movie movie
	 * @param product product
	 */
	public void update(String name, String startDatetime, String endDatetime, String rentalCompany, Movie movie,
			String product) {
		this.name = name;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.rentalCompany = rentalCompany;
		this.movie = movie;
		this.product = product;
	}
	
	/**
	 * Converts an Entity object to a DTO object.
	 * 
	 * @return a DTO mapped by this Entity instance.
	 */
	public EventDTO toDto() {
		return EventDTO.converter()
				.code(code)
				.name(name)
				.startDatetime(startDatetime)
				.endDatetime(endDatetime)
				.rentalCompany(rentalCompany)
				.movie(movie != null ? movie.toDto() : null)
				.product(product)
				.convertToDto();
	}

}