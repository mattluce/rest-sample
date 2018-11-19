package com.hoopladigital.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hoopladigital.bean.Pet;

public interface PetMapper {

	List<Pet> getPetList(Long personId);
	Pet getPet(Long id);
	Pet getPetForPerson(@Param("personId")Long personId, @Param("petId")Long petId);
	void updatePet(Pet Pet);
	void insertPet(Pet Pet);
	void deletePet(Long id);

}
