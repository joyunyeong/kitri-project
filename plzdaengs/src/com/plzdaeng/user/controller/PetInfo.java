package com.plzdaeng.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plzdaeng.dto.PetDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.service.PetService;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/petinfo")
public class PetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PetService service;
       
    public PetInfo() {
        super();
        service = new PetService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String petName = request.getParameter("petname");
		UserDto user = (UserDto)request.getSession().getAttribute("userInfo");
		System.out.println("petinfo : " + petName + "//" + user.getUser_id());
		
		PetDto petDto = service.petDetail(user.getUser_id(), petName);
		System.out.println(petDto);
		
		request.setAttribute("petInfo", petDto);
		String path = "/user/animalmodify.jsp";
		MoveUrl.forward(request, response, path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
