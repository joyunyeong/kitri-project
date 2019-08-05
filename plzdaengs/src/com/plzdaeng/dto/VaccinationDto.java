package com.plzdaeng.dto;

public class VaccinationDto {
	private String vaccin_code;
	private String vaccin_name;
	private AnimalDto animalDto;
	private int vaccin_cycle;
	
	public VaccinationDto() {
		super();
	}
	public String getVaccin_code() {
		return vaccin_code;
	}
	public void setVaccin_code(String vaccin_code) {
		this.vaccin_code = vaccin_code;
	}
	public String getVaccin_name() {
		return vaccin_name;
	}
	public void setVaccin_name(String vaccin_name) {
		this.vaccin_name = vaccin_name;
	}
	public AnimalDto getAnimalDto() {
		return animalDto;
	}
	public void setAnimalDto(AnimalDto animalDto) {
		this.animalDto = animalDto;
	}
	public int getVaccin_cycle() {
		return vaccin_cycle;
	}
	public void setVaccin_cycle(int vaccin_cycle) {
		this.vaccin_cycle = vaccin_cycle;
	}
	
	@Override
	public String toString() {
		return "VaccinationDto [vaccin_code=" + vaccin_code + ", vaccin_name=" + vaccin_name + ", animalDto="
				+ animalDto + ", vaccin_cycle=" + vaccin_cycle + "]";
	}
}
