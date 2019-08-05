package com.plzdaeng.diary.dao;



import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.plzdaeng.dto.DiaryDto;

public class test {
public static void main(String[] args) throws ParseException {
	/* util.date
	DiaryDto dto = new DiaryDto();
	Date date;
	dto.setDiary_date(new Date());
	*/
	
	
	/*
	DiaryDto dto = new DiaryDto();
	Date date = new Date(0);
	date.valueOf("2000-01-01");
	*/
	
	//Date date = new Date(dto.getDiary_date());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	DiaryDto dto = new DiaryDto();
	dto.setDiary_date(sdf.parse("2019/06/12 16:24:33"));
	System.out.println(dto.getDiary_date());
	}
}
