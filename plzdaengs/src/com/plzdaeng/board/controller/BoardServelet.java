package com.plzdaeng.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plzdaeng.board.dao.PlzBoardDaoImpl;
import com.plzdaeng.board.model.BoardPage;
import com.plzdaeng.board.model.PlzBoard;
import com.plzdaeng.board.model.PlzReply;
import com.plzdaeng.board.yugi.service.YugiService;
import com.plzdaeng.dto.UserDto;
import com.plzdaeng.util.MoveUrl;

/**
 * Servlet implementation class BoardServelet
 */
@WebServlet("/plzBoard")
public class BoardServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int rPageScale = 5;
	private static int bPageScale = 10;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("게시판서블릿들어옴");
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		String cmd = request.getParameter("cmd");
		System.out.println(cmd);
		String path = "";
		if("boardWrite".equals(cmd)) {
			path = "/board/totalboardwrite.jsp";
			request.setAttribute("category", PlzBoardDaoImpl.getPlzBoardDao().getCategoryList());
			request.setAttribute("updateView", new PlzBoard());
			request.setAttribute("mode", "regist");
			MoveUrl.forward(request, response, path);
		}else if("regist".equals(cmd)) {
			path = "/board/totalboardview.jsp";
			HttpSession session = request.getSession();
			UserDto userDto = (UserDto)session.getAttribute("userInfo");
//			getPostId
			int post_id = PlzBoardDaoImpl.getPlzBoardDao().getPostId();
			PlzBoard board = new PlzBoard();
			board.setPost_id(post_id);
			board.setBoard_category_id(request.getParameter("board_category_id"));
			board.setPost_subject(request.getParameter("post_subject"));
			board.setUser_id(userDto.getUser_id());
			//board.setUser_id("yaho");
			board.setPost_contents(request.getParameter("post_contents"));
			
			PlzBoardDaoImpl.getPlzBoardDao().insertBoard(board);
			 
			 
			 PlzBoard detailBoard = PlzBoardDaoImpl.getPlzBoardDao().getBoardDetail(post_id);	//게시물 상세조회
			//리플 리스트 조회 
			PlzReply reply = new PlzReply();
			reply.setPost_id(post_id);
			reply.setrCurPage(1);
			reply.setrPagescale(rPageScale);
			 
			List<PlzReply> list = PlzBoardDaoImpl.getPlzBoardDao().getReplyList(reply);
			
			//리플 전체 건수조회
			int ttlCnt = PlzBoardDaoImpl.getPlzBoardDao().getReplyTotalCnt(reply.getPost_id());
			BoardPage page = new BoardPage(ttlCnt, 1, rPageScale);
			
			 System.out.println(path);
			 //리플 리스트 조회 
			 request.setAttribute("detailView", detailBoard);
			 request.setAttribute("reply", list);
			 request.setAttribute("replyPage", page);
			 
			 MoveUrl.forward(request, response, path);
			 
		}else if("boardList".equals(cmd)) {
			int curPage = 1;
			int ttlCnt = 0;
			path = "/board/totalboard.jsp";
			
			
			if(! "".equals(request.getParameter("curPage"))) {
				curPage = Integer.parseInt(request.getParameter("curPage"));
			}
			
			ttlCnt = PlzBoardDaoImpl.getPlzBoardDao().getBoardTotalCnt();
			BoardPage page = new BoardPage(ttlCnt, curPage, bPageScale);
			
			String searchValue = "";
			String searchGubun = "";
			
			searchValue = request.getParameter("searchValue");
			searchGubun = request.getParameter("searchGubun");
			PlzBoard board = new PlzBoard();
			board.setCurPage(curPage);
			board.setPagescale(10);
			board.setSearchValue(searchValue);
			board.setSearchGubun(searchGubun);
			
			System.out.println(board.toString());
			
			List<PlzBoard> list = PlzBoardDaoImpl.getPlzBoardDao().getBoardList(board);
			
			
			request.setAttribute("boardList",list);
			request.setAttribute("boardPage",page);
			request.setAttribute("searchValue", searchValue);
			request.setAttribute("searchGubun", searchGubun);
			
			
			MoveUrl.forward(request, response, path);
		}else if("detail".equals(cmd)) {
			path = "/board/totalboardview.jsp";
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			 PlzBoardDaoImpl.getPlzBoardDao().updateViews(post_id); //조회수 증가
			 
			 PlzBoard board = PlzBoardDaoImpl.getPlzBoardDao().getBoardDetail(post_id);	//게시물 상세조회
			 
			//리플 리스트 조회 
			PlzReply reply = new PlzReply();
			reply.setPost_id(post_id);
			reply.setrCurPage(1);
			reply.setrPagescale(rPageScale);
			 
			List<PlzReply> list = PlzBoardDaoImpl.getPlzBoardDao().getReplyList(reply);
			
			//리플 전체 건수조회
			int ttlCnt = PlzBoardDaoImpl.getPlzBoardDao().getReplyTotalCnt(reply.getPost_id());
			BoardPage page = new BoardPage(ttlCnt, 1, rPageScale);
			
			request.setAttribute("detailView", board);
			request.setAttribute("reply", list);
			request.setAttribute("replyPage", page);
			
			MoveUrl.forward(request, response, path);
		}else if("modify".equals(cmd)) {
			path = "/board/totalboardwrite.jsp";
			int post_id = Integer.parseInt(request.getParameter("post_id"));
			PlzBoard board = PlzBoardDaoImpl.getPlzBoardDao().getBoardDetail(post_id);	//게시물 상세조회
			
			 request.setAttribute("updateView", board);
			 request.setAttribute("mode", "update");
			 request.setAttribute("category", PlzBoardDaoImpl.getPlzBoardDao().getCategoryList());
			 MoveUrl.forward(request, response, path);
		}else if("update".equals(cmd)) {
			System.out.println("update ininin");
			//수정하기
			path = "/board/totalboardview.jsp";
			HttpSession session = request.getSession();
			UserDto userDto = (UserDto)session.getAttribute("userInfo");
			
			PlzBoard board = new PlzBoard();
			board.setBoard_category_id(request.getParameter("board_category_id"));
			board.setPost_subject(request.getParameter("post_subject"));
			board.setUser_id(userDto.getUser_id());
//			board.setUser_id("yaho");
			board.setPost_contents(request.getParameter("post_contents"));
			board.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			
			PlzBoardDaoImpl.getPlzBoardDao().updateBoard(board);
			
			PlzBoard boardDetail = PlzBoardDaoImpl.getPlzBoardDao().getBoardDetail(board.getPost_id());	//게시물 상세조회
			
			//리플 리스트 조회 
			PlzReply reply = new PlzReply();
			reply.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			reply.setrCurPage(1);
			reply.setrPagescale(rPageScale);
			 
			List<PlzReply> list = PlzBoardDaoImpl.getPlzBoardDao().getReplyList(reply);
			
			//리플 전체 건수조회
			int ttlCnt = PlzBoardDaoImpl.getPlzBoardDao().getReplyTotalCnt(reply.getPost_id());
			BoardPage page = new BoardPage(ttlCnt, 1, rPageScale);

			 
			 //리플 리스트 조회 
			 request.setAttribute("detailView", boardDetail);

			request.setAttribute("reply", list);
			request.setAttribute("replyPage", page);
			 
			MoveUrl.forward(request, response, path);
			
		}else if("reply".equals(cmd)) {
			path = "/board/totalboardview.jsp";
			HttpSession session = request.getSession();
			UserDto userDto = (UserDto)session.getAttribute("userInfo");
			PlzReply reply = new PlzReply();
			reply.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			reply.setBoard_category_id(request.getParameter("board_category_id"));
			reply.setReply_contents(request.getParameter("reply_contents"));
			reply.setUser_id(userDto.getUser_id());
			reply.setrCurPage(1);
			reply.setrPagescale(rPageScale);
			//리플저장
			PlzBoardDaoImpl.getPlzBoardDao().insertReply(reply);
			
			//리플 리스트 조회 
			List<PlzReply> list = PlzBoardDaoImpl.getPlzBoardDao().getReplyList(reply);
			
			//리플 전체 건수조회
			int ttlCnt = PlzBoardDaoImpl.getPlzBoardDao().getReplyTotalCnt(reply.getPost_id());
			BoardPage page = new BoardPage(ttlCnt, 1, rPageScale);
			
			String resultXML = getPaggingXml(list, page);
			response.setContentType("text/xml;charset=utf-8");
			
			System.out.println(resultXML);
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		
		}else if("getReply".equals(cmd)) {
			path = "/board/totalboardview.jsp";
			PlzReply reply = new PlzReply();
			reply.setPost_id(Integer.parseInt(request.getParameter("post_id")));
			reply.setrCurPage(Integer.parseInt(request.getParameter("curPage")));
			reply.setrPagescale(rPageScale);
			
			//리플 리스트 조회 
			List<PlzReply> list = PlzBoardDaoImpl.getPlzBoardDao().getReplyList(reply);
			
			//리플 전체 건수조회
			int ttlCnt = PlzBoardDaoImpl.getPlzBoardDao().getReplyTotalCnt(reply.getPost_id());
			BoardPage page = new BoardPage(ttlCnt, reply.getrCurPage(), rPageScale);
			
			String resultXML = getPaggingXml(list, page);
			System.out.println(resultXML);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		
		}else if("remove".equals(cmd)) {
			int result = PlzBoardDaoImpl.getPlzBoardDao().deleteBoard(Integer.parseInt(request.getParameter("post_id")));
			
			String resultXML = getRemoveXml(result);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
			
		}else if("removeReply".equals(cmd)) {
			int result = PlzBoardDaoImpl.getPlzBoardDao().deleteRelply(Integer.parseInt(request.getParameter("reply_id")));
			
			String resultXML = getRemoveXml(result);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(resultXML);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	public String getPaggingXml(List<PlzReply> list, BoardPage page) {
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		result += "<replyList>\n";
		
		for(PlzReply reply : list) {
			result += "<reply>\n";
			result += "		<reply_id>"+reply.getReply_id()+"</reply_id>\n";
			result += "		<post_id>"+reply.getPost_id()+"</post_id>\n";
			result += "		<user_id>"+reply.getUser_id()+"</user_id>\n";
			result += "		<user_name>"+reply.getUser_name()+"</user_name>\n";
			result += "		<reply_contents><![CDATA["+reply.getReply_contents()+"]]></reply_contents>\n";
			result += "		<create_date><![CDATA["+reply.getCreat_date()+"]]></create_date>\n";
			result += "</reply>\n";
		}
		result += "<page>\n";
		result += "		<curBlock>"+page.getCurBlock()+"</curBlock>\n";
		result += "		<blockBegin>"+page.getBlockBegin()+"</blockBegin>\n";
		result += "		<curPage>"+page.getCurPage()+"</curPage>\n";
		result += "		<blockEnd>"+page.getBlockEnd()+"</blockEnd>\n";
		result += "		<totalBlock>"+page.getTotalBlock()+"</totalBlock>\n";
		result += "</page>\n";
		result += "</replyList>";
		return result;
	}
	
	
	public String getRemoveXml(int removeResult) {
		String result = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n";
		result += "<removeList>";
		result += "		<remove>\n";
		result += "			<result>"+removeResult+"</result>\n";
		if(1 == removeResult) {
			result += "			<msg>삭제되었습니다.</msg>\n";
		}else {
			result += "			<msg>삭제실패하였습니다. 관리자에게 문의하세요.</msg>\n";
		}
		result += "		</remove>\n";
		result += "</removeList>";
		return result;
	}

}
