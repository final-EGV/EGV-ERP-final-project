package org.erp.egv.theater.model.dto;

import org.erp.egv.theater.entity.Theater;

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
public class TheaterDTO {

	private int code;
	private String name;
	private int occupancy;
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertToDto")
	public TheaterDTO(int code, String name, int occupancy) {
		this.code = code;
		this.name = name;
		this.occupancy = occupancy;
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
	public Theater toEntity() {
		return Theater.converter()
				.name(name)
				.occupancy(occupancy)
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
	public Theater toEntityWithId() {
		return Theater.converter()
				.code(code)
				.name(name)
				.occupancy(occupancy)
				.convertWithId();
	}

}
