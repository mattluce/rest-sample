package com.hoopladigital.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person {

	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;

	@JsonCreator
	public Person(@JsonProperty("id") Long id,
				  @JsonProperty("firstName") String firstName,
				  @JsonProperty("middleName") String middleName,
				  @JsonProperty("lastName") String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) { // mutability yuck!!!!
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}


}
