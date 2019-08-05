package com.plzdaeng.board.model;

/**
 * @author Administrator
 *
 */
public class PlzReply {
	int reply_id;
	int post_id;
	String board_category_id;
	String user_id;
	String user_name;
	String reply_contents;
	String creat_date;
	int rCurPage;
	int rPagescale;
	int no ;	//순번
	
	


	public PlzReply(int reply_id, int post_id, String board_category_id, String user_id, String user_name,
			String reply_contents, String creat_date, int rCurPage, int rPagescale, int no) {
		super();
		this.reply_id = reply_id;
		this.post_id = post_id;
		this.board_category_id = board_category_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.reply_contents = reply_contents;
		this.creat_date = creat_date;
		this.rCurPage = rCurPage;
		this.rPagescale = rPagescale;
		this.no = no;
	}

	public PlzReply() {
		super();
	}

	
	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getBoard_category_id() {
		return board_category_id;
	}
	public void setBoard_category_id(String board_category_id) {
		this.board_category_id = board_category_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getReply_contents() {
		return reply_contents;
	}
	public void setReply_contents(String reply_contents) {
		this.reply_contents = reply_contents;
	}
	public String getCreat_date() {
		return creat_date;
	}
	public void setCreat_date(String creat_date) {
		this.creat_date = creat_date;
	}
	public int getrCurPage() {
		return rCurPage;
	}
	public void setrCurPage(int rCurPage) {
		this.rCurPage = rCurPage;
	}
	public int getrPagescale() {
		return rPagescale;
	}
	public void setrPagescale(int rPagescale) {
		this.rPagescale = rPagescale;
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "PlzReply [reply_id=" + reply_id + ", post_id=" + post_id + ", board_category_id=" + board_category_id
				+ ", user_id=" + user_id + ", user_name=" + user_name + ", reply_contents=" + reply_contents
				+ ", creat_date=" + creat_date + ", rCurPage=" + rCurPage + ", rPagescale=" + rPagescale + ", no=" + no
				+ "]";
	}

	
	
	
}
