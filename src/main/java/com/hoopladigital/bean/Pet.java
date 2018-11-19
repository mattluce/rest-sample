package com.hoopladigital.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

	private Long id;
	private String name;
	private Long personId;

	@JsonCreator
	public Pet(
		@JsonProperty("id") Long id,
		@JsonProperty("name") String name,
		@JsonProperty("personId") Long personId) {
		this.id = id;
		this.name = name;
		this.personId = personId;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getPersonId() {
		return personId;
	}
}
