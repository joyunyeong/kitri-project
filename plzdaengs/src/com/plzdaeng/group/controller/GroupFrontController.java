package com.plzdaeng.group.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.MoveUrl;
import com.plzdaeng.util.SiteConstance;


@WebServlet("/groupfront")
public class GroupFrontController extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
	}

	private static final long serialVersionUID = 1L;
       
   
    public GroupFrontController() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		
		System.out.println("front 도착");
		String act = request.getParameter("act");
		//String path = "/group/index.jsp";
		System.out.println(act);
		HttpSession session = request.getSession();
		UserDto user = (UserDto)session.getAttribute("userInfo");
		
		//테스트용 코드
		if(user == null) {
			user = new UserDto();
			//user.setUser_id("asdf");
			user.setUser_id("qwer");
			request.getSession().setAttribute("userInfo", user);
		}
		
		System.out.println(user);
		String path = "";
		if("creategroup".equals(act)) {
			System.out.println("front create");
			path = GroupController.getGroupController().create(request, response, user);
			MoveUrl.forward(request, response, path);
		}else if("loading".equals(act)) {
			System.out.println("front loading");
			path = GroupController.getGroupController().pageLoaing(request, response, user);
			MoveUrl.forward(request, response, path);
			System.out.println(path);
		}else if("enter".equals(act)) {
			System.out.println("front enter");
			path = GroupController.getGroupController().enterorsingup(request, response, user);
			MoveUrl.forward(request, response, path);
		}else if("groupmanage".equals(act)) {
			System.out.println("front groupmanage");
			path = GroupController.getGroupController().entermanege(request, response);
			MoveUrl.forward(request, response, path);
			System.out.println("go to the groupdetail");
		}else if("changedetail".equals(act)) {
			System.out.println("front changedetail");
			path = GroupController.getGroupController().changeDetail(request, response);
			MoveUrl.forward(request, response, path);
		}else if("joingroup".equals(act)) {
			System.out.println("front joingroup");
			path = GroupController.getGroupController().joinGroup(request, response, user);
			MoveUrl.forward(request, response, path);
		}else if("memberlist".equals(act)) {
			System.out.println("front memberlist");
			path = GroupController.getGroupController().memberlist(request, response);
			MoveUrl.forward(request, response, path);
		}else if("managemember".equals(act)) {
			System.out.println("front managemember");
			path = GroupController.getGroupController().managemember(request, response);
			MoveUrl.forward(request, response, path);
//		}else if("firstPageLoding".equals(act)) {
//			System.out.println("front firstPageLoding");
//			path = GroupController.getGroupController().descriptLoding(request,response);
//			MoveUrl.forward(request, response, path);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(SiteConstance.ENCODE);
		doGet(request, response);
		
	}


}
