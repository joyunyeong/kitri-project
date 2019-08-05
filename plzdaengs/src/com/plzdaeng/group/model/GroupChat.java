package com.plzdaeng.group.model;

import java.util.Date;

public class GroupChat {

	
	private int group_id;
	private String group_member;
	private Date chat_date;
	private String chat_contents;

	
	public GroupChat(int group_id, String group_member, Date chat_date, String chat_contents) {
		super();
		this.group_id = group_id;
		this.group_member = group_member;
		this.chat_date = chat_date;
		this.chat_contents = chat_contents;
	}
	
	
		public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_member() {
		return group_member;
	}

	public void setGroup_member(String group_member) {
		this.group_member = group_member;
	}

	public Date getChat_date() {
		return chat_date;
	}

	public void setChat_date(Date chat_date) {
		this.chat_date = chat_date;
	}

	public String getChat_contents() {
		return chat_contents;
	}

	public void setChat_contents(String chat_contents) {
		this.chat_contents = chat_contents;
	}


		
	
}
