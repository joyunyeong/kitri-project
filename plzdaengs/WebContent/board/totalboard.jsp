<%@page import="com.plzdaeng.dto.UserDto"%>
<%@page import="com.plzdaeng.board.model.BoardPage"%>
<%@page import="com.plzdaeng.board.model.PlzBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<PlzBoard> board = (List) request.getAttribute("boardList");
	BoardPage pageInfo = (BoardPage) request.getAttribute("boardPage");
	UserDto userDto = (UserDto)request.getSession().getAttribute("userInfo");
%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bubbly - Boootstrap 4 Admin template by Bootstrapious.com</title>
    <%@ include file="/template/default_link.jsp" %>
    <script src="/plzdaengs/board/js/httpRequest.js"></script>
  </head>
  <script type="text/javascript">
  	function searchList(){
		
  		$("#searchGubun").val($("#searchGubun2 option:selected").val());
  		$("#searchValue").val($("#searchValue2").val());
  		$("#curPage").val("1");
  		
  		
		document.getElementById("search").action = "/plzdaengs/plzBoard";
		document.getElementById("search").submit();
  	}
  	
  	function goDetail(post_id){
  		$("#post_id").val(post_id);
  		document.getElementById("detail").action = "/plzdaengs/plzBoard";
		document.getElementById("detail").submit();
  	}
  	
  	function goMovePage(page){
  		var searchGubun = $("#searchGubun2").val();
  		var searchValue = $("#searchValue2").val();
  		//console.log("searchGubun : " +searchGubun);
  		//console.log("searchValue : " +searchValue);
  		document.location.href = "/plzdaengs/plzBoard?cmd=boardList&curPage="
  				+page + "&searchGubun="+searchGubun + "&searchValue="+searchValue;
  	}
  	
  
  </script>
<body>
	<!-- navbar-->	
	<%@ include file="/template/header.jsp" %>
	<div class="d-flex align-items-stretch" id ="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp" %>
		<!-- 사이드 sidebar -->
      
      <div class="page-holder w-100 d-flex flex-wrap">
        <div class="container-fluid px-xl-5" id="contents">
          <section class="py-5" style="max-height: none;">
            <div class="row"><!-- 여기 밑에 페이징 -->
              <div class=" col-lg-12 mb-4">
                <div class="card" style="min-width:40rem"><!-- 여기 끝에 글쓰기버튼 -->
                  <div class="card-header">
                    <h6 class="text-uppercase mb-0">게시판</h6>
                  </div>
                  <div class="card-body">
                    <table class="table table-hover card-text">
                      <thead>
                        <tr>
                          <th>No</th>
                          <th>카테고리</th>
                          <th colspan="5">제목</th>
                          <th>작성자</th>
                          <th>조회수</th>
                          <th>작성일</th>
                        </tr>
                      </thead>
                      <!--게시물 목록 반복문 실행될 곳-->
                      <tbody>
                        <%
                        	for(int i=0; i<board.size();i++){
                        %>
	                        <tr onclick="goDetail(<%=board.get(i).getPost_id() %>)">
	                          <th scope="row"><%=board.get(i).getNo() %></th>
	                          <td><%=board.get(i).getBoard_category_descripton() %></td>
	                          <td colspan="5"><%=board.get(i).getPost_subject() %></td>
	                          <td><%=board.get(i).getNickname() %></td>
	                          <td><%=board.get(i).getViews() %></td>
	                          <td><%=board.get(i).getCreat_date() %></td>
                        </tr>
                        <%} %>
                      </tbody>
                    </table>
                  </div>
                </div><!-- 여기 끝에 글쓰기버튼 -->
                <%if(userDto != null){ %>
                <button class="btn btn-info" type="button" style="float: right; padding: 0.2rem 0.8rem;">
                	<a href="/plzdaengs/plzBoard?cmd=boardWrite">글쓰기</a>
                </button>
                <%} %>
              </div>
              <!-- 사용할 게시판 끝과 사용안할것  -->
            </div><!-- 여기 끝에 페이징처리 -->
            <div class="form-group row mb-4" style="margin-left: 10%;">
              <select id="searchGubun2" class="form-control col-md-1">
                    <option value="1">제목</option>
                    <option value="2">작성자</option>
              </select>
              <input type="text" id="searchValue2" class="form-control col-md-7" style="margin-left:1rem;margin-right:4rem;">
              <button type="button" class="btn btn-primary" onclick="searchList()">검색하기</button>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><!-- 페이징 -->
			<nav>
				<ul class="pagination" style="margin-left: 30%;">
					<% if(pageInfo.getCurBlock() > 1 ){ %>
					<li class="page-item">
						<a class="page-link" href="#">Previous</a>
					</li>
					<%} 
					
						for(int i = pageInfo.getBlockBegin(); i <=pageInfo.getBlockEnd() ; i++){
							if(i == pageInfo.getCurPage()){
					%>
						<li class="page-item" onclick="goMovePage(<%=i%>)">
							<a class="page-link"><%=i %></a>
						</li>
					<%}else{ %>
						<li class="page-item" onclick="goMovePage(<%=i%>)">
							<a class="page-link" href="#"><%=i %></a>
						</li>
					<% 
					}
						}
						
						if(pageInfo.getCurBlock() <= pageInfo.getTotalBlock()){ %>
					<li class="page-item">
						<a class="page-link" href="#">Next</a>
					</li>
					<%} %>
				</ul>
			</nav>
		</div>
		<form id="search" method="get" action="">
			<input type="hidden" name="searchValue" id="searchValue" value="">
			<input type="hidden" name="searchGubun" id="searchGubun" value="">
			<input type="hidden" name="curPage" id="curPage" value="">
			<input type="hidden" name="cmd" id="cmd" value="boardList">
		</form>
		
		<form id="detail" method="post" action="">
			<input type="hidden" name="post_id" id="post_id" value="">
			<input type="hidden" name="cmd" id="cmd" value="detail">
		</form>
            
          </section>
        </div>
        <%@ include file="/template/footer.jsp" %>
      </div>
    </div>
    <%@ include file="/template/default_js_link.jsp" %>
  </body>
</html>