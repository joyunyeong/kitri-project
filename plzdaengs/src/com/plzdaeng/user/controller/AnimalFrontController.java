package com.plzdaeng.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plzdaeng.dto.PetDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.service.PetService;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/animal")
public class AnimalFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PetService service;
  
    public AnimalFrontController() {
       service = new PetService();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		
		System.out.println("animal : " + act);
		if(act == null)
			return;
		//로그인 확인
		UserDto user = (UserDto)request.getSession().getAttribute("userInfo");
		if(user == null) {
			MoveUrl.forward(request, response, path);
			return;
		}
		
		//세션에 펫리스트 세팅해두기		
		HttpSession session = request.getSession();
		if(session.getAttribute("petList") != null) {
			session.removeAttribute("petlist");
		}
		List<PetDto> petList = service.petList(user);
		session.setAttribute("petList", petList);
		
		switch (act) {
		case "animalregister":
			path = "/user/animalregister.jsp";
			break;
		case "animals":
			path = "/user/animals.jsp";
			break;
			
		default:
			break;
		}
		System.out.println(petList);
		MoveUrl.forward(request, response, path);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
