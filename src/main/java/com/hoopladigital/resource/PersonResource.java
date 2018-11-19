package com.hoopladigital.resource;

import com.hoopladigital.bean.Person;
import com.hoopladigital.service.PersonService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/people")
@Produces("application/json")
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
		final Person person = personService.getPerson(id);
		if (person == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(person).build();
	}

	@PUT
	public Response update(Person person) {
		final Person existingPerson = personService.getPerson(person.getId());
		if (existingPerson == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		personService.update(person);
		return Response.ok().entity(personService.getPerson(person.getId())).build();
	}

	@POST
	public Response insert(Person person) {
		personService.insert(person);
		return Response.status(Response.Status.CREATED).entity(person).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id) {

		final Person person = personService.getPerson(id);
		if (person == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		personService.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

}
