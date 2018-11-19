package com.hoopladigital.resource;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.google.inject.Inject;
import com.hoopladigital.bean.Person;

import com.hoopladigital.test.AbstractMapperTest;

import static org.junit.Assert.*;

public class PersonResourceTest extends AbstractMapperTest {


	@Inject
	private PersonResource personResource;

	@Test
	public void getList() {

		assertEquals(10,personResource.getPersonList().size());
	}

	@Test
	public void testPut() {

		personResource.update(new Person(null, "Tom","Rex","Jones"),1L);
		final Response person = personResource.getPerson(1L);
		assertEquals("Tom",((Person)person.getEntity()).getFirstName());
		assertEquals("Rex",((Person)person.getEntity()).getMiddleName());
		assertEquals("Jones",((Person)person.getEntity()).getLastName());
	}


	@Test
	public void testDelete() {
		assertEquals(Response.Status.OK.getStatusCode(),personResource.getPerson(1L).getStatus());
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(),personResource.delete(1L).getStatus());
		try {
			personResource.getPerson(1L);
			fail("expected not found");
		} catch (NotFoundException e) {

		}
	}

	@Test
	public void testInsert() {

		final List<Person> personList = personResource.getPersonList();
		assertEquals(10,personList.size());
		personResource.insert(new Person(null, "Tom","Rex","Jones"));
		final List<Person> newPersonList = personResource.getPersonList();
		assertEquals(11,newPersonList.size());

	}

}
