package com.hoopladigital.resource;

import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.junit.Test;

import com.google.inject.Inject;
import com.hoopladigital.bean.Pet;
import com.hoopladigital.test.AbstractMapperTest;

import static org.junit.Assert.*;

public class PetResourceTest extends AbstractMapperTest {

	@Inject
	private PetResource petResource;


	@Test
	public void testGet() {
		final List<Pet> petList = petResource.getPetList(1L);
		assertEquals(1,petList.size());
		assertEquals("Fido",petList.get(0).getName());
	}

	@Test
	public void testPut() {

		petResource.update(1L,1L,new Pet(null,"Rex",null));
		final Response pet = petResource.getPet(1L, 1L);
		assertEquals("Rex",((Pet)pet.getEntity()).getName());
	}


	@Test
	public void testDelete() {
		assertEquals(Response.Status.OK.getStatusCode(),petResource.getPet(1L,1L).getStatus());
		assertEquals(Response.Status.NO_CONTENT.getStatusCode(),petResource.delete(1L,1L).getStatus());
		try {
			petResource.getPet(1L,1L);
			fail("expected not found");
		} catch (NotFoundException e) {

		}
	}

	@Test
	public void testInsert() {

		final List<Pet> petList = petResource.getPetList(1L);
		assertEquals(1,petList.size());
		petResource.insert(1L,new Pet(null,"Rex",null));
		final List<Pet> newPetList = petResource.getPetList(1L);
		assertEquals(2,newPetList.size());

	}

}
