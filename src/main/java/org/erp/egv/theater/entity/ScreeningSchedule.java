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

import org.erp.egv.theater.model.dto.ScreeningScheduleDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "SCREENING_SCHEDULE")
@SequenceGenerator(name = "SCREENING_SCHEDULE_SEQ_GENERATOR",
					sequenceName = "SEQ_SCREENING_CODE",
					initialValue = 1,
					allocationSize = 1)
@Getter
@ToString(exclude = {"theater", "movie"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreeningSchedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "SCREENING_SCHEDULE_SEQ_GENERATOR")
	@Column(name = "SCREENING_CODE")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "MOVIE_CODE")
	private Movie movie;
	
	@ManyToOne
	@JoinColumn(name = "THEATER_CODE")
	private Theater theater;
	
	@Column(name = "SCREENING_START")
	private String screeningStart;
	
	@Column(name = "SCREENING_END")
	private String screeningEnd;

	@Builder(builderMethodName = "converter", buildMethodName = "convertWithoutId")
	public ScreeningSchedule(Movie movie, Theater theater, String screeningStart, String screeningEnd) {
		this.movie = movie;
		this.theater = theater;
		this.screeningStart = screeningStart;
		this.screeningEnd = screeningEnd;
	}
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertWithId")
	public ScreeningSchedule(int code, Movie movie, Theater theater, String screeningStart, String screeningEnd) {
		this.code = code;
		this.movie = movie;
		this.theater = theater;
		this.screeningStart = screeningStart;
		this.screeningEnd = screeningEnd;
	}

	/**
	 * Updates the value of all field variables in this Entity instance. 
	 * <p>
	 * Notice that this method updates the entity's information only by taking over all field
	 * variables as parameters. The feature updating an entity by accessing each single field
	 * variable is not supported.
	 * 
	 * @param movie movie to update
	 * @param theater theater to update
	 * @param screeningStart screeningStart to update
	 * @param screeningEnd screeningEnd to update
	 */
	public void update(Movie movie, Theater theater, String screeningStart, String screeningEnd) {
		this.movie = movie;
		this.theater = theater;
		this.screeningStart = screeningStart;
		this.screeningEnd = screeningEnd;
	}
	
	/**
	 * Converts an Entity object to a DTO object.
	 * 
	 * @return a DTO mapped by this Entity instance.
	 */
	public ScreeningScheduleDTO toDto() {
		return ScreeningScheduleDTO.converter()
				.code(code)
				.movie(movie != null ? movie.toDto() : null)
				.theater(theater != null ? theater.toDto() : null)
				.screeningStart(screeningStart)
				.screeningEnd(screeningEnd)
				.convertToDto();
	}

}