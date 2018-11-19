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

	public Pet getPet(Long id) {
		return petMapper.getPet(id);

	}

	public void update(Pet Pet) {
		petMapper.updatePet(Pet);
	}

	public Pet insert(Pet Pet) {
		petMapper.insertPet(Pet);
		return Pet;
	}

	public void delete(Long id) {
		petMapper.deletePet(id);
	}
}
