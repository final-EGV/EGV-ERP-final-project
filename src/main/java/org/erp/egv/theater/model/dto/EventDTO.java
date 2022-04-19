package org.erp.egv.theater.model.dto;

import org.erp.egv.theater.entity.Event;

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
public class EventDTO {
	
	private int code;
	private String name;
	private String startDatetime;
	private String endDatetime;
	private String rentalCompany;
	private MovieDTO movie;
	private String product;
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertToDto")
	public EventDTO(int code, String name, String startDatetime, String endDatetime, String rentalCompany,
			MovieDTO movie, String product) {
		this.code = code;
		this.name = name;
		this.startDatetime = startDatetime;
		this.endDatetime = endDatetime;
		this.rentalCompany = rentalCompany;
		this.movie = movie;
		this.product = product;
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
	public Event toEntity() {
		return Event.converter()
				.name(name)
				.startDatetime(startDatetime)
				.endDatetime(endDatetime)
				.rentalCompany(rentalCompany != null ? rentalCompany : null)
				.movie(movie != null ? movie.toEntityWithId() : null)
				.product(product != null ? product : null)
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
	public Event toEntityWithId() {
		return Event.converter()
				.code(code)
				.name(name)
				.startDatetime(startDatetime)
				.endDatetime(endDatetime)
				.rentalCompany(rentalCompany != null ? rentalCompany : null)
				.movie(movie != null ? movie.toEntityWithId() : null)
				.product(product != null ? product : null)
				.convertWithId();
	}

}
