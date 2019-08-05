package com.plzdaeng.user.service;

import java.util.List;

import com.plzdaeng.dto.BreedDto;
import com.plzdaeng.dto.PetDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.dao.PetDao;

public class PetService {
	private PetDao dao;
	
	public PetService() {
		dao = new PetDao();
	}
	
	public int petNameCheck(String petName, String userId) {
		return dao.countByPetName(petName, userId);
	}

	public List<BreedDto> selectKind(String name, String animalCode) {
		
		return dao.selectKindByName(name, animalCode);
	}

	public int petRegister(PetDto pet) {
		return dao.insert(pet);
	}

	public List<PetDto> petList(UserDto user) {
		return dao.selectPetByUserId(user);
	}

	public PetDto petDetail(String user_id, String petName) {
		return dao.selectByPetName(user_id, petName);
	}

	public int petModify(PetDto pet) {		
		return dao.update(pet);
	}

}
