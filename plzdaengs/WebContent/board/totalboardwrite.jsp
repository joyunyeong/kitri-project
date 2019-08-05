<%@page import="com.plzdaeng.board.model.PlzBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.plzdaeng.board.model.PlzBoardCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<PlzBoardCategory> category = (List) request.getAttribute("category");
	PlzBoard board = (PlzBoard) request.getAttribute("updateView");
	String mode = (String)request.getAttribute("mode");
%>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bubbly - Boootstrap 4 Admin template by Bootstrapious.com</title>
    <%@ include file="/template/default_link.jsp" %>
    <!-- include summernote css/js -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<link href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
<!-- 서머노트 -->
  </head>
  <script type="text/javascript">
  $(document).ready(function() {//
	var contents = '<%=board.getPost_contents()%>';
	var subject = '<%= board.getPost_subject()%>';
	
	if('null' != contents){
		$('#summernote').summernote('code',contents);
	}
	
	if('null' != subject){
		$('#post_subject').val(subject);
	}
  });

  	function regist(mode){
  		var categoryId = $("#board_category option:selected").val();
  		if(categoryId == ""){
  			alert('카테고리를 선택해주세요');
  			return;
  		}
  		
  		if($("#post_subject").val() == ""){
  			alert('제목을 입력해주세요');
  			return;
  		}
  	  var markupStr = $('#summernote').summernote('code');
  	  
  	  if(markupStr == ""){
  		  alert('내용을 입력해주세요');
  		  return;
  	  }
  		$('#board_category_id').val(categoryId);
		
  		
  		document.getElementById("boardRegist").action = "/plzdaengs/plzBoard";
  		document.getElementById("boardRegist").submit();
  	}
  	
  	
  </script>
  <body>
  
    <!-- navbar-->
    <header class="header">
      <nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow"><a href="#" class="sidebar-toggler text-gray-500 mr-4 mr-lg-5 lead"><i class="fas fa-align-left"></i></a><a href="index.jsp" class="navbar-brand font-weight-bold text-uppercase text-base">Bubbly Dashboard</a>
        <ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
          <li class="nav-item">
            <form id="searchForm" class="ml-auto d-none d-lg-block">
              <div class="form-group position-relative mb-0">
                <button type="submit" style="top: -3px; left: 0;" class="position-absolute bg-white border-0 p-0"><i class="o-search-magnify-1 text-gray text-lg"></i></button>
                <input type="search" placeholder="Search ..." class="form-control form-control-sm border-0 no-shadow pl-4">
              </div>
            </form>
          </li>
          <li class="nav-item dropdown mr-3"><a id="notifications" href="http://example.com" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle text-gray-400 px-1"><i class="fa fa-bell"></i><span class="notification-icon"></span></a>
            <div aria-labelledby="notifications" class="dropdown-menu"><a href="#" class="dropdown-item">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-violet text-white"><i class="fab fa-twitter"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">You have 2 followers</p>
                  </div>
                </div></a><a href="#" class="dropdown-item"> 
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-green text-white"><i class="fas fa-envelope"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">You have 6 new messages</p>
                  </div>
                </div></a><a href="#" class="dropdown-item">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-blue text-white"><i class="fas fa-upload"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">Server rebooted</p>
                  </div>
                </div></a><a href="#" class="dropdown-item">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-violet text-white"><i class="fab fa-twitter"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">You have 2 followers</p>
                  </div>
                </div></a>
              <div class="dropdown-divider"></div><a href="#" class="dropdown-item text-center"><small class="font-weight-bold headings-font-family text-uppercase">View all notifications</small></a>
            </div>
          </li>
          <li class="nav-item dropdown ml-auto"><a id="userInfo" href="http://example.com" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle"><img src="/plzdaengs/template/img/avatar-6.jpg" alt="Jason Doe" style="max-width: 2.5rem;" class="img-fluid rounded-circle shadow"></a>
            <div aria-labelledby="userInfo" class="dropdown-menu"><a href="#" class="dropdown-item"><strong class="d-block text-uppercase headings-font-family">Mark Stephen</strong><small>Web Developer</small></a>
              <div class="dropdown-divider"></div><a href="#" class="dropdown-item">Settings</a><a href="#" class="dropdown-item">Activity log       </a>
              <div class="dropdown-divider"></div><a href="login.html" class="dropdown-item">Logout</a>
            </div>
          </li>
        </ul>
      </nav>
    </header>
    <div class="d-flex align-items-stretch" id="document">
      <!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp" %>
		<!-- 사이드 sidebar -->
      <div class="page-holder w-100 d-flex flex-wrap">
        <div class="container-fluid px-xl-5" id="contents">
          <section class="py-5" style="max-height: none;">
            <div class="row">
              <div class=" col-lg-12 mb-4">
                <div class="card" style="min-width:40rem"><!-- 여기 끝에 글쓰기버튼 -->
                <form id="boardRegist" method="post" action="">
                <input type="hidden" name="cmd" value="<%=mode%>">
                <input type="hidden" name="post_id" id="post_id" value="<%=board.getPost_id()%>">
                <input type="hidden" name="board_category_id" id="board_category_id" value="">
                  <div class="card-header">
                  <div class="form-group row" >
                  <div class="col-md-2">
												<select id="board_category" class="form-control">
													<option value="">카테고리</option>
													<%
														for(int i=0; i<category.size();i++){
															PlzBoardCategory plz = category.get(i);
															if("update".equals(mode) && plz.getBoard_category_id().equals(board.getBoard_category_id())){
													%>
														<option value="<%=plz.getBoard_category_id()%>" selected="selected"><%=plz.getBoard_category_descripton()%></option>
													<%}else{ %>
														<option value="<%=plz.getBoard_category_id()%>"><%=plz.getBoard_category_descripton()%></option>
													<%
														}
													} %>
												</select>
											</div>
                        <label class="col-md-1 form-control-label">제목</label>
                        <div class="col-md-9">
                          <input type="text" id="post_subject" name="post_subject" class="form-control" value="">
                        </div>
                      </div>
                  </div>
                  <div class="card-body"><!-- 서머노트 들어갈 곳 -->
									<!-- 서머노트 예제 -->
									<textarea id="summernote" name="post_contents"></textarea>
									<script>
										$('#summernote').summernote({
											placeholder : '내용을 입력하세요',
											disableDragAndDrop: true,
											toolbar: [
												  ['style', ['style']],
												  ['font', ['bold', 'underline', 'clear']],
												  ['fontname', ['fontname']],
												  ['color', ['color']],
												  ['para', ['ul', 'ol', 'paragraph']],
												  ['table', ['table']],
												  ['insert', ['link', 'picture']],
												  ['view', ['codeview']],
												],
											lang : 'ko-KR',
											tabsize : 3,
											height : 400
										});
										
									
									</script>
									<!-- 여기까지 -->
								</div>
                </div><!-- 여기 끝에 글쓰기버튼 -->
                </form>
                <button class="btn btn-primary " type="button" style="background-color: #dc3545; float: left; padding: 0.2rem 0.8rem;">목록가기</button>
                <% if("regist".equals(mode)){ %>
               	 <button class="btn btn-primary " onclick="regist()" type="button" style="background-color: #dc3545; float: right; padding: 0.2rem 0.8rem;">등록하기</button>
                <%}else{ %>
                	<button class="btn btn-primary " onclick="regist()" type="button" style="background-color: #dc3545; float: right; padding: 0.2rem 0.8rem;">수정하기</button>
                <%} %>
                
              </div>
            </div>
          </section>
        </div>
        <%@ include file="/template/footer.jsp" %>
      </div>
    </div>
    <%@ include file="/template/default_js_link.jsp" %>
  </body>
</html>