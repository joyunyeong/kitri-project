package com.plzdaeng.diary.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.plzdaeng.diary.dao.diaryDao;
import com.plzdaeng.dto.DiaryDto;
import com.plzdaeng.dto.DiaryImgDto;
import com.plzdaeng.dto.UserDto;

public class DiaryService {
	private diaryDao dao;
	
	public DiaryService() {
		dao = new diaryDao();
	}
	
	public int enrollDiary(UserDto user, DiaryDto dto) { // 다이어리 등록용
		System.out.println();
		System.out.println("> " + user.getUser_id() + "님 접속!");
		System.out.println("> DiaryService 이동OK > insertDiary로 접근합니다.");
		return dao.insertDiary(user, dto);
	}

	public List<DiaryDto> initDataByMonth(String date, UserDto user) {
		return dao.selectAllByMonth(date, user);
	}


	public int enrollImg(UserDto user, DiaryImgDto diaryImgDto) {
		System.out.println();
		System.out.println("> " + user.getUser_id() + "님 접속!");
		System.out.println("> DiaryService 이동OK > updateImg로 접근합니다.");
		return  dao.insertImage(user, diaryImgDto);
	}

	public List<DiaryImgDto> initDataByMonth2(String date, UserDto user) {
		return dao.selectAllByMonth2(date, user);
	}
}
