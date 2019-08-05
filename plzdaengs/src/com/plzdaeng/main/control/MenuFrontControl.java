package com.plzdaeng.main.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/menu")
public class MenuFrontControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MenuFrontControl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "index.jsp";
		HttpSession session = request.getSession();
		UserDto userDto = (UserDto)session.getAttribute("userInfo");
		System.out.println("menu : " + act);
		
		if(act == null)
			return;
		
		switch (act) {
		case "animals":
			path = "/animal";
			break;
			
		case "usermodify":
			if(userDto == null) {
				path = "/index.jsp";
			}else {
				path = "usermodify";
			}
			break;
		case "home":
			path = "/index.jsp";
			break;
		
		case "admin":
			path = "/admin";
			break;
		
		case "chart":
			path = "/chart";
			break;
		default:
			break;
		}
		MoveUrl.forward(request, response, path);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
