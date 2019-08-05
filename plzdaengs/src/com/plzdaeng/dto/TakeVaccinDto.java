package com.plzdaeng.dto;

public class TakeVaccinDto {
	private UserDto userDto;
	private PetDto petDto;
	private VaccinationDto vaccinationDto;
	private String takeVaccinDate;
	
	public TakeVaccinDto() {
		super();
	}
	public TakeVaccinDto(UserDto userDto, PetDto petDto, VaccinationDto vaccinationDto, String takeVaccinDate) {
		super();
		this.userDto = userDto;
		this.petDto = petDto;
		this.vaccinationDto = vaccinationDto;
		this.takeVaccinDate = takeVaccinDate;
	}
	public UserDto getUserDto() {
		return userDto;
	}
	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
	public PetDto getPetDto() {
		return petDto;
	}
	public void setPetDto(PetDto petDto) {
		this.petDto = petDto;
	}
	public VaccinationDto getVaccinationDto() {
		return vaccinationDto;
	}
	public void setVaccinationDto(VaccinationDto vaccinationDto) {
		this.vaccinationDto = vaccinationDto;
	}
	public String getTakeVaccinDate() {
		return takeVaccinDate;
	}
	public void setTakeVaccinDate(String takeVaccinDate) {
		this.takeVaccinDate = takeVaccinDate;
	}
	@Override
	public String toString() {
		return "TakeVaccinDto [userDto=" + userDto + ", petDto=" + petDto + ", vaccinationDto=" + vaccinationDto
				+ ", takeVaccinDate=" + takeVaccinDate + "]";
	}
	
	
	
}
