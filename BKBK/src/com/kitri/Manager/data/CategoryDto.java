package com.kitri.Manager.data;

// 도서종류 Dto

public class CategoryDto {

	private int bookCtg;		// 도서종류코드
	private String bookCtgName;	// 도서종류명칭

	public int getBookCtg() {
		return bookCtg;
	}

	public void setBookCtg(int bookCtg) {
		this.bookCtg = bookCtg;
	}

	public String getBookCtgName() {
		return bookCtgName;
	}

	public void setBookCtgName(String bookCtgName) {
		this.bookCtgName = bookCtgName;
	}

}
