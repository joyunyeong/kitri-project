<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp" %>
<script>
$(function(){
	$('#gMember').attr("class", "nav-link active");
	$('#gInfo').removeClass("active");
	$('#gMeet').removeClass("active");
	
	 $.ajax({
			url: '/plzdaengs/groupfront',
			method:'POST',
			data:{
				act:"memberlist"},
			success:function(result){
				$("#memberlist").html(result);	
				var bArr = $('tbody#memberlist>tr>td>button');
				$(bArr).click(btnClick);
				
			} 
	
	});
	 
	 
	
 function btnClick(){
		 alert('처리되었습니다.');
		  var rowOfBtn = $(this);
		  var tr = rowOfBtn.parent().parent();
		  var memberId = tr.children('.member_id').text();
		 
		$.ajax({
		 url: '/plzdaengs/groupfront',
		 method:'POST',
		 data:{ act:'managemember',
			 member_id : memberId,
			 member_status:$('input[name=member_status]').val()},
		 success:function(result){
			 document.location.href='/plzdaengs/group/managemember.jsp';
		 }
		});

	 }
	 
	 
});

function moveManagegroupinfo(){
	$.ajax({
		url : '/plzdaengs/groupfront',
		method : 'POST',
		data : {act : 'groupmanage'},
		success : function(groupdetail) {
			$("section").html(groupdetail);
		}

	});
}

</script>
</head>
<body>
<!-- navbar-->	
	<header class="header">
		<nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
			<a href="#" class="sidebar-toggler text-gray-500 mr-4 mr-lg-5 lead"><i
				class="fas fa-align-left"></i></a><a href="index.html"
				class="navbar-brand font-weight-bold text-uppercase"></a>
			<ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
				<li class="nav-item">
					<form id="searchForm" class="ml-auto d-none d-lg-block">
						<div class="form-group position-relative mb-0">
							<button type="submit" style="top: -3px; left: 0;"
								class="position-absolute bg-white border-0 p-0">
								<i class="o-search-magnify-1 text-gray text-lg"></i>
							</button>
							<input type="search" placeholder="Search ..."
								class="form-control form-control-sm border-0 no-shadow pl-4">
						</div>
					</form>
				</li>
				<li class="nav-item dropdown mr-3"><a id="notifications"
					href="http://example.com" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"
					class="nav-link dropdown-toggle text-gray-400 px-1"><i
						class="fa fa-bell"></i><span class="notification-icon"></span></a>
					<div aria-labelledby="notifications" class="dropdown-menu">
						<a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-violet text-white">
									<i class="fab fa-twitter"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">You have 2 followers</p>
								</div>
							</div>
						</a><a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-green text-white">
									<i class="fas fa-envelope"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">You have 6 new messages</p>
								</div>
							</div>
						</a><a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-blue text-white">
									<i class="fas fa-upload"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">Server rebooted</p>
								</div>
							</div>
						</a><a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-violet text-white">
									<i class="fab fa-twitter"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">You have 2 followers</p>
								</div>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item text-center"><small
							class="font-weight-bold headings-font-family text-uppercase">View
								all notifications</small></a>
					</div></li>
				<li class="nav-item dropdown ml-auto"><a id="userInfo"
					href="http://example.com" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"
					class="nav-link dropdown-toggle"><img src="/plzdaengs/template/img/avatar-6.jpg"
						alt="Jason Doe" style="max-width: 2.5rem;"
						class="img-fluid rounded-circle shadow"></a>
					<div aria-labelledby="userInfo" class="dropdown-menu">
						<a href="#" class="dropdown-item"><strong
							class="d-block text-uppercase headings-font-family">Mark
								Stephen</strong><small>Web Developer</small></a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item">Settings</a><a href="#"
							class="dropdown-item">Activity log </a>
						<div class="dropdown-divider"></div>
						<a href="/plzdaengs/login.html" class="dropdown-item">Logout</a>
					</div></li>
			</ul>
		</nav>
	</header>

	<div class="d-flex align-items-stretch" id ="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp" %>
		<!-- 사이드 sidebar -->
		
		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">

				 <section class="py-5">
				 
		        <ul style="display: inline-block" class="nav nav-tabs nav-pills nav-justified grouplisttype">
				    <li class="nav-item" style="float:left;margin-right: 0.5rem;border-radius:0.5rem;" >
				      <a id=gInfo class="nav-link" style="border-radius:0.5rem;" href='#' onclick="moveManagegroupinfo()">소모임 정보변경</a>
				    </li>
				    <li class="nav-item" style="float:left;border-radius:0.5rem;">
				      <a id=gMeet class="nav-link" style="border-radius:0.5rem;" href='/plzdaengs/group/managemeeting.jsp'>소모임 일정관리</a>
				    </li>
				    <li class="nav-item" style="float:left;border-radius:0.5rem;">
				      <a id=gMember class="nav-link" style="border-radius:0.5rem;" href='/plzdaengs/group/managemember.jsp'>소모임원 관리</a>
				    </li>
				 </ul>
            <div class="row">
              <div class="col-lg-12 mb-5">
                <div class="card">
                  <div class="card-header">
                    <h6 class="text-uppercase mb-0">소모임원 관리</h6>
                  </div>
                  <div class="card-body">                           
                    <table class="table table-striped table-hover card-text">
                      <thead>
                        <tr>
                          <th>#</th>
                          <th>타입</th>
                          <th>아이디</th>
                          <th>닉네임</th>
                          <th>가입일</th>
                          <th>관리</th>
                        </tr>
                      </thead>
                      <tbody id="memberlist">

                      </tbody>
                    </table>
                    <!--  <div>페이징처리영역</div> -->
                    <div class="col-lg-8 mb-4 mb-lg-0" style="padding-left:0px;">
               <div class="searchgroup button-group">
	             	 <form>
		             	 <select class="form-control col-md-3" name="searchoption" style="float: left;">
		             	 <option>아이디</option>
		             	 <option>닉네임</option>
		             	 <option>타입</option>
		             	 </select>
		             	 <input class="form-control col-md-6" type="text" name="searchword" size="50" style="float: left;">
		             	 <button type="submit" class="btn btn-info">검색</button>
	             	 </form>
	             	 </div>
	             	 </div>
                  </div>
                </div>
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