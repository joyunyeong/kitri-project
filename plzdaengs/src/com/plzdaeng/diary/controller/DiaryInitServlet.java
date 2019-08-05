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
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/diaryinit")
public class DiaryInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DiaryService service;
	
    public DiaryInitServlet() {
        super();
        service = new DiaryService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String path = "/index.jsp";
		System.out.println("diaryinit : " + date);
		UserDto user = (UserDto)request.getSession().getAttribute("userInfo");
		if(user == null) {
			user = new UserDto();
			user.setUser_id("calubang");
		}
		
		
		List<DiaryDto> list = service.initDataByMonth(date, user);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String listJSON = mapper.writeValueAsString(list);
		System.out.println(listJSON);
		request.setAttribute("result", listJSON);
		
		System.out.println(list);
		path = "/diary/initdataresult.jsp";
		MoveUrl.forward(request, response, path);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
