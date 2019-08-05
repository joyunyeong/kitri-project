package com.kitri.Statistics;

public class StatBookDto {
	
	private int ranking;
	private String Book_name;
	private String genre_name;
	private String book_ctg_name;
	
	//그래프용
	//도서 대여 순위
	private String bookName;
	private int totalcount;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getRanking() {
		return ranking;
	}
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	public String getBook_name() {
		return Book_name;
	}
	public void setBook_name(String book_name) {
		Book_name = book_name;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public String getBook_ctg_name() {
		return book_ctg_name;
	}
	public void setBook_ctg_name(String book_ctg_name) {
		this.book_ctg_name = book_ctg_name;
	}
	
	
	
}
