package com.plzdaeng.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plzdaeng.user.service.AdminService;
import com.plzdaeng.util.MoveUrl;

@WebServlet("/admin")
public class PetKindRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AdminService service;
    
    public PetKindRegister() {
        super();
        service = new AdminService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		System.out.println("admin : " + act);
		int result = 0;
		String path = "/user/admin.jsp";
		if(act == null) {
			return;
		}
		
		switch (act) {
		case "petkindregister":
			result = service.petKindRegister();
			request.setAttribute("result", result);
			path = "/user/result/petkindResult.jsp";
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
