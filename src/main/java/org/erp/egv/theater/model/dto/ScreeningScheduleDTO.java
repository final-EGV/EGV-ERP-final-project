package org.erp.egv.theater.model.dto;

import org.erp.egv.theater.entity.ScreeningSchedule;

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
public class ScreeningScheduleDTO {
	
	private int code;
	private MovieDTO movie;
	private TheaterDTO theater;
	private String screeningStart;
	private String screeningEnd;
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertToDto")
	public ScreeningScheduleDTO(int code, MovieDTO movie, TheaterDTO theater, String screeningStart,
			String screeningEnd) {
		this.code = code;
		this.movie = movie;
		this.theater = theater;
		this.screeningStart = screeningStart;
		this.screeningEnd = screeningEnd;
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
	public ScreeningSchedule toEntity() {
		return ScreeningSchedule.converter()
				.movie(movie.toEntityWithId())
				.theater(theater.toEntityWithId())
				.screeningStart(screeningStart)
				.screeningEnd(screeningEnd)
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
	public ScreeningSchedule toEntityWithId() {
		return ScreeningSchedule.converter()
				.code(code)
				.movie(movie.toEntityWithId())
				.theater(theater.toEntityWithId())
				.screeningStart(screeningStart)
				.screeningEnd(screeningEnd)
				.convertWithId();
	}

}
