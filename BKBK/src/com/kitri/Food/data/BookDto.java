package com.kitri.Food.data;

import java.util.Date;

// 도서 Dto

public class BookDto {

	private Date bookNum; // 도서코드
	private int genreNum; // 장르코드
	private String bookName; // 도서명
	private int bookCtg; // 종류코드
	private String author; // 작가
	private String publisher; // 출판사
	private int bookPrice; // 정가
	private char rentState; // 대출현황
	private char replace; // 교체요망
	private char state; // 상태
	public CategoryDto ctgDto = new CategoryDto();
	public GenreDto genDto = new GenreDto();

	public Date getBookNum() {
		return bookNum;
	}

	public void setBookNum(Date bookNum) {
		this.bookNum = bookNum;
	}

	public int getGenreNum() {
		return genreNum;
	}

	public void setGenreNum(int genreNum) {
		this.genreNum = genreNum;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getBookCtg() {
		return bookCtg;
	}

	public void setBookCtg(int bookCtg) {
		this.bookCtg = bookCtg;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public char getRentState() {
		return rentState;
	}

	public void setRentState(char rentState) {
		this.rentState = rentState;
	}

	public char getReplace() {
		return replace;
	}

	public void setReplace(char replace) {
		this.replace = replace;
	}

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}

}
