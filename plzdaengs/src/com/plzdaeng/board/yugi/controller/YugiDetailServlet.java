package com.plzdaeng.board.yugi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/yugidetail")
public class YugiDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public YugiDetailServlet() {
        super();
       
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("상세페이지 서블릿 들어옴");
		response.setContentType("text/xml;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		if ("yugiDetail".equals(cmd)) {
			System.out.println(request.getParameter("specialMark"));
			String path = "/board/yugidetail.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);//path : 경로 내 프로젝트 안에서만 이동가능
			dispatcher.forward(request, response);
		}
	}

}
