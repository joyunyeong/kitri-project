package com.plzdaeng.user.controller;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import com.oreilly.servlet.MultipartRequest;
import com.plzdaeng.dto.UserDetailDto;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.user.service.UserService;
import com.plzdaeng.util.*;

@WebServlet("/userjoin")
public class UserJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service;
       
    public UserJoinServlet() {
        super();
        service = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("userjoin");
	
		//String saveDirectory = request.getServletContext().getRealPath("\\img\\user");
		String saveDirectory = SiteConstance.IMG_PATH;
		
		//System.out.println(request.getContentType());
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, "utf-8");
		
		String id = mr.getParameter("id");
		String password = mr.getParameter("password");
		String emailid = mr.getParameter("emailid");
		String emaildomain = mr.getParameter("emaildomain");
		String nickname = mr.getParameter("nickname");
		String tel = mr.getParameter("tel");
		String gender = mr.getParameter("gender");
		String zipcode = mr.getParameter("zipcode");
		String address = mr.getParameter("address");
		String addressdetail = mr.getParameter("addressdetail");
		
		UserDto userDto = new UserDto();
		userDto.setUser_id(id);
		userDto.setPassword(password);
		userDto.setEmailid(emailid);
		userDto.setEmaildomain(emaildomain);
		userDto.setNickname(nickname);
		
		//상세정보
		UserDetailDto userDetailDto = new UserDetailDto();
		userDto.setUserDetailDto(userDetailDto);
		userDetailDto.setTel(tel);
		userDetailDto.setGender(gender);
		userDetailDto.setZipcode(zipcode);
		userDetailDto.setAddress(address);
		userDetailDto.setAddress_detail(addressdetail);
				
		//등록한 파일이 없으면 기본 이미지 사용
		File profileFile = mr.getFile("imgdata");
		if(profileFile  == null) {
			userDto.setUser_img("/plzdaengs/template/img/basic_user_profile.png");
		}else {
			String[] fileNames = profileFile.getName().split("\\.");
			userDto.setUser_img("/plzdaengs/img/"+userDto.getUser_id()+"/user_profile." + fileNames[1]);
		}
		
		int result = service.userJoin(userDto);
		if(result == 1 && profileFile  != null) {
			String path = request.getServletContext().getRealPath("/img");
			ProfileCreate.profileRegister(profileFile, path , userDto.getUser_id(), null , "user");
		}
		request.setAttribute("userjoinresult", result);
		MoveUrl.forward(request, response, "/user/userjoinresult.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
