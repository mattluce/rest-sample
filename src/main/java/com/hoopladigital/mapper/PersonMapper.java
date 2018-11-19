package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;

import java.util.List;

public interface PersonMapper {
	List<Person> getPersonList();
	Person getPerson(Long id);
	void updatePerson(Person person);
	void insertPerson(Person person);
	void deletePerson(Long id);
}
