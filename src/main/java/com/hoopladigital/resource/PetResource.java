package com.hoopladigital.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PersonService;
import com.hoopladigital.service.PetService;

@Path("/people/{personId}/pets")
@Produces(MediaType.APPLICATION_JSON)
public class PetResource {
	
	private PetService petService;
	private PersonService personService;
	
	@Inject
	public PetResource(PetService petService, PersonService personService) {
		this.petService = petService;
		this.personService = personService;
	}

	@GET
	public List<Pet> getPetList(@PathParam("personId") Long personId) {
		return petService.getPetList(personId);
	}

	@GET
	@Path("/{id}")
	public Response getPet(@PathParam("personId") Long personId,
						   @PathParam("id") Long id) {

		final Pet pet = findPet(personId,id);

		return Response.ok().entity(pet).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insert(@PathParam("personId") Long personId, Pet pet) {

		if (personService.getPerson(personId) == null) {
			throw new NotFoundException();
		}

		pet.setPersonId(personId);
		petService.insert(pet);
		return Response.status(Response.Status.CREATED).entity(pet).build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("personId") Long personId, @PathParam("id") Long id, Pet pet) {

		final Pet existingPet = findPet(personId,id);

		existingPet.setName(pet.getName());

		petService.update(existingPet);
		return Response.ok().entity(existingPet).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("personId") Long personId, @PathParam("id") Long id) {

		findPet(personId, id);

		petService.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	private Pet findPet(Long personId, Long id) {
		final Pet existingPet = petService.getPetForPerson(personId, id);
		if (existingPet == null) {
			throw new NotFoundException();
		}
		return existingPet;
	}
}
