package com.plzdaeng.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/userlogout")
public class UserLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserLogout() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDto user = (UserDto)request.getSession().getAttribute("userInfo");
		System.out.println("userlogout : " + user);
		
		request.getSession().invalidate();
		String path = "/index.jsp";
		MoveUrl.forward(request, response, path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
