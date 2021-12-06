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
@Table(name = "SCREENING_SCHEDULE")
@SequenceGenerator(name = "SCREENING_SCHEDULE_SEQ_GENERATOR",
					sequenceName = "SEQ_SCREENING_CODE",
					initialValue = 1,
					allocationSize = 1)
public class ScreeningScheduleDTO implements Serializable {
	private static final long serialVersionUID = -1950767068130331713L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "SCREENING_SCHEDULE_SEQ_GENERATOR")
	@Column(name = "SCREENING_CODE")
	private int code;
	
	@ManyToOne
	@JoinColumn(name = "MOVIE_CODE")
	private MovieDTO movieAndScreening;
	
	@ManyToOne
	@JoinColumn(name = "THEATER_CODE")
	private TheaterDTO theater;
	
	@Column(name = "SCREENING_START")
	private String screeningStart;
	
	@Column(name = "SCREENING_END")
	private String screeningEnd;

	public ScreeningScheduleDTO() {
	}

	public ScreeningScheduleDTO(int code, MovieDTO movieAndScreening, TheaterDTO theater, String screeningStart,
			String screeningEnd) {
		this.code = code;
		this.movieAndScreening = movieAndScreening;
		this.theater = theater;
		this.screeningStart = screeningStart;
		this.screeningEnd = screeningEnd;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public MovieDTO getMovieAndScreening() {
		return movieAndScreening;
	}

	public void setMovieAndScreening(MovieDTO movieAndScreening) {
		this.movieAndScreening = movieAndScreening;
	}

	public TheaterDTO getTheater() {
		return theater;
	}

	public void setTheater(TheaterDTO theater) {
		this.theater = theater;
	}

	public String getScreeningStart() {
		return screeningStart;
	}

	public void setScreeningStart(String screeningStart) {
		this.screeningStart = screeningStart;
	}

	public String getScreeningEnd() {
		return screeningEnd;
	}

	public void setScreeningEnd(String screeningEnd) {
		this.screeningEnd = screeningEnd;
	}

	@Override
	public String toString() {
		return "ScreeningScheduleDTO [code=" + code + ", movieAndScreening=" + movieAndScreening.getName() + ", theater="
				+ theater.getName() + ", screeningStart=" + screeningStart + ", screeningEnd=" + screeningEnd + "]";
	}

}
