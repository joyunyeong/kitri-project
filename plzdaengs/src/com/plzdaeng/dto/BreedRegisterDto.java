package com.plzdaeng.dto;

public class BreedRegisterDto {
	private AnimalDto animalDto;
	private String knm;
	private String kindCd;
	
	public BreedRegisterDto() {
		super();
	}

	public AnimalDto getAnimalDto() {
		return animalDto;
	}

	public void setAnimalDto(AnimalDto animalDto) {
		this.animalDto = animalDto;
	}

	public String getKnm() {
		return knm;
	}

	public void setKm(String knm) {
		knm = knm;
	}

	public String getKindCd() {
		return kindCd;
	}

	public void setKindCd(String kindCd) {
		this.kindCd = kindCd;
	}

	@Override
	public String toString() {
		return "BreedRegisterDto [animalDto=" + animalDto + ", kNm=" + knm + ", kindCd=" + kindCd + "]";
	}
	
}
