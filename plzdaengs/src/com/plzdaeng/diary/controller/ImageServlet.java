package com.plzdaeng.diary.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plzdaeng.diary.service.DiaryService;
import com.plzdaeng.dto.DiaryDto;
import com.plzdaeng.dto.DiaryImgDto;
import com.plzdaeng.dto.UserDto;

@WebServlet("/enrollimage")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DiaryService service;
	
    public ImageServlet() {
        super();
        service = new DiaryService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 만약 hashtag에 이미 있으면 > insert , 없으면 update
		
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("userInfo");
		
		System.out.println();
		System.out.println("------------------------------> 이모티콘 저장 SERVLET 이동 OK");
		
		String date = request.getParameter("date");
		String category = request.getParameter("category");
		
		System.out.println("> 전송된 DATA : [날짜 : " + date + ", category : " + category + "]");
		
		// DTO에다 받아온 정보들 저장
		DiaryDto dto = new DiaryDto();
		dto.setUser_id(user.getUser_id());
		dto.setCategory_id(category);

		try {
			dto.setDiary_date(new SimpleDateFormat("yyyy/MM/dd").parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
		System.out.println("> DTO결과 불러오기 : " + dto);
		System.out.println("> DB에 넣는 시도 ing...");
		
		int result = service.enrollDiary(user, dto);
		//int result = service.putImg(user, dto); // in DB
		System.out.println("------------------------------> SERVLET BYE");
		System.out.println();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
