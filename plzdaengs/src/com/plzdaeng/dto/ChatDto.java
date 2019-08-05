package com.plzdaeng.dto;

public class ChatDto {
	private String type;
	private int group_id;
	private String user_id;
	private String nickname;
	private String chat_date;
	private String chat_contents;
	
	public ChatDto() {
		super();
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getChat_date() {
		return chat_date;
	}

	public void setChat_date(String chat_date) {
		this.chat_date = chat_date;
	}

	public String getChat_contents() {
		return chat_contents;
	}

	public void setChat_contents(String chat_contents) {
		this.chat_contents = chat_contents;
	}

	@Override
	public String toString() {
		return "ChatDto [group_id=" + group_id + ", user_id=" + user_id + ", chat_date=" + chat_date
				+ ", chat_contents=" + chat_contents + "]";
	}
	
	
}
