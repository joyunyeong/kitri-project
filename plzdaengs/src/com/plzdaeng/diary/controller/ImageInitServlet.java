package com.plzdaeng.diary.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plzdaeng.diary.service.DiaryService;
import com.plzdaeng.dto.DiaryDto;
import com.plzdaeng.dto.DiaryImgDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/imageinit")
public class ImageInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DiaryService service;
	
    public ImageInitServlet() {
        super();
        service = new DiaryService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		System.out.println("------------------------------> ImageInitServlet 이동 OK");
		
		String date = request.getParameter("date");
		String path = "/index.jsp";
		
		System.out.println("> 받아와야 하는 月 : " + date);
		UserDto user = (UserDto)request.getSession().getAttribute("userInfo");
		if(user == null) {
			user = new UserDto();
			user.setUser_id("mnmm97");
		}
		
		/*
		List<DiaryImgDto> list = service.initDataByMonth2(date, user);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String listJSON = mapper.writeValueAsString(list);
		System.out.println(listJSON);
		request.setAttribute("result", listJSON);
		
		//System.out.println(list);
		path = "/diary/initdataresult.jsp";
		MoveUrl.forward(request, response, path);
		System.out.println("------------------------------> SERVLET BYE");
		System.out.println();
		*/
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
