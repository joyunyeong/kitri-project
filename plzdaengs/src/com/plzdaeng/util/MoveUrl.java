package com.plzdaeng.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MoveUrl {

	public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		
		response.sendRedirect(request.getContextPath() + path);
		
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
//		기준이 이미 프로젝트 안이기 때문에 request.getContextPath()를 쓸 필요가없다.
		System.out.println("forward : "+path);
		RequestDispatcher dispacher = request.getRequestDispatcher(path);
		dispacher.forward(request, response);
	}
	
}
