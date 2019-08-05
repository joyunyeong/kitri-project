package com.plzdaeng.group.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.plzdaeng.dto.PageBean;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.group.model.*;
import com.plzdaeng.group.model.dao.GroupDaoImpl;
import com.plzdaeng.util.ProfileCreate;
import com.plzdaeng.util.SiteConstance;

public class GroupController {
	
	private static GroupController groupController;
	GroupDto dto;
	private HttpSession session;
	static {
		groupController = new GroupController();
		
	}

	public static GroupController getGroupController() {
		return groupController;
	}

	
	String path = "";
	
	public String pageLoaing(HttpServletRequest request, HttpServletResponse response, UserDto user) {
		// path = "/plzdaengs/template/alert_danger.jsp";
		String id = user.getUser_id();
		//user.setUser_id("qwer");
		String type = request.getParameter("type");
		List<GroupDto> list = null;
		if (type != null && type.equals("mine")) {
			System.out.println("if type mine");
			list = GroupDaoImpl.getGroupDaoImpl().myGroup(id);
		} else if (type != null && type.equals("recommend")) {
			System.out.println("if type recommend");
			list = GroupDaoImpl.getGroupDaoImpl().recommendGroup(id);
		} else {
			String key = request.getParameter("searchoption");
			String word = request.getParameter("searchword");
			System.out.println(key + word);
			list = GroupDaoImpl.getGroupDaoImpl().searchGroup(key, word);
		}
		
		
		//차후 로그인에서 넣어주면 지워도 되는 부분.
		//HttpSession session = request.getSession();
		//session.setAttribute("mygrouplist", list); 
		for(GroupDto dto : list) {
			System.out.println(dto.toString());
		}
		System.out.println(list.size());
		
		//페이지 처리로직
		String currentPageStr = request.getParameter("currentpage");
		int currentPage = 1;
		int listSize = list.size();
		if(currentPageStr != null) {
			currentPage = Integer.parseInt(currentPageStr);
		}
		String url = "";
		if (type != null && type.equals("mine")) {
			url = "/plzdaengs/groupfront?act=loading&type=mine";
		} else if (type != null && type.equals("recommend")) {
			url = "/plzdaengs/groupfront?act=loading&type=recommend";
		} else {
			url = "/plzdaengs/groupfront?act=loading&searchoption=key&searchword=word";
		}
		PageBean<GroupDto> pageBean 
			= new PageBean<GroupDto>(4, 5, currentPage, listSize, url);
		List<GroupDto> pageList = null;
		if(listSize > 0) {
			pageList = list.subList(pageBean.getStartRow()-1, pageBean.getEndRow());
		}else {
			pageList = new ArrayList<GroupDto>();
		}
		System.out.println(pageBean);
		System.out.println(pageList);
		
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("grouplist", pageList);
		
		path = "/group/result/grouplistresult.jsp";
		return path;
	}

	public String create(HttpServletRequest request, HttpServletResponse response, UserDto user) {
		System.out.println("create들어옴");
		//image
		String saveDirectory = SiteConstance.IMG_PATH;
		MultipartRequest multipartRequest = null;
		try {
			multipartRequest = new MultipartRequest(request, saveDirectory, "UTF-8");
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = multipartRequest.getParameter("groupname");
		String desc = multipartRequest.getParameter("groupdescription");
		String keyword = multipartRequest.getParameter("groupkeyword");
		String groupdontselect = multipartRequest.getParameter("groupdontselect");	
		
		
		
		path = "/group/groupmain.jsp";
		String id = user.getUser_id();
		//지역무관
		System.out.println(name + keyword + desc + "location : " + groupdontselect);
		
		
		// 소모임 정보set
		GroupCategory cate = new GroupCategory();
		cate.setGroup_category_id(keyword);
		dto = new GroupDto();
		dto.setGroup_leader(id);
		dto.setGroup_name(name);
		dto.setGroup_description(desc);
		if (groupdontselect == null || !groupdontselect.equals("on") ){
			String groupsido = multipartRequest.getParameter("groupsido");
			dto.setAddress_sido(groupsido);
		}
		dto.setGroupCategory(cate);
		
		//file upload
		File file = multipartRequest.getFile("imgdata");
		String fileName = UUID.randomUUID().toString();
		if(file == null) {
			//디폴트 그룹이미지
			dto.setGroup_img("/plzdaengs/template/img/siba.jpg");
		}else {
			
			dto.setGroup_img("/plzdaengs/img/group/" + fileName + "." + file.getName().split("\\.")[1]);
		}
		System.out.println(dto);
		

		int result = GroupDaoImpl.getGroupDaoImpl().createGroup(dto);
		if(result == 1) {
			String path = request.getServletContext().getRealPath("/img");
			System.out.println(path);
			ProfileCreate.profileRegister(file, path, fileName, null, "group");
		}
		
		System.out.println("dao 결과 result : " + result);
		request.setAttribute("result", result);
		return path;
	}

	public String enterorsingup(HttpServletRequest request, HttpServletResponse response, UserDto user) {
		response.setContentType("text/html;charset=UTF-8");
		path = "";
		String id = user.getUser_id();
		String result = "";
		//user.setUser_id("qwer");
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		//String group_name = request.getParameter("group_name");
		//System.out.println(group_id + group_name);
		//session = request.getSession();
		//session.setAttribute("group_id", group_id);
		//1 입장
		//GroupDaoImpl.getGroupDaoImpl().firstpage(group_id);
		
		
		//HttpSession session = request.getSession();
		//List<GroupDto> list = (List)session.getAttribute("grouplist");
		
//		for(GroupDto group : list) {
//			if(group_id == group.getGroup_id()) {
//				System.out.println("그룹입장");
//				result = group.
//				return
//			}
//		}
		
		//2 권한에 따라 버튼이 바뀜 ( 리더 : 관리, 일반 : 탈퇴, 요청중 : 대기, 비회원 : 가입 )
		//System.out.println("cont group_id : "+group_id);
		//System.out.println("cont id : "+id);
		result = GroupDaoImpl.getGroupDaoImpl().inquiry(group_id, id);
		//System.out.println(result);
		request.getSession().setAttribute("group_id", group_id);
		request.setAttribute("authority", result);
		descriptLoading(request, response, group_id);
		
		System.out.println(result);
		
		//3.그룹 홈페이지 로딩
		//GroupDaoImpl.getGroupDaoImpl().boardLoading(group_id);
		path = "/group/groupfirstpage.jsp";

		return path;
	}

	public String joinGroup(HttpServletRequest request, HttpServletResponse response, UserDto user) {
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String id = user.getUser_id();
		
		int joinrequestresult = GroupDaoImpl.getGroupDaoImpl().joinGroup(group_id, id);
		path = enterorsingup(request, response, user);
		System.out.println("result : "+ joinrequestresult);
		request.setAttribute("joinrequestresult", joinrequestresult);
		return path;
	}
	
	public String goOutGroup(HttpServletRequest request, HttpServletResponse response, UserDto user) {
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String id = user.getUser_id();
		
		int joinrequestresult = GroupDaoImpl.getGroupDaoImpl().goOutGroup(group_id, id);
		path = enterorsingup(request, response, user);
		System.out.println("result : "+ joinrequestresult);
		request.setAttribute("joinrequestresult", joinrequestresult);
		return path;
	}



	public String entermanege(HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		dto = new GroupDto();
		int group_id = 0;
		if(request.getParameter("group_id") != null) {
			group_id = Integer.parseInt(request.getParameter("group_id"));
		}else {
			dto = (GroupDto)session.getAttribute("groupdetail");
			group_id = dto.getGroup_id();
		}
		System.out.println(group_id);
		dto = GroupDaoImpl.getGroupDaoImpl().groupDetail(group_id);
		session.setAttribute("groupdetail", dto);
		path = "/group/managegroupinfo.jsp";
		return path;
	}

	public String changeDetail(HttpServletRequest request, HttpServletResponse response) {
		session = request.getSession();
		dto = new GroupDto();
		dto = (GroupDto)session.getAttribute("groupdetail");
		path = "/group/managegroupinfo.jsp";
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		String name = request.getParameter("group_name");
		String desc = request.getParameter("group_description");
		String keyword = request.getParameter("group_cate");
		String groupdontselect = request.getParameter("groupdontselect");//지역무관
		String img = request.getParameter("group_img");
		String groupsido = request.getParameter("group_sido");

		if (groupdontselect == null || !groupdontselect.equals("on") ){
			System.out.println("지역무관 판단");
			String group_sido = request.getParameter("groupsido");
			dto.setAddress_sido(groupsido);
		}
		
		System.out.println(name + "/" +keyword  + "/" + desc + "/" + groupdontselect);
		// 소모임 정보set
		dto.setGroup_id(group_id);
		//dto.setGroup_leader(id);
		dto.setGroup_name(name);
		dto.setGroup_description(desc);
		dto.getGroupCategory().setGroup_category_name(keyword);;
		dto.setGroup_img(img);
		dto.setAddress_sido(groupsido);
		
		
		int result = GroupDaoImpl.getGroupDaoImpl().changeGroup(dto);
		request.setAttribute("result", result);
		entermanege(request, response);
		session.setAttribute("groupdetail", dto);
		return path;
	}


	public String memberlist(HttpServletRequest request, HttpServletResponse response) {
		path = "/group/result/memberlistresult.jsp";
		session = request.getSession();
		dto = (GroupDto)session.getAttribute("groupdetail");
		List<GroupMember> list = null;
		int group_id = dto.getGroup_id();
		System.out.println(group_id);
		list = GroupDaoImpl.getGroupDaoImpl().memberlist(group_id);
		request.setAttribute("memberlist", list);
		
		return path;
	}

	public String managemember(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("들어는오나?");
		path = "/group/result/memberlistresult.jsp";
		session = request.getSession();
		dto = (GroupDto)session.getAttribute("groupdetail");
		int group_id = dto.getGroup_id();
		String user_id = request.getParameter("member_id");
		String member_status = request.getParameter("member_status");
		System.out.println(user_id +"/" + member_status);
		GroupMember member = new GroupMember();
		member.setGroup_id(group_id);
		member.setUser_id(user_id);
		member.setMember_status(member_status);
		System.out.println(member);
		int result = -1;
		if(member_status.equals("A")) {
			result = GroupDaoImpl.getGroupDaoImpl().permitMember(member);
		}else if(member_status.equals("M")) {
			result = GroupDaoImpl.getGroupDaoImpl().kickMember(member);
		}else if(member_status.equals("L")){
			result = GroupDaoImpl.getGroupDaoImpl().removeAuthority(member);
		}
		request.setAttribute("result", result);
		return path;
	}

	public void descriptLoading(HttpServletRequest request, HttpServletResponse response, int group_id) {
		//path = "/group/groupfirstpage.jsp";
		//int group_id = (int)request.getAttribute("group_id");
		System.out.println(group_id);
		dto = GroupDaoImpl.getGroupDaoImpl().groupPageLoading(group_id);
		System.out.println(dto.getGroup_name());
		request.setAttribute("group", dto);
		
		//return path;
	}





	
	

}
