package com.kitri.Book.rent;

import java.util.Date;

// 도서 DTO

public class BookDTO {
	private int rowNum;
	private String book_ctg_name;
	private int one;
	
	private String bookNum; // 도서코드
	private int genreNum; // 장르코드
	private String bookName; // 도서명
	private int bookCtg; // 종류코드
	private String author; // 작가
	private String publisher; // 출판사
	private int bookPrice; // 정가
	private char rentState; // 대출현황
	private char replace; // 교체요망z
	public CategoryDTO ctgDTO = new CategoryDTO();
	public GenreDTO genDTO = new GenreDTO();

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getBook_ctg_name() {
		return book_ctg_name;
	}

	public void setBook_ctg_name(String book_ctg_name) {
		this.book_ctg_name = book_ctg_name;
	}

	public int getOne() {
		return one;
	}

	public void setOne(int one) {
		this.one = one;
	}
	
	public String getBookNum() {
		return bookNum;
	}

	public void setBookNum(String bookNum) {
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

	public CategoryDTO getCtgDTO() {
		return ctgDTO;
	}

	public void setCtgDTO(CategoryDTO ctgDTO) {
		this.ctgDTO = ctgDTO;
	}

	public GenreDTO getGenDTO() {
		return genDTO;
	}

	public void setGenDTO(GenreDTO genDTO) {
		this.genDTO = genDTO;
	}
	
	@Override
	public String toString() {
		return bookName;
	}

}
