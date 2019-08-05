package com.plzdaeng.dto;

import java.util.List;

public class PetDto {
	private UserDto userDto;
	private String pet_name;
	private BreedDto breedDto;
	
	private String pet_gender;
	private String birth_date;
	private String pet_type;
	private String pet_img;
	
	//백신리스트
	private List<TakeVaccinDto> takeVaccinList;

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public String getPet_name() {
		return pet_name;
	}

	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}

	public BreedDto getBreedDto() {
		return breedDto;
	}

	public void setBreedDto(BreedDto breedDto) {
		this.breedDto = breedDto;
	}

	public String getPet_gender() {
		return pet_gender;
	}

	public void setPet_gender(String pet_gender) {
		this.pet_gender = pet_gender;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getPet_type() {
		return pet_type;
	}

	public void setPet_type(String pet_type) {
		this.pet_type = pet_type;
	}

	public String getPet_img() {
		return pet_img;
	}

	public void setPet_img(String pet_img) {
		this.pet_img = pet_img;
	}

	public List<TakeVaccinDto> getTakeVaccinList() {
		return takeVaccinList;
	}

	public void setTakeVaccinList(List<TakeVaccinDto> takeVaccinList) {
		this.takeVaccinList = takeVaccinList;
	}

	@Override
	public String toString() {
		return "PetDto [userDto=" + userDto + ", pet_name=" + pet_name + ", breedDto=" + breedDto + ", pet_gender="
				+ pet_gender + ", birth_date=" + birth_date + ", pet_type=" + pet_type + ", pet_img=" + pet_img
				+ ", takeVaccinList=" + takeVaccinList + "]";
	}
	
}
