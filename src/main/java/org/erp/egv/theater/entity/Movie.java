package org.erp.egv.theater.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.erp.egv.theater.model.dto.MovieDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "MOVIE")
@SequenceGenerator(name = "MOVIE_SEQ_GENERATOR",
					sequenceName = "SEQ_MOVIE_CODE",
					initialValue = 1,
					allocationSize = 1)
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "MOVIE_SEQ_GENERATOR")
	@Column(name = "MOVIE_CODE")
	private int code;
	
	@Column(name = "MOVIE_NAME")
	private String name;

	@Column(name = "OPENING_DATE")
	private java.sql.Date openingDate;

	@Column(name = "RUNNINGTIME")
	private int runningTime;

	@Column(name = "GRADE")
	private String grade;

	@Column(name = "GENRE")
	private String genre;

	@Column(name = "DISTRIBUTOR")
	private String distributor;

	@Column(name = "DIRECTOR")
	private String director;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "POSTER_ORIG_NAME")
	private String posterOrigName;

	@Column(name = "POSTER_UUID_NAME")
	private String posterUuidName;

	@Column(name = "POSTER_IMG_PATH")
	private String posterImgPath;

	@Column(name = "OPENING_YN", columnDefinition = "default = 'N'")
	private String openingYn;

	@Builder(builderMethodName = "converter", buildMethodName = "convertWithoutId")
	public Movie(String name, Date openingDate, int runningTime, String grade, String genre, String distributor,
			String director, String country, String posterOrigName, String posterUuidName, String posterImgPath,
			String openingYn) {
		this.name = name;
		this.openingDate = openingDate;
		this.runningTime = runningTime;
		this.grade = grade;
		this.genre = genre;
		this.distributor = distributor;
		this.director = director;
		this.country = country;
		this.posterOrigName = posterOrigName;
		this.posterUuidName = posterUuidName;
		this.posterImgPath = posterImgPath;
		this.openingYn = openingYn;
	}
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertWithId")
	public Movie(int code, String name, Date openingDate, int runningTime, String grade, String genre,
			String distributor, String director, String country, String posterOrigName, String posterUuidName,
			String posterImgPath, String openingYn) {
		this.code = code;
		this.name = name;
		this.openingDate = openingDate;
		this.runningTime = runningTime;
		this.grade = grade;
		this.genre = genre;
		this.distributor = distributor;
		this.director = director;
		this.country = country;
		this.posterOrigName = posterOrigName;
		this.posterUuidName = posterUuidName;
		this.posterImgPath = posterImgPath;
		this.openingYn = openingYn;
	}

	/**
	 * Updates the value of all field variables in this Entity instance. 
	 * <p>
	 * Notice that this method updates the entity's information only by taking over all field
	 * variables as parameters. The feature updating an entity by accessing each single field
	 * variable is not supported.
	 * 
	 * @param name name to update
	 * @param openingDate openingDate to update
	 * @param runningTime runningTime to update
	 * @param grade grade to update
	 * @param genre genre to update
	 * @param distributor distributor to update
	 * @param director director to update
	 * @param country country to update
	 * @param posterOrigName posterOrigName to update
	 * @param posterUuidName posterUuidName to update
	 * @param posterImgPath posterImgPath to update
	 * @param openingYn openingYn to update
	 */
	public void update(String name, Date openingDate, int runningTime, String grade, String genre, String distributor,
			String director, String country, String posterOrigName, String posterUuidName, String posterImgPath,
			String openingYn) {
		this.name = name;
		this.openingDate = openingDate;
		this.runningTime = runningTime;
		this.grade = grade;
		this.genre = genre;
		this.distributor = distributor;
		this.director = director;
		this.country = country;
		this.posterOrigName = posterOrigName;
		this.posterUuidName = posterUuidName;
		this.posterImgPath = posterImgPath;
		this.openingYn = openingYn;
	}
	
	/**
	 * Converts an Entity object to a DTO object.
	 * 
	 * @return a DTO mapped by this Entity instance.
	 */
	public MovieDTO toDto() {
		return MovieDTO.converter()
				.code(code)
				.name(name)
				.openingDate(openingDate)
				.runningTime(runningTime)
				.grade(grade)
				.genre(genre)
				.distributor(distributor)
				.director(director)
				.country(country)
				.posterOrigName(posterOrigName)
				.posterUuidName(posterUuidName)
				.posterImgPath(posterImgPath)
				.openingYn(openingYn)
				.convertToDto();
	}
}
