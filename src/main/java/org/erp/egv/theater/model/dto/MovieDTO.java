package org.erp.egv.theater.model.dto;

import java.sql.Date;

import org.erp.egv.theater.entity.Movie;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MovieDTO {

	private int code;
	private String name;
	private java.sql.Date openingDate;
	private int runningTime;
	private String grade;
	private String genre;
	private String distributor;
	private String director;
	private String country;
	private String posterOrigName;
	private String posterUuidName;
	private String posterImgPath;
	private String openingYn;
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertToDto")
	public MovieDTO(int code, String name, Date openingDate, int runningTime, String grade, String genre,
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
	 * Converts a DTO object to an Entity object.
	 * <p>
	 * Notice that this DTO to Entity conversion is not including primary key field, therefore the
	 * <code>@Id</code> annotated column of returned Entity will remain <code>null</code>.
	 * <p>
	 * Use this conversion method when you want to persist new entity into the persistence context,
	 * or to add new record into the database with given DTO object.
	 * 
	 * @return an Entity mapped by this DTO instance, without primary key(@Id).
	 */
	public Movie toEntity() {
		return Movie.converter()
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
				.convertWithoutId();
	}
	
	/**
	 * Converts a DTO object to an Entity object.
	 * <p>
	 * Notice that this DTO to Entity conversion is including primary key field, therefore the
	 * <code>@Id</code> annotated column of returned Entity does not remain <code>null</code>,
	 * but will be appropriately converted based on the filed value of the given DTO object.
	 * <p>
	 * Use this conversion method when you want to update already being managed entity in the
	 * persistence context, or to update already existing record in the database with given DTO
	 * object.
	 * 
	 * @return an Entity mapped by this DTO instance, with primary key(@Id).
	 */
	public Movie toEntityWithId() {
		return Movie.converter()
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
				.convertWithId();
	}

}
