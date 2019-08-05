package com.plzdaeng.dto;

import java.util.Date;

public class DiaryImgDto {
	private String user_id;
	private Date diary_date;
	private String image_name;
	
	public DiaryImgDto() {
		super();
	}
	
	public Date getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(Date diary_date) {
		this.diary_date = diary_date;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "DiaryImgDto [user_id=" + user_id + ", diary_date=" + diary_date + ", image_name=" + image_name+ "]";
	}
	
	
}
