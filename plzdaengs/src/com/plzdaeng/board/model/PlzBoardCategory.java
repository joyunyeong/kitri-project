package com.plzdaeng.board.model;

public class PlzBoardCategory {

	String board_category_id ; /* 보드카테고리ID */
	String board_category_name ; /* 보드카테고리이름 */
	String board_category_descripton; /* 보드카테고리설명 */
	
	
	public PlzBoardCategory(String board_category_id, String board_category_name, String board_category_descripton) {
		super();
		this.board_category_id = board_category_id;
		this.board_category_name = board_category_name;
		this.board_category_descripton = board_category_descripton;
	}


	public PlzBoardCategory() {
		super();
	}

	

	public String getBoard_category_id() {
		return board_category_id;
	}


	public void setBoard_category_id(String board_category_id) {
		this.board_category_id = board_category_id;
	}


	public String getBoard_category_name() {
		return board_category_name;
	}


	public void setBoard_category_name(String board_category_name) {
		this.board_category_name = board_category_name;
	}


	public String getBoard_category_descripton() {
		return board_category_descripton;
	}


	public void setBoard_category_descripton(String board_category_descripton) {
		this.board_category_descripton = board_category_descripton;
	}


	@Override
	public String toString() {
		return "PlzBoardCategory [board_category_id=" + board_category_id + ", board_category_name="
				+ board_category_name + ", board_category_descripton=" + board_category_descripton + "]";
	}
	
	
	
	
	
	
}
