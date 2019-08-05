package com.kitri.Manager.data;

//도서 장르 Dto

public class GenreDto {

	private int genreNum;		//장르코드
	private String genreName;	//장르명칭

	public int getGenreNum() {
		return genreNum;
	}

	public void setGenreNum(int genreNum) {
		this.genreNum = genreNum;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

}
