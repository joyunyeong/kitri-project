<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userInfo" value="${sessionScope.userInfo}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp" %>
<c:if test="${!empty userInfo}">
<script>
document.location.href = "/plzdaengs/diary/calendar.jsp";
function loginSuccessProcess(){
	//var profilePath = "${userInfo.user_img}";
	//$("#profile").css("background-image", "url('"+profilePath + "')");
	mainSectionChange();
}
function mainSectionChange() {
	$("#contents").html("<h3>여기에 다이어리 들어가야함.</h3>");
}
</script>
</c:if>
<script type="text/javascript">
$(function() {
	$("#loginbtn").click(loginModalShow);
	$("#loginmodal .modal-success").click(login);
	//$("#loginmodal .kakao-login").click(loginWithKakao);
	//회원가입
	$("#registerbtn").click(registerbtnClick);
	
	$(".modal-cancel").click(function() {
		var name = $(this).attr("name");
		$("#"+name).modal("hide");
		return false;
	});
	
	//2중모달 처리
	$("#alert").on('hidden.bs.modal', function(){
		$("body").prop("class", "modal-open");
	  });
	
	//로그인시 패스워드 엔터처리
	$("#loginmodal input[type=password]").keyup(passwordKeyUp);
});
function passwordKeyUp(e) {
	if(e.keyCode == 13){
		$("#loginmodal .modal-success").trigger("click");
	}
}

function registerbtnClick() {
	$.ajax({
		url : "user"
		, data : {
			act : "userjoin"
		}
		, success : function (result) {
			$("#contents").html(result);
		}
	});
	return false;
}

function loginAlertBtnClick() {
	$("#alert").modal("hide");
	$("body").prop("class", "modal-open");
	return false;
}

function loginProcess() {
	$("#profile").css("background-image", "url('/plzdaengs/template/img/profile.jpg')");
}
function loginModalShow() {
	$("#loginmodal").modal("show");
}

function login(){
	var id = $("#loginmodal input[name=id]").val();
	var password = $("#loginmodal input[name=password]").val();
	
	var idPattern = /^[A-Za-z]{1}[A-Za-z0-9]{3,10}$/;
	
	if(id == null || id.length == 0){
		showAlertModal("로그인 경고", "아이디를 입력하세요");
		return;
	}
	if(password == null || password.length == 0){
		showAlertModal("로그인 경고", "비밀번호를 입력하세요");
		return;
	}
	
	//로그인 실패를 위해 ajax
	$.ajax({
		url : "userlogin"
		, method : "post"
		, data : $("#loginmodal form").serialize()
		, success : function(result){
			if(result.trim() != "1"){
				//$("#loginalert p").html(result);
				showAlertModal("로그인 경고", result);
			}else{
				//location.href = "/plzdaengs/menu?act=main";
				location.href = "/plzdaengs/diary/calendar.jsp";
			}
		}
	});
}
</script>
<style type="text/css">
#loginmodal .modal-lg{
	width: 50%;
}
#loginmodal .modal-content{
	border-radius: 5%;
}
#loginmodal .form-control-label{
	font-size: 1.2rem;
}
#loginmodal .form-group input{
	height: 3rem;
	font-size: 1.2rem;
}
#loginmodal .btn-group button{
	height: 3rem
}
#loginmodal h5{
	font-size: 1.7rem;	
}
</style>
</head>
<body>
	<!-- navbar-->	
	<%@ include file="/template/header.jsp" %>

	<div class="d-flex align-items-stretch" id ="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp" %>
		<!-- 사이드 sidebar -->
		
		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">
				<!-- 로그인 모달창 -->
				<div id="loginmodal" class="modal fade" role="dialog">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header text-center">
								<h5 class="modal-title" id="myModalLabel" style="margin-left: auto;">로그인</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
							</div>
							<div class="modal-body">
								<form>
									<div class="form-group">
				                        <label class="form-control-label text-uppercase" >아이디</label>
				                        <input type="text" placeholder="아이디를 입력하세요" class="form-control" required="required" name="id">
				                      </div>
				                      <div class="form-group">       
				                        <label class="form-control-label text-uppercase">비밀번호</label>
				                        <input type="password" placeholder="" class="form-control" name="password" required="required">
				                      </div>
			            		</form>
			            		<div class="">
				            		<button class="btn btn-primary btn-login-sm modal-success" name="loginmodal">로그인</button>
				            		<!--  <button class="btn btn-primary btn-login-sm kakao-login" name="loginmodal">카카오 로그인</button>-->
				            		<button class="btn btn-primary btn-login-sm modal-cancel" name="loginmodal">취소</button>
				            	</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 로그인 시 뜨는 경고창 -->
				<%@ include file="/template/alert_danger.jsp" %>
				
				
				<!-- section 1 -->
				<section class="py-5" id="mainSection">
				
					<div class="w-100">
						<h1 class="text-main">댕댕이를 <span class="text-primary">부탁해~</span></h1>
						<div class="subheading">
							kitri 2nd Project 4조
						</div>
						<div class="login-icons">
							<button class="btn btn-primary btn-login" id="loginbtn">로그인</button>
							<button class="btn btn-primary btn-login" id="registerbtn">회원가입</button>
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