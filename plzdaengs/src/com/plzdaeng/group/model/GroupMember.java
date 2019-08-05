package com.plzdaeng.group.model;

public class GroupMember {


	private int group_id;
	private String user_id;
	private String nickName;
	private String member_status;
	private String group_joindate;
	public GroupMember() {
	}
	public GroupMember(int group_id, String user_id, String nickName, String member_status, String group_joindate) {
		super();
		this.group_id = group_id;
		this.user_id = user_id;
		this.nickName = nickName;
		this.member_status = member_status;
		this.group_joindate = group_joindate;
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
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	public String getGroup_joindate() {
		return group_joindate;
	}
	public void setGroup_joindate(String group_joindate) {
		this.group_joindate = group_joindate;
	}
	

	
	
	
	
}
