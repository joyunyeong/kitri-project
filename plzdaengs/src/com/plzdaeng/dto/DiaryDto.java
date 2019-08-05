package com.plzdaeng.dto;

import java.sql.Clob;
import java.util.Date;

public class DiaryDto {
	private int diary_number;
	private String user_id;
	private String category_id;
	private String category_id2;
	private String category_name;
	private Date diary_date; // 좀더 정확하게 표현하고자 util의 date 사용
	private String diary_subject;
	private String hashtag;
	private String diary_contents; // ??
	private String diary_img;
	private String location_x;
	private String location_y;
	private Date create_date;
	
	public DiaryDto() {
		super();
	}
	
	public int getDiary_number() {
		return diary_number;
	}
	public void setDiary_number(int diary_number) {
		this.diary_number = diary_number;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_id2() {
		return category_id2;
	}
	public void setCategory_id2(String category_id2) {
		this.category_id2 = category_id2;
	}
	public Date getDiary_date() {
		return diary_date;
	}
	public void setDiary_date(Date diary_date) {
		this.diary_date = diary_date;
	}
	public String getDiary_subject() {
		return diary_subject;
	}
	public void setDiary_subject(String diary_subject) {
		this.diary_subject = diary_subject;
	}
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public String getDiary_contents() {
		return diary_contents;
	}
	public void setDiary_contents(String diary_contents) {
		this.diary_contents = diary_contents;
	}
	public String getDiary_img() {
		return diary_img;
	}
	public void setDiary_img(String diary_img) {
		this.diary_img = diary_img;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	@Override
	public String toString() {
		return "DiaryDto [diary_number=" + diary_number + ", user_id=" + user_id + ", category_id=" + category_id
				+ ", category_id2=" + category_id2 + ", category_name=" + category_name + ", diary_date=" + diary_date
				+ ", diary_subject=" + diary_subject + ", hashtag=" + hashtag + ", diary_contents=" + diary_contents
				+ ", diary_img=" + diary_img + ", location_x=" + location_x + ", location_y=" + location_y
				+ ", create_date=" + create_date + "]";
	}	
}
