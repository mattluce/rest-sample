package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

	private final PersonService personService;

	@Inject
	public PersonResource(final PersonService personService) {
		this.personService = personService;
	}

	@GET
	public List<Person> getPersonList() {
		return personService.getPersonList();
	}

	@GET
	@Path("/{id}")
	public Response getPerson(@PathParam("id") Long id) {
		final Person person = findPerson(id);
		return Response.ok().entity(person).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Person person, @PathParam("id") Long id) {

		findPerson(id); // assert

		person.setId(id);
		personService.update(person);
		return Response.ok().entity(personService.getPerson(person.getId())).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(Person person) {
		personService.insert(person);
		return Response.status(Response.Status.CREATED).entity(person).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {

		findPerson(id); // assert

		personService.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	private Person findPerson(Long id) {
		final Person person = personService.getPerson(id);
		if (person == null) {
			throw new NotFoundException();
		}
		return person;
	}

}
