package com.plzdaeng.dto;

public class AnimalDto {
	private String animal_code;
	private String animal_name;
	
	public AnimalDto() {
		super();
	}
	public AnimalDto(String animal_code) {
		super();
		this.animal_code = animal_code;
	}
	public String getAnimal_code() {
		return animal_code;
	}
	public void setAnimal_code(String animal_code) {
		this.animal_code = animal_code;
	}
	public String getAnimal_name() {
		return animal_name;
	}
	public void setAnimal_name(String animal_name) {
		this.animal_name = animal_name;
	}
	
	@Override
	public String toString() {
		return "AnimalDto [animal_code=" + animal_code + ", animal_name=" + animal_name + "]";
	}
}
