package com.hoopladigital.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.service.PetService;

@Produces("application/json")
@Path("/people/{personId}/")
public class PetResource {
	
	private PetService petService;
	
	@Inject
	public PetResource(PetService petService) {
		this.petService = petService;
	}

	@GET
	@Path("/pets")
	public List<Pet> getPetList(@PathParam("personId") Long personId) { // todo, check for person
		return petService.getPetList(personId);
	}

	@GET
	@Path("/pets/{id}")
	public Response getPet(@PathParam("id") Long id) {
		final Pet Pet = petService.getPet(id);
		if (Pet == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok().entity(Pet).build();
	}

	@PUT
	@Path("/pets")
	public Response update(Pet Pet) {
		final Pet existingPet = petService.getPet(Pet.getId());
		if (existingPet == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		petService.update(Pet);
		return Response.ok().entity(petService.getPet(Pet.getId())).build();
	}

	@POST
	@Path("/pets")
	public Response insert(Pet Pet) {
		petService.insert(Pet);
		return Response.ok().entity(Pet).build();
	}

	@DELETE
	@Path("/pets/{id}")
	public Response delete(@PathParam("id") Long id) {

		final Pet Pet = petService.getPet(id);
		if (Pet == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		petService.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
