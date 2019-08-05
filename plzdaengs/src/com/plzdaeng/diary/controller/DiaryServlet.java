package com.plzdaeng.diary.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Clob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.plzdaeng.diary.service.DiaryService;
import com.plzdaeng.dto.DiaryDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.ProfileCreate;
import com.plzdaeng.util.SiteConstance;

// groupfrontcontroller 참고 > 세션

@WebServlet("/enrolldiary")
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DiaryService service;
	
    public DiaryServlet() {
        super();
        service = new DiaryService();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. servlet 들어왔는지 확인
		 * 2. dto에 넘어온 정보 set > img also > userjoinservlet 참고
		 */
		
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("userInfo");
		
		System.out.println();
		System.out.println("------------------------------> diary 생성 SERVLET 이동 OK");
		
		String saveDirectory = SiteConstance.IMG_PATH;
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, "utf-8");
		
		String title = mr.getParameter("title");
		String description = mr.getParameter("description");
		String date = mr.getParameter("date");
		
		File imgdata = mr.getFile("imgdata");
		System.out.println("> 전송된 DATA : [제목> " + title + "] [내용> "+ description + "] [이미지> " + imgdata + "] [날짜> "+ date +"]");
		
		System.out.println("> 임의로 생성되는 ID : " + UUID.randomUUID());
		String filename = UUID.randomUUID().toString(); // 원래 string 형태 아니여서 toString 처리
		String path = request.getServletContext().getRealPath("/img");
		System.out.println("> 따로 저장되는 공간 : " + path);
		// 왜 이거 넘어가면 NullPointer 뜰까 > img 첨부안하면 뜸!
		
		// DTO에다 받아온 정보들 저장
		DiaryDto dto = new DiaryDto();
		dto.setDiary_subject(title);
		dto.setDiary_contents(description);
		
		Date diaryDate = null;
		try {
			diaryDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setDiary_date(diaryDate);
		if(imgdata != null) {
			System.out.println("	> 사진이 있네!");
			dto.setDiary_img("/plzdaengs/img/"+ filename + "." + imgdata.getName().split("\\.")[1]);
		} 
		
		if(dto.getDiary_img() == null){
			System.out.println("	> 사진이 없네ㅡㅡ");
			dto.setDiary_img(" ");
		}
		System.out.println("	> DTO결과 불러오기 : " + dto);
		
		//db 저장하는 로직
		//성공 : 1
		//실패 : -1
		
		System.out.println("> DB에 넣는 시도 ing...");
		
		int result = service.enrollDiary(user, dto); // in DB
		if(result > 0 && imgdata !=  null) { // 성공할 때 넣어야함
			ProfileCreate.profileRegister(imgdata, path, filename , null, "diary");
		}
		
		System.out.println("------------------------------> SERVLET BYE");
		System.out.println();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	/*
	// return type은 json으로
	JSONObject obj = new JSONObject();
	obj.put("result", "OK");
	
	response.setContentType("application/x-json; charset=UTF-8");
	response.getWriter().print(obj);
	
	System.out.println("/diary Servlet으로 이동 >>");
	
	//String val = request.getParameter("val");
	//String num = request.getParameter("num");
	
	String diary_subject = request.getParameter("title");
	String diary_contents = request.getParameter("description");
			System.out.print("DIARY[제목 : " + diary_subject + ", ");
	System.out.println("내용 : " + diary_contents + "]");
	// 아직 img는 못넘어왔음
	
	DiaryDto dto = new DiaryDto();
	dto.setDiary_subject(diary_subject);
	dto.setDiary_contents(diary_contents);
	**/

}
