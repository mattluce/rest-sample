package com.hoopladigital.mapper;

import com.hoopladigital.bean.Person;
import com.hoopladigital.test.AbstractMapperTest;
import org.junit.Test;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

public class PersonMapperTest extends AbstractMapperTest {

	@Inject
	private PersonMapper personMapper;

	@Test
	public void should_get_person_list() throws Exception {

		// setup
		final Person george = new Person(1L,"George",null,"Washington");
		//george.setId(1L);
		//george.setFirstName("George");
		//george.setLastName("Washington");

		// run test
		final List<Person> personList = personMapper.getPersonList();

		// verify mocks / capture values

		// assert results
		assertEquals(10, personList.size());
		beanTestHelper.diffBeans(george, personList.get(0));

	}

	@Test
	public void testInsert() {
		final Person person = new Person(null,"Bob","","Smith");
		personMapper.insertPerson(person);

		assertEquals(new Long(11),person.getId());

		final Person person2 = new Person(null,"Bob","","Smith");
		personMapper.insertPerson(person2);

		assertEquals(new Long(12),person2.getId());
	}

	@Test
	public void testUpdate() {

		final Long id = 1L;

		final Person person = new Person(id,"Bob",null,"Smith");
		personMapper.updatePerson(person);

		final Person updatedPerson = personMapper.getPerson(id);

		assertEquals("Bob",updatedPerson.getFirstName());
		assertEquals("Smith",updatedPerson.getLastName());

	}

}
