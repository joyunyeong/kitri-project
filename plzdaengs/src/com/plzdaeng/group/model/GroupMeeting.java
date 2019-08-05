package com.plzdaeng.group.model;

import java.util.Date;

public class GroupMeeting {
	
	
	private int meeting_id;
	private int group_id;
	private String meeting_title;
	private String meeting_description;
	private Date meeting_date;
	private String location_x;
	private String location_y;

	
	public GroupMeeting(int meeting_id, int group_id, String meeting_title, String meeting_description,
			Date meeting_date, String location_x, String location_y) {
		super();
		this.meeting_id = meeting_id;
		this.group_id = group_id;
		this.meeting_title = meeting_title;
		this.meeting_description = meeting_description;
		this.meeting_date = meeting_date;
		this.location_x = location_x;
		this.location_y = location_y;
	}
	
	
	public int getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(int meeting_id) {
		this.meeting_id = meeting_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getMeeting_title() {
		return meeting_title;
	}
	public void setMeeting_title(String meeting_title) {
		this.meeting_title = meeting_title;
	}
	public String getMeeting_description() {
		return meeting_description;
	}
	public void setMeeting_description(String meeting_description) {
		this.meeting_description = meeting_description;
	}
	public Date getMeeting_date() {
		return meeting_date;
	}
	public void setMeeting_date(Date meeting_date) {
		this.meeting_date = meeting_date;
	}
	public String getLocation_x() {
		return location_x;
	}
	public void setLocation_x(String location_x) {
		this.location_x = location_x;
	}
	public String getLocation_y() {
		return location_y;
	}
	public void setLocation_y(String location_y) {
		this.location_y = location_y;
	}
	
	
	
	
}
