package org.erp.egv.theater.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.erp.egv.theater.model.dto.TheaterDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "THEATER")
@SequenceGenerator(name = "THEATER_SEQ_GENERATOR",
					sequenceName = "SEQ_THEATER_CODE",
					initialValue = 1,
					allocationSize = 1)
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theater {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "THEATER_SEQ_GENERATOR")
	@Column(name = "THEATER_CODE")
	private int code;
	
	@Column(name = "THEATER_NAME")
	private String name;
	
	@Column(name = "OCCUPANCY")
	private int occupancy;

	@Builder(builderMethodName = "converter", buildMethodName = "convertWithoutId")
	public Theater(String name, int occupancy) {
		this.name = name;
		this.occupancy = occupancy;
	}
	
	@Builder(builderMethodName = "converter", buildMethodName = "convertWithId")
	public Theater(int code, String name, int occupancy) {
		this.code = code;
		this.name = name;
		this.occupancy = occupancy;
	}
	
	/**
	 * Updates the value of all field variables in this Entity instance. 
	 * <p>
	 * Notice that this method updates the entity's information only by taking over all field
	 * variables as parameters. The feature updating an entity by accessing each single field
	 * variable is not supported.
	 * 
	 * @param name name to update
	 * @param occupancy occupancy to update
	 */
	public void update(String name, int occupancy) {
		this.name = name;
		this.occupancy = occupancy;
	}
	
	/**
	 * Converts an Entity object to a DTO object.
	 * 
	 * @return a DTO mapped by this Entity instance.
	 */
	public TheaterDTO toDto() {
		return TheaterDTO.converter()
				.code(code)
				.name(name)
				.occupancy(occupancy)
				.convertToDto();
	}

}