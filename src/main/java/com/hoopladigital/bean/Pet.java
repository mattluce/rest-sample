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

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}
}
