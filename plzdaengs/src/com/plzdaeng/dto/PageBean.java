package com.plzdaeng.dto;

import java.util.List;

public class PageBean<T> {
	private int cntPerPage = 10; 			//페이지별 보여줄 글목록수
	private int cntPerPageGroup = 3;		//페이지 그룹당 페이지수
	
	//불러올 글목록
	private int startRow = 1;  		//시작행
	private int endRow = 1;			//마지막행
	private int totalRow = 1;
	
	//페이지 그룹에서의 시작과 끝을 계산
	private int startPage;				//페이지 그룹의 시작페이지
	private int endPage;				//페이지 그룹의 끝페이지
	private int currentPage = 1;	//현재 페이지
	private int maxPage = 1;  		 // 총 페이지 수 
	
	private String url;					//페이지링크 클릭시 요청할 url
	private List<T> list;				//페이지안에 글목록을 저장할 리스트
	
public PageBean() {
		
	}
	public PageBean(int cntPerPage, int cntPerPageGroup, int currentPage, int totalRow, String url) {
		this.cntPerPage = cntPerPage;
		this.cntPerPageGroup = cntPerPageGroup;
		this.currentPage = currentPage;
		this.totalRow = totalRow;
		this.url = url;
		excute();
	}
	
	//값 세팅
	private void excute() {
		//현재 입력받은 페이지당 뿌려줄 글번호

		startRow = (currentPage-1)*cntPerPage + 1;
		if(startRow > totalRow) {
			startRow = totalRow;
		}
		
		endRow = currentPage*cntPerPage;
		if(endRow > totalRow) {
			endRow = totalRow;
		}
		
		//총페이지수 계산
		maxPage = (int)Math.ceil((double)totalRow/cntPerPage);
		
		//페이지 그룹에 보여줄 페이지 수
		//페이지 그룹에서의 시작과 끝을 계산
		startPage = ((currentPage-1)/cntPerPageGroup)*cntPerPageGroup + 1;
		endPage = ((currentPage-1)/cntPerPageGroup + 1) * cntPerPageGroup;
		endPage = endPage > maxPage ? maxPage : endPage;
	}
	
	public int getCntPerPage() {
		return cntPerPage;
	}
	public void setCntPerPage(int cntPerPage) {
		this.cntPerPage = cntPerPage;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PageBean [cntPerPage=" + cntPerPage + ", cntPerPageGroup=" + cntPerPageGroup + ", startRow=" + startRow
				+ ", endRow=" + endRow + ", totalRow=" + totalRow + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", currentPage=" + currentPage + ", maxPage=" + maxPage + ", url=" + url + ", list=" + list + "]";
	}
}
