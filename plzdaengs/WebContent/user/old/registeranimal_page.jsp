<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp"%>
<script type="text/javascript">
	$(function() {
		//파일 업로드시 관련 이벤트 호출
		$("input[type=file].file-hidden").change(fileUploadChange);
		$(".kindother").click(kindotherClick);
		
		fileDropDown();
				
		//품종클릭시 이벤트
		$(".dogkind").click(dogkindClick);
		
		$(".datepicker-here").datepicker({
			language: "kr"
			, autoClose: true
		});
		
		//예방접종 추가이벤트
		$(".vaccinlistitem>.plus").click(vaccinplusClick);
		
		$(".vaccinlistitem>.dropdown-menu>.dropdown-item").click(vaccindropdownitemClick);
		
	});
	
	function vaccindropdownitemClick() {
		var text = $(this).text();
		$(this).parent().siblings("input[type=hidden]").val(text);
		$(this).parent().siblings(".dropdown-toggle").text(text);
	}
	
	function vaccinplusClick() {
		var vaccinlist = $(".vaccinlist");
		var vaccinlistitems = vaccinlist.find(".vaccinlistitem");
		var vaccinlistitemlast = $(vaccinlistitems[vaccinlistitems.length-1]);
		var newvaccinlistitem = vaccinlistitemlast.clone();
		
		vaccinlistitemlast.find(".plus").remove();		
		//
		newvaccinlistitem.find(".dropdown-toggle").text("예방접종 종류");
		newvaccinlistitem.find("input[type=hidden]").val("");
		newvaccinlistitem.find("input[type=text]").val("");
		
		
		newvaccinlistitem.find("input[type=text]").datepicker({
		    language: 'kr'
		    , autoClose: true
		});
		
		newvaccinlistitem.find(".dropdown-menu>.dropdown-item").click(vaccindropdownitemClick);
		newvaccinlistitem.find(".plus").click(vaccinplusClick);
		vaccinlist.append(newvaccinlistitem);
		
		return false;
	}
	
	function dogkindClick(){
		var dogkindclass = $(".dogkind");
		//모두 기본css 로 통일
		dogkindclass.css("color", "#4680ff");
		dogkindclass.css("border-color", "#4680ff");
		dogkindclass.css("background-color", "white");
		
		//클릭한 객체만 css변경
		$(this).css("border-color", "#c6d8ff");
		$(this).css("box-shadow", "0 0 0 0.2rem rgba(70, 128, 255, 0.25)");
		$(this).css("color", "white");
		$(this).css("background-color", "#4680ff");
		
		var text = $(this).text();
		//클릭한 객체로 hidden input변경
		$(".dogkinddiv input[type=hidden]").val(text);
	}
	
	function kindotherClick(){
		$("#kindothermodal").modal("show");
	}
	
	function dropdownItemClick() {
		var text = $(this).text();
		$(this).parent().siblings("button").text(text);
	}

	function zipModalPopUp() {
		$("#doro").val("");
		$("#zipModal").modal("show");
	}

	function fileUploadChange() {
		var filename = $(this)[0].files[0].name;
		var imgtag = $(this).siblings("img");
		imgtag.prop("src", "/plzdaengs/template/img/basic_pet_profile.jpg");
		
		if (!($(this)[0].type.startsWith('image/'))) {
			$(this)[0].value = "";
			showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
			return;
		}
		
		$(this).siblings("input[type=text]").val(filename);

		
		
		if (window.FileReader) {

			var reader = new FileReader();
			reader.onload = function(e) {
				var src = e.target.result;
				imgtag.prop("src", src);
			}
			reader.readAsDataURL($(this)[0].files[0]);
		} else {
			$(this)[0].select();
			$(this)[0].blur();
			var imgSrc = document.selection.createRange().text;
			imgtag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""
					+ imgSrc + "\")";
		}
	}

	function fileDropDown() {
		var fileInputText = $(".registerfileupload input[type=text]");
		var fileInput = $(".registerfileupload input[type=file]");

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
				fileRegisterProcess(files);
			} else {
				showAlertModal("이미지 업로드 경고", "프로필 등록을 실패하였습니다.");
			}
		});
	}

	function fileRegisterProcess(files) {
		var fileInputText = $(".registerfileupload input[type=text]");
		var fileInput = $(".registerfileupload input[type=file]");
		var imgtag = $(".registerfileupload img");

		var fileName = files[0].name;

		if (!(files[0].type.startsWith('image/'))) {
			showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
			return;
		}

		fileInput[0].files = files;
		fileName = fileInput[0].files[0].name;
		$(fileInputText[0]).val(fileName);
		//

		imgtag.prop("src", "/plzdaengs/template/img/basic_pet_profile.jpg");
		if (window.FileReader) {
			var reader = new FileReader();
			reader.onload = function(e) {
				var src = e.target.result;
				imgtag.prop("src", src);
			}
			reader.readAsDataURL(fileInput[0].files[0]);
		} else {
			fileInput[0].select();
			fileInput[0].blur();
			var imgSrc = document.selection.createRange().text;
			imgtag.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enable='true',sizingMethod='scale',src=\""
					+ imgSrc + "\")";
		}
	}
</script>
<style type="text/css">
.register .input-group-prepend>button {
	width: inherit;
}

.register .form-control-label {
	font-size: 1.0rem;
	margin-top: auto;
	margin-bottom: auto;
}

.register .form-group input {
	font-size: 1.0rem;
}

.register .file-hidden {
	display: none;
}

.register label[for=ex_file] {
	border: 1px solid #4680ff;
	border-radius: 2rem;
	height: calc(2.25rem + 2px);
	line-height: calc(2.25rem + 2px);
	text-align: center;
	color: #4680ff;
}

.register label[for=ex_file]:hover {
	background-color: #4680ff;
	color: white;
}

.register .fileuploadimg {
	margin-left: 20px;
	max-width: 100px;
	max-height: 100px;
	padding: 0px;
}

.register .registerfileupload label, .register .registerfileupload input
	{
	margin-top: auto;
	margin-bottom: auto;
}

.register h3 {
	font-size: 1.5rem;
}

<!--펫 css -->
input[type=checkbox] {
	-ms-transform: scale(1.5); /* IE */
	-moz-transform: scale(1.5); /* FF */
	-webkit-transform: scale(1.5); /* Safari and Chrome */
	-o-transform: scale(1.5); /* Opera */
}

#zipModal, #zipModal .form-control{
		font-size: 1rem;
}

.vaccinlistitem>.plus{
	/* background-image: url("/plzdaengs/template/img/plus.png");
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover; */
	font-size: 1.5rem;
	font-weight: 600;
	line-height: 1.2;
	text-align: center;
} 
.dogkind{
	padding-left: inherit;
	padding-right: inherit;
}
</style>
</head>
<body>
	<!-- 경고창 모달 -->
	<%@ include file="/template/alert_danger.jsp"%>
	<!-- 강아지 기타 눌렸을 때 모달 -->
	<div id="kindothermodal" class="modal fade" role="dialog">
		<h5 class="modal-title" id="myModalLabel">강아지 품종 검색</h5>
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header text-center">
					<label style="margin-left: auto; margin-bottom:auto; margin-top:auto; font-size: 1.5rem">강아지 품종 검색</label>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body text-center">
					
					<div class="input-group" align="left">
						<input type="text" class="form-control" id="doro" name="doro"
							placeholder="검색 할 품종명"> <span
							class="input-group-btn"> <input type="button"
							class="btn btn-warning" value="검색" id="searchBtn">
						</span>
					</div>
					<div style="width: 100%; height: 500px; overflow: auto; margin-top: 1%;">
		
					</div>
				</div>
			</div>
		</div>
	</div>
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
				<!-- section 1 -->
				<section class="register">
					<div class="col-lg-10 mb-5">
						<div class="card">
							<div class="card-header">
								<h3 class="h6 text-uppercase mb-0">반려동물 등록</h3>
							</div>
							<div class="card-body">
								<form class="form-horizontal">
									<div class="form-group row">
										<label class="col-md-3 form-control-label">반려동물 이름(*)</label>
										<div class="col-md-5">
											<input type="text" placeholder="반려동물 이름을 입력하세요"
												class="form-control" required>
										</div>
										<label class="col-md-2 form-control-label">같은 이름으로 등록된
											펫확인</label>
									</div>
									<div class="line"></div>
									<div class="form-group row dogkinddiv">
										<label class="col-md-3 form-control-label">반려동물 품종</label>
										<div class="col-md-8">
											<input type="hidden" value="" name="kind">
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">말티즈</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">푸들</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">포메</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">시츄</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">비숑</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">요크셔</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">치와와</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">스피츠</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind">믹스</button>
											<button type="button"
												class="btn btn-outline-primary col-md-2 dogkind kindother">기타</button>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-md-3 form-control-label">반려동물 성별</label>
										<div class="col-md-5">
											<div
												class="custom-control custom-radio custom-control-inline">
												<input id="genderfemaleradio" type="radio"
													class="custom-control-input" name="gender" value="female">
												<label for="genderfemaleradio" class="custom-control-label">여아</label>
											</div>
											<div
												class="custom-control custom-radio custom-control-inline">
												<input id="gendermaleradio" type="radio"
													class="custom-control-input" name="gender" value="male">
												<label for="gendermaleradio" class="custom-control-label">남아</label>
											</div>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row calendar">
										<label class="col-md-3 form-control-label">반려동물 생일</label>
										<div class="col-md-5">
											<input type='text' placeholder="반려동물 생일을 입력해주세요" class="form-control datepicker-here" data-auto-close="true" data-position="right top" data-language='kr'/>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row registerfileupload">
										<label class="col-md-3 form-control-label">프로필등록</label>
										<div class="col-md-9 input-group-prepend">
											<label for="ex_file" class="col-md-2">프로필선택</label> <input
												type="file" class="form-control file-hidden" id="ex_file"
												accept="image/*"> <input type="text"
												placeholder="파일을 등록해주세요" class="form-control col-md-5 "
												readonly> <img alt="" class="col-md-2 fileuploadimg"
												src="/plzdaengs/template/img/basic_pet_profile.jpg">
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<label class="col-md-3 form-control-label">대표 반려동물 설정</label>
										<div class="col-md-8">
											<div class="custom-control custom-checkbox">
												<input id="mainpetCheck" type="checkbox"
													class="custom-control-input"> 
													<label
													for="mainpetCheck" class="custom-control-label">대표
													펫으로 설정(대표 펫은 좌측의 프로필에 보여집니다.)</label>
											</div>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row vaccin">
										<label class="col-md-3 form-control-label">반려동물 예방접종</label>
										<div class="col-md-9">
											<label class="form-control-label">최근 1년이내 예방접종을 입력해주세요.</label>
											<div class="vaccinlist">
												<div class="input-group-prepend vaccinlistitem ">
													<input type="hidden" value="" name="vaccin">
													<button type="button" data-toggle="dropdown"
														aria-haspopup="true" aria-expanded="false"
														class="btn btn-outline-primary dropdown-toggle col-md-3">예방접종 종류</button>
													<div class="dropdown-menu">
														<span class="dropdown-item">종합백신</span> 
														<span class="dropdown-item">코로나 장염 예방접종</span>
														<span class="dropdown-item">켄넬코프 예방접종</span>
													</div>
													<input type='text' class="form-control datepicker-here col-md-5" data-position="right top" data-language='kr' readonly/>
													<button type="button" class="btn btn-outline-primary plus">+</button>
												</div>
											</div>
										</div>
									</div>
									<div class="line"></div>
									<div class="form-group row">
										<div class="col-md-9 ml-auto">
											<button type="reset" class="btn btn-primary">취소</button>
											<button type="submit" class="btn btn-primary">등록</button>
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