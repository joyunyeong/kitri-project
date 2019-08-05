<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="userDetail" value="${requestScope.userDetail}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp"%>
<style>
.usermodify .input-group-prepend>button {
	width: inherit;
}

.usermodify .form-control-label {
	font-size: 1.0rem;
	margin-top: auto;
	margin-bottom: auto;
}

.usermodify .form-group input {
	font-size: 1.0rem;
}

.usermodify .file-hidden {
	display: none;
}

.usermodify label[for=ex_file] {
	border: 1px solid #4680ff;
	border-radius: 2rem;
	height: calc(2.25rem + 2px);
	line-height: calc(2.25rem + 2px);
	text-align: center;
	color: #4680ff;
}

.usermodify label[for=ex_file]:hover {
	background-color: #4680ff;
	color: white;
}

.usermodify .fileuploadimg {
	margin-left: 20px;
	max-width: 100px;
	max-height: 100px;
	padding: 0px;
}

.usermodify .usermodifyfileupload label, .usermodify .usermodifyfileupload input
	{
	margin-top: auto;
	margin-bottom: auto;
}

.usermodify h3 {
	font-size: 1.5rem;
}
</style>
<script>
//아이디 중복검사용
var idcheck = false;

$(function() {
	//드랍다운 이벤트
	$(".dropdown-item").click(dropdownItemClick);

	//주소검색 세팅
	zipsearchWebSetting($(".usermodifyaddress button"), $(".usermodifyaddress input[name=address]"));
	//주소검색
	$(".usermodifyaddress button").click(zipModalPopUp);
	
	//프로필 파일 업로드
	$("input[type=file].file-hidden").change(fileUploadChange);
	fileDropDown();
	
	//비밀번호 같은지 체크
	$("input[type=password]").keyup(passwordCheck);
	
	//회원가입 성공시 뜨는 모달
	$("#alertSuccess button").click(successAlertOkClick);
	
	//수정버튼 이벤트
	$(".usermodify #usermodifyBtn").click(userModifyClick);
	
	//취소
	$("#cancelBtn").click(cancelBtnClick);
	
	initData();
	
	
});

function cancelBtnClick(){
	document.location.href = "/plzdaengs/menu?act=home";
	return false;
}

function passwordCheck(){
	var password = $("input[name=password]").val();
	var passwordcheck = $("input[name=passwordcheck]").val();
	var passwordCheckLabel = $($("input[name=passwordcheck]").parent().siblings("label")[1]);
	
	passwordCheckLabel.css("color", "red");
	if(password == null || password.length == 0){
		passwordCheckLabel.text("비밀번호를 확인해주세요");
		return;
	}
	if(passwordcheck == null || passwordcheck.length == 0){
		passwordCheckLabel.text("비밀번호를 확인해주세요");
		return;
	}
	
	if(password != passwordcheck){
		passwordCheckLabel.text("비밀번호를 확인해주세요");
		return;
	}else{
		passwordCheckLabel.css("color", "green");
		passwordCheckLabel.text("비밀번호가 일치합니다");
		return;
	}
}


function initData() {
	var id = $("input[name=id]").val("${userDetail.user_id}");
	//var password = $("input[name=password]").val();
	//var passwordcheck = $("input[name=passwordcheck]").val();
	var emailid = $("input[name=emailid]").val("${userDetail.emailid}");
	
	var emaildomainInput = $("input[name=emaildomain]");
	var emaildomain = emaildomainInput.val("${userDetail.emaildomain}");
	emaildomainInput.siblings("button").text("${userDetail.emaildomain}");
	
	var nickname = $("input[name=nickname]").val("${userDetail.nickname}");
	var tel = $("input[name=tel]").val("${userDetail.userDetailDto.tel}");
		
	if('${userDetail.userDetailDto.gender}' == 'F'){
		$($("input[name=gender]")[0]).attr("checked", "checked");
	}else{
		$($("input[name=gender]")[1]).attr("checked", "checked");
	}
	
	var zipcodeInput = $("input[name=zipcode]");
	var zipcodeStr = "${userDetail.userDetailDto.zipcode}";
	var zipcode = zipcodeInput.val("${userDetail.userDetailDto.zipcode}");
	if(zipcodeStr == null || zipcodeStr.length == 0){
		zipcodeInput.siblings("button").text("우편번호");
	}else{
		zipcodeInput.siblings("button").text("${userDetail.userDetailDto.zipcode}");
	}
	
	
	var address = $("input[name=address]").val("${userDetail.userDetailDto.address}");
	var addressdetail = $("input[name=addressdetail]").val("${userDetail.userDetailDto.address_detail}");
	
	$(".fileuploadimg").attr("src", "${userDetail.user_img}");
}

function successAlertOkClick() {
	$("#alertSuccess").modal("hide");
	location.href = "/plzdaengs/index.jsp";
	return false;
}

function userModifyClick() {
	var id = $("input[name=id]").val();
	var password = $("input[name=password]").val();
	var passwordcheck = $("input[name=passwordcheck]").val();
	var emailid = $("input[name=emailid]").val();
	var emaildomain = $("input[name=emaildomain]").val();
	var nickname = $("input[name=nickname]").val();
	var tel = $("input[name=tel]").val();
	var gender = $("input[name=gender]").val();
	var zipcode = $("input[name=zipcode]").val();
	var address = $("input[name=address]").val();
	var addressdetail = $("input[name=addressdetail]").val();
	
	//필수 입력 확인
	if(password == null || password.length ==0){
		showAlertModal("필수값 입력 확인", "비밀번호를 입력하지 않으셨어요.");
		$("input[name=password]").focus();
		return false;
	}
	if(passwordcheck == null || passwordcheck.length ==0){
		showAlertModal("필수값 입력 확인", "비밀번호를 입력하지 않으셨어요.");
		$("input[name=passwordcheck]").focus();
		return false;
	}
	if(emailid == null || emailid.length ==0){
		showAlertModal("필수값 입력 확인", "이메일을 입력하지 않으셨어요.");
		$("input[name=emailid]").focus();
		return false;
	}
	if(emaildomain == null || emaildomain.length ==0){
		showAlertModal("필수값 입력 확인", "이메일을 입력하지 않으셨어요.");
		$("input[name=emaildomain]").focus();
		return false;
	}
	if(nickname == null || nickname.length ==0){
		showAlertModal("필수값 입력 확인", "닉네임을 입력하지 않으셨어요.");
		$("input[name=nickname]").focus();
		return false;
	}
	
	userModify();
	
	return false;
}

function userModify() {
	var form = $("#usermodifyForm")[0];
	var formData = new FormData(form);
	$.ajax({
		url : "userupdate"
		, method : "post"
		, processData : false
		, contentType : false
		, data : formData
		, success : function (result) {
			alert(result.trim());
			if(result.trim() == '1'){
				showSuccessAlertModal("회원정보 수정", "회원정보가 수정되었습니다");
			}else{
				showAlertModal("회원정보 수정", "회원정보 수정 실패하였습니다.");
			}
		}
		, error : function (jqXHR, result) {
			showAlertModal("에러", result + "관리자에게 연락하세요.");
		}
	});
	
	return false;
}

function fileDropDown() {
	var fileInputText = $(".usermodifyfileupload input[type=text]");
	var fileInput = $(".usermodifyfileupload input[type=file]");

	//드래그 한채로 들어오기
	fileInputText.on("dragenter", function(e) {
		e.stopPropagation();
		e.preventDefault();
		fileInputText.css("border-color", "#c6d8ff");
		fileInputText.css("box-shadow",
				"0 0 0 0.2rem rgba(70, 128, 255, 0.25)");
	});

	//드래그 한채로 나가기
	fileInputText.on("dragleave", function(e) {
		e.stopPropagation();
		e.preventDefault();
		fileInputText.css("border-color", "#ced4da");
		fileInputText.css("box-shadow", "");
	});
	//??
	fileInputText.on('dragover', function(e) {
		e.stopPropagation();
		e.preventDefault();
		fileInputText.css("border-color", "#c6d8ff");
		fileInputText.css("box-shadow",
				"0 0 0 0.2rem rgba(70, 128, 255, 0.25)");
	});

	//드래그 객체 놓기
	fileInputText.on('drop', function(e) {
		e.preventDefault();
		fileInputText.css("border-color", "#ced4da");
		fileInputText.css("box-shadow", "");

		var files = e.originalEvent.dataTransfer.files;

		if (files != null) {
			if (files.length < 1) {
				showAlertModal("이미지 업로드 경고", "잘못된 파일입니다.");
				return;
			}
			fileusermodifyProcess(files);
		} else {
			showAlertModal("이미지 업로드 경고", "프로필 등록을 실패하였습니다.");
		}
	});
}

function fileusermodifyProcess(files) {
	var fileInputText = $(".usermodifyfileupload input[type=text]");
	var fileInput = $(".usermodifyfileupload input[type=file]");
	var imgtag = $(".usermodifyfileupload img");

	var fileName = files[0].name;
	imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");

	if (!(files[0].type.startsWith('image/'))) {
		alert(files[0].type);
		showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
		return;
	}

	fileInput[0].files = files;
	fileName = fileInput[0].files[0].name;
	$(fileInputText[0]).val(fileName);
	
	var reader =new FileReader();
	reader.onload = function (e) {
		imgtag.prop("src", e.target.result);
	}
	reader.readAsDataURL(fileInput[0].files[0]);
}

function dropdownItemClick() {
	var text = $(this).text();
	$(this).parent().siblings("button").text(text);
	$(this).parent().siblings("input[type=hidden]").val(text);
}

function zipModalPopUp() {
	$("#doro").val("");
	$("#zipModal").modal("show");
}

function fileUploadChange() {
	var filename = this.files[0].name;
	var imgtag = $(this).siblings("img");
	imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");
	
	
	if (!this.files[0].type.startsWith("image/")) {
		this.files[0].value = "";
		showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
		return;
	}

	imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");
	var reader =new FileReader();
	reader.onload = function (e) {
		imgtag.prop("src", e.target.result);
	}
	reader.readAsDataURL(this.files[0]);
	$(this).siblings("input[type=text]").val(filename);

}
</script>
</head>
<body>
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
					class="nav-link dropdown-toggle"><img
						src="/plzdaengs/template/img/avatar-6.jpg" alt="Jason Doe"
						style="max-width: 2.5rem;" class="img-fluid rounded-circle shadow"></a>
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

	<div class="d-flex align-items-stretch" id="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp"%>
		<!-- 사이드 sidebar -->

		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">
				<!-- section -->
				<section class="usermodify">
					<!-- 경고창 모달 -->
					<%@ include file="/template/alert_danger.jsp"%>
					<%@ include file="/template/alert_success.jsp"%>
					<!-- 우편번호 검색 모달 -->
					<%@ include file="/template/zipsearchWeb.jsp"%>
					<div class="col-lg-10 mb-5">
						<div class="card">
							<div class="card-header">
								<h3 class="h6 text-uppercase mb-0">회원 정보 수정</h3>
							</div>
							<div class="card-body">
								<form class="form-horizontal" enctype="multipart/form-data"
									method="post" id="usermodifyForm">
									<input type="hidden" name="act" value="userupdate">
									<div class="form-group row usermodifyid">
										<label class="col-md-3 form-control-label">아이디(*)</label>
										<div class="col-md-5">
											<input type="text" placeholder="아이디를 입력하세요"
												class="form-control" name="id" readonly="readonly">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifypassword">
										<label class="col-md-3 form-control-label">비밀번호(*)</label>
										<div class="col-md-5">
											<input type="password" placeholder="비밀번호를 입력하세요"
												class="form-control" name="password">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifypassword">
										<label class="col-md-3 form-control-label">비밀번호
											다시입력(*)</label>
										<div class="col-md-5">
											<input type="password" placeholder="비밀번호를 입력하세요"
												class="form-control" name="passwordcheck">
										</div>
										<label class="col-md-4 form-control-label">비밀번호 확인</label>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifyemail">
										<label class="col-md-3 form-control-label">이메일(*)</label>
										<div class="col-md-3">
											<input type="text" placeholder="이메일을 입력하세요"
												class="form-control" name="emailid">
										</div>
										<label class="form-control-label text-label">@</label>
										<div class="input-group-prepend col-md-3">
											<input type="hidden" name="emaildomain" value="">
											<button type="button" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="false"
												class="btn btn-outline-primary dropdown-toggle">이메일선택</button>
											<div class="dropdown-menu">
												<span class="dropdown-item">gmail.com</span> <span
													class="dropdown-item">naver.com</span> <span
													class="dropdown-item">daum.net</span>
											</div>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifynickname">
										<label class="col-md-3 form-control-label">닉네임(*)</label>
										<div class="col-md-5">
											<input type="text" placeholder="닉네임을 입력해주세요"
												class="form-control" name="nickname">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifyfileupload">
										<label class="col-md-3 form-control-label">프로필등록</label>
										<div class="col-md-9 input-group-prepend">
											<label for="ex_file" class="col-md-3">프로필선택</label> <input
												type="file" class="form-control file-hidden" id="ex_file"
												accept="image/*" name="imgdata"> <input type="text"
												placeholder="파일을 등록해주세요" class="form-control col-md-5 "
												name="userimg" readonly> <img alt=""
												class="col-md-2 fileuploadimg"
												src="/plzdaengs/template/img/basic_user_profile.png">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifytel">
										<label class="col-md-3 form-control-label">전화번호</label>
										<div class="col-md-5">
											<input type="tel" placeholder="전화번호를 입력해주세요"
												class="form-control" name="tel">
										</div>
										<label class="col-md-2 form-control-label">(-)은 생략</label>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifygender">
										<label class="col-md-3 form-control-label">성별</label>
										<div class="col-md-5">
											<div
												class="custom-control custom-radio custom-control-inline">
												<input id="genderfemaleradio" type="radio"
													class="custom-control-input" name="gender" value="F">
												<label for="genderfemaleradio" class="custom-control-label">여자</label>
											</div>
											<div
												class="custom-control custom-radio custom-control-inline">
												<input id="gendermaleradio" type="radio"
													class="custom-control-input" name="gender" value="M">
												<label for="gendermaleradio" class="custom-control-label">남자</label>
											</div>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row usermodifyaddress">
										<label class="col-md-3 form-control-label">주소</label>
										<div class="col-md-9">
											<input type="hidden" name="zipcode" value="">
											<button type="button"
												class="btn btn-outline-primary col-md-4">우편번호</button>
											<input type="text" placeholder="주소를 입력주세요"
												class="form-control" name="address" readonly> <input
												type="text" placeholder="상세주소를 입력해주세요" class="form-control"
												name="addressdetail">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row button-group">
										<div class="col-md-9 ml-auto">
											<button type="reset" class="btn btn-primary" id="cancelBtn">취소</button>
											<button type="submit" class="btn btn-primary"
												id="usermodifyBtn">회원수정</button>
											<button type="button" class="btn btn-danger delete" id="deleteBtn">탈퇴</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</section>
				</div>
			<%@ include file="/template/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/template/default_js_link.jsp"%>
</body>
</html>