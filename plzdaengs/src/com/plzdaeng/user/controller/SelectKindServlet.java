package com.plzdaeng.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plzdaeng.dto.BreedDto;
import com.plzdaeng.user.service.PetService;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/selectkind")
public class SelectKindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PetService service;
	
    public SelectKindServlet() {
    	service = new PetService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/index.jsp";
		
		String name = request.getParameter("kind");
		String animalCode = request.getParameter("animalcode");
		if(name == null) {
			name = "";
		}
		if(animalCode == null) {
			animalCode = "417000";
		}
		System.out.println("selectkind : " + name);
		List<BreedDto> list = service.selectKind(name, animalCode);
		request.setAttribute("selectkindresult", list);
		System.out.println(list);
		
		path = "/user/result/selectkindresult.jsp";
		MoveUrl.forward(request, response, path);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
