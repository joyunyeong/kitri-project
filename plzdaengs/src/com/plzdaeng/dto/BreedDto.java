package com.plzdaeng.dto;

public class BreedDto {
	private AnimalDto animalDto;
	private String breed_code;
	private String breed_name;
	
	public BreedDto() {
		super();
	}
	
	public BreedDto(AnimalDto animalDto, String breed_code, String breed_name) {
		super();
		this.animalDto = animalDto;
		this.breed_code = breed_code;
		this.breed_name = breed_name;
	}

	public AnimalDto getAnimalDto() {
		return animalDto;
	}

	public void setAnimalDto(AnimalDto animalDto) {
		this.animalDto = animalDto;
	}

	public String getBreed_code() {
		return breed_code;
	}

	public void setBreed_code(String breed_code) {
		this.breed_code = breed_code;
	}

	public String getBreed_name() {
		return breed_name;
	}

	public void setBreed_name(String breed_name) {
		this.breed_name = breed_name;
	}

	@Override
	public String toString() {
		return "BreedDto [animalDto=" + animalDto + ", breed_code=" + breed_code + ", breed_name=" + breed_name + "]";
	}
	
}
