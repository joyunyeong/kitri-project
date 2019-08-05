package com.kitri.Book.rent;

//도서 장르 Dto

public class GenreDTO {

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
