package com.plzdaeng.chart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plzdaeng.util.MoveUrl;

@WebServlet("/chart")
public class Chart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ChartService service;
	
    public Chart() {
        super();
        service = new ChartService();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String path = "/chart/chart.jsp";
		if(data == null) {
			MoveUrl.forward(request, response, path);
			return;
		}
		//String path = "/index.jsp";
		String dataJSON = "";
		System.out.println("chart : " + data);
		
		switch (data) {
		case "animalBreedRanking":
			int maxRanking = Integer.parseInt(request.getParameter("maxranking"));
			dataJSON = service.animalBreedRanking(maxRanking);
			request.setAttribute("result", dataJSON);
			//System.out.println(dataJSON);
			path = "/chart/chartresult.jsp";
			break;
			
		case "genderAvgAgeForBreed":	
			String breedName = request.getParameter("breedname");
			dataJSON = service.genderAvgAgeForBreed(breedName);
			request.setAttribute("result", dataJSON);
			//System.out.println(dataJSON);
			path = "/chart/chartresult.jsp";
			break;
			
		default:
			break;
		}
		System.out.println("data : " + dataJSON);
		MoveUrl.forward(request, response, path);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
