package com.plzdaeng.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.plzdaeng.dto.UserDetailDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.service.UserService;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/usermodify")
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service;
    public UserModifyServlet() {
        super();
        service = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userInfo");
		String act = request.getParameter("act");
		System.out.println("usermodify : "+ userDto.getUser_id());
		
		
		UserDto userDetail = service.userModify(userDto);
		request.setAttribute("userDetail", userDetail);
		
		String path = "/user/usermodify.jsp";
		MoveUrl.forward(request, response, path);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
