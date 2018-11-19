package com.hoopladigital.service;

import java.util.List;

import javax.inject.Inject;

import com.hoopladigital.bean.Pet;
import com.hoopladigital.mapper.PetMapper;

public class PetService {
	
	private final PetMapper petMapper;
	
	@Inject
	public PetService(PetMapper petMapper) {
		this.petMapper = petMapper;
	}

	public List<Pet> getPetList(Long personId) {
		return petMapper.getPetList(personId);
	}

	public Pet getPetForPerson(Long personId, Long petId) {
		return petMapper.getPetForPerson(personId,petId);
	}

	public void update(Pet Pet) {
		petMapper.updatePet(Pet);
	}

	public void insert(Pet Pet) {
		petMapper.insertPet(Pet);
	}

	public void delete(Long id) {
		petMapper.deletePet(id);
	}
}
