package com.plzdaeng.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.service.UserService;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/userlogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service;
	
    public UserLogin() {
        super();
        service = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		UserDto userDto = new UserDto();
		userDto.setUser_id(id);
		userDto.setPassword(password);
		userDto = service.userLogin(userDto);
		String path = "/user/result/userloginresult.jsp";
		//로그인 성공
		if(userDto != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", userDto);
			request.setAttribute("userloginresult", 1);
		}else {
			request.setAttribute("userloginresult", -1);
		}
		
		MoveUrl.forward(request, response, path);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
