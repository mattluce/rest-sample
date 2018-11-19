package com.hoopladigital.mapper;

import java.util.List;

import com.hoopladigital.bean.Pet;

public interface PetMapper {

	List<Pet> getPetList(Long personId);
	Pet getPet(Long id);
	void updatePet(Pet Pet);
	void insertPet(Pet Pet);
	void deletePet(Long id);

}
