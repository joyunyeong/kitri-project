<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	var petNameCheck = false;
	$(function() {
		//파일 업로드시 관련 이벤트 호출
		$("input[type=file].file-hidden").change(fileUploadChange);
		
		$(".kindother").click(kindotherClick);
		$("#kindothermodal .modal-body input[type=text]").keyup(kindselectKeyup);
		
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
		
		//등록 버튼
		$(".registeranimal #registerBtn").click(animalRegisterClick);
		
		//펫이름 중복 확인
		$(".registeranimal input[name=petname]").keyup(petnameKeyup);
	
		//완료 모달의 확인 선택시
		$("#alertSuccess button").click(function() {
			$("#alertSuccess").modal("hide");
			document.location.href="/plzdaengs/menu?act=animals";
		});
		
		//취소
		$("#cancelBtn").click(cancelBtnClick);
	});
	
	function cancelBtnClick(){
		document.location.href = "/plzdaengs/menu?act=animals";
		return false;
	}
	
	function kindselectKeyup() {
		var text = $(this).val();
		//console.log(text);
		var buttons = $("#kindothermodal .kindDiv").find("button");
		for(var i=0 ; i<buttons.length; i++){
			var button = $(buttons[i]);
			if(button.text().indexOf(text) < 0 ){
				button.hide();
			}else{
				button.show();
			}
		}
	}
	
	function petnameKeyup() {
		var input = $(this).val();
		var label = $(this).parent().siblings("label[role=check]");
		//console.log(label);
		
		$.ajax({
			url : "petnamecheck"
			, data : {
				petname : input
			}
			, success: function (result) {
				//alert(result);
				result = result.trim();
				var msg = "같은 이름으로 등록된 이름이 있습니다";
				label.css("color", "red");
				petNameCheck = false;
				if(result == 0){
					msg = "사용 가능한 이름입니다";
					label.css("color", "green");
					petNameCheck = true;
				}
				label.text(msg);
			}
		});
		
		return false;
	}
	
	function animalRegisterClick() {
		//필수값 확인
		var petname = $(".registeranimal input[name=petname]").val();
		var breedcode = $(".registeranimal input[name=breedcode]").val();
		
		if(petname == null || petname.length == 0){
			showAlertModal("필수값 입력", "반려동물의 이름을 입력해주세요");
			return;
		}
		if(breedcode == null || breedcode.length == 0){
			showAlertModal("필수값 입력", "반려동물의 품종을 선택해주세요");
			return;
		}
		
		var form = $(".registeranimal form")[0];
		console.log(form);
		var formData = new FormData(form);
		console.log(formData);
		
		$.ajax({
			url : "petregister"
			, method : "post"
			, processData : false
			, contentType : false
			, data : formData
			, success: function(result) {
				result = result.trim();
				if(result == "1"){
					showSuccessAlertModal("반려동물 등록", "등록되었습니다");
				}else{
					showAlertModal("시스템 에러", "관리자에게 문의하세요");
				}
			}
			, error: function() {
				
			}
		});
		
		return false;
	}
	
	function vaccindropdownitemClick() {
		var code = $(this).attr("data");
		var text = $(this).text();
		$(this).parent().siblings("input[type=hidden]").val(code);
		$(this).parent().siblings(".dropdown-toggle").text(text);
	}
	
	function vaccinplusClick() {
		var vaccinlist = $(".vaccinlist");
		var vaccinlistitems = vaccinlist.find(".vaccinlistitem");
		var vaccinlistitemlast = $(vaccinlistitems[vaccinlistitems.length-1]);
		var newvaccinlistitem = vaccinlistitemlast.clone();
		
				
		//기존  item 플러스단추 제거
		vaccinlistitemlast.find(".plus").remove();
		
		//기존 item 에 x버튼 달기
		var xButton = '<button type="button" class="btn btn-outline-primary delete">x</button>';
		vaccinlistitemlast.append(xButton);
		vaccinlistitemlast.find(".delete").click(vaccindeleteClick);
		
		//새로 추가될 element 초기화
		newvaccinlistitem.find(".dropdown-toggle").text("예방접종 종류");
		newvaccinlistitem.find("input[type=hidden]").val("");
		newvaccinlistitem.find("input[type=text]").val("");
		
		newvaccinlistitem.find("input[type=text]").datepicker({
		    language: 'kr'
		    , autoClose: true
		});
		
		//이벤트 추가
		newvaccinlistitem.find(".dropdown-menu>.dropdown-item").click(vaccindropdownitemClick);
		newvaccinlistitem.find(".plus").click(vaccinplusClick);
		vaccinlist.append(newvaccinlistitem);
		
		return false;
	}
	
	function vaccindeleteClick() {
		var item = $(this).parent();
		item.remove();
	}
	
	function dogkindClick(){
		
		$.each($(".class"), function(index, element) {
			index
		});
		var dogkindclass = $(".dogkind");
		//모두 기본css 로 통일
		dogkindclass.css("color", "#4680ff");
		dogkindclass.css("border-color", "#4680ff");
		dogkindclass.css("background-color", "white");
		dogkindclass.css("box-shadow", "");
		
		//클릭한 객체만 css변경
		$(this).css("border-color", "#c6d8ff");
		$(this).css("box-shadow", "0 0 0 0.2rem rgba(70, 128, 255, 0.25)");
		$(this).css("color", "white");
		$(this).css("background-color", "#4680ff");
		
		var text = $(this).attr("data");
		//클릭한 객체로 hidden input변경
		$(".dogkinddiv input[type=hidden]").val(text);
	}
	
	function kindotherClick(){
		$("#kindothermodal").modal("show");
		selectkind();
	}
	
	function selectkind(name){
		alert(name);
		var div = $("#kindothermodal .kindDiv");
		
		$.ajax({
			url : "selectkind"
			, data : {
				kind : name
			}
			, success : function(result) {
				div.html(result);
			}
		});
		
	}
	
	
	function dropdownItemClick() {
		var text = $(this).text();
		$(this).parent().siblings("button").text(text);
	}

	function fileUploadChange() {
		var filename = this.files[0].name;
		var imgtag = $(this).siblings("img");
		imgtag.prop("src", "/plzdaengs/template/img/basic_pet_profile.jpg");
		
		if (!this.files[0].type.startsWith("image/")) {
			this.files[0].value = "";
			showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
			return;
		}
		
		var reader =new FileReader();
		reader.onload = function (e) {
			imgtag.prop("src", e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
		$(this).siblings("input[type=text]").val(filename);
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
		imgtag.prop("src", "/plzdaengs/template/img/basic_pet_profile.jpg");
		
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
</script>
<style>
.registeranimal .input-group-prepend>button {
	width: inherit;
}

.registeranimal .form-control-label {
	font-size: 1.0rem;
	margin-top: auto;
	margin-bottom: auto;
}

.registeranimal .form-group input {
	font-size: 1.0rem;
}

.registeranimal .file-hidden {
	display: none;
}

.registeranimal label[for=ex_file] {
	border: 1px solid #4680ff;
	border-radius: 2rem;
	height: calc(2.25rem + 2px);
	line-height: calc(2.25rem + 2px);
	text-align: center;
	color: #4680ff;
}

.registeranimal label[for=ex_file]:hover {
	background-color: #4680ff;
	color: white;
}

.registeranimal .fileuploadimg {
	margin-left: 20px;
	max-width: 100px;
	max-height: 100px;
	padding: 0px;
}

.registeranimal .registerfileupload label, .registeranimal .registerfileupload input
	{
	margin-top: auto;
	margin-bottom: auto;
}

.registeranimal h3 {
	font-size: 1.5rem;
}

<!--펫 css -->
.registeranimal input[type=checkbox] {
	-ms-transform: scale(1.5); /* IE */
	-moz-transform: scale(1.5); /* FF */
	-webkit-transform: scale(1.5); /* Safari and Chrome */
	-o-transform: scale(1.5); /* Opera */
}

#kindothermodal, #kindothermodal .form-control{
		font-size: 1rem;
}

.registeranimal .vaccinlistitem>.plus{
	/* background-image: url("/plzdaengs/template/img/plus.png");
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover; */
	font-size: 1.5rem;
	font-weight: 600;
	line-height: 1.2;
	text-align: center;
} 

.registeranimal .vaccinlistitem>.delete{
	/* background-image: url("/plzdaengs/template/img/plus.png");
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover; */
	font-size: 1.5rem;
	font-weight: 600;
	line-height: 1.2;
	text-align: center;
} 

.registeranimal .dogkind{
	padding-left: inherit;
	padding-right: inherit;
}

.registeranimal .vaccinlistitem .dropdown-toggle{
	padding-left: inherit;
	padding-right: inherit;
}
</style>

<!-- section 1 -->
<section class="registeranimal">
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
					<div class="kindDiv" style="width: 100%; height: 500px; overflow: auto; margin-top: 1%;" >
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-10 mb-5">
		<div class="card">
			<div class="card-header">
				<h3 class="h6 text-uppercase mb-0">반려동물 등록</h3>
			</div>
			<div class="card-body">
				<form class="form-horizontal" enctype="multipart/form-data" method="post">
					<input type="hidden" name="animalcode" value="417000">
					<div class="form-group row">
						<label class="col-md-3 form-control-label">반려동물 이름(*)</label>
						<div class="col-md-5">
							<input type="text" placeholder="반려동물 이름을 입력하세요"
								class="form-control" name="petname">
						</div>
						<label class="col-md-4 form-control-label" role="check"></label>
					</div>
					<div class="line"></div>
					<div class="form-group row dogkinddiv">
						<label class="col-md-3 form-control-label">반려동물 품종(*)</label>
						<div class="col-md-8">
							<input type="hidden" value="" name="breedcode">
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000072">말티즈</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000128">푸들</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000089">포메</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000101">시츄</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000018">비숑</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000113">요크셔</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000032">치와와</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000124">스피츠</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind" data="000114">믹스</button>
							<button type="button"
								class="btn btn-outline-primary col-md-2 dogkind kindother" data="">기타</button>
						</div>
					</div>
					<div class="line"></div>
					<div class="form-group row">
						<label class="col-md-3 form-control-label">반려동물 성별</label>
						<div class="col-md-5">
							<div
								class="custom-control custom-radio custom-control-inline">
								<input id="genderfemaleradio" type="radio"
									class="custom-control-input" name="petgender" value="F">
								<label for="genderfemaleradio" class="custom-control-label">여아</label>
							</div>
							<div
								class="custom-control custom-radio custom-control-inline">
								<input id="gendermaleradio" type="radio"
									class="custom-control-input" name="petgender" value="M">
								<label for="gendermaleradio" class="custom-control-label">남아</label>
							</div>
						</div>
					</div>
					<div class="line"></div>
					<div class="form-group row calendar">
						<label class="col-md-3 form-control-label">반려동물 생일</label>
						<div class="col-md-5">
							<input type='text' placeholder="반려동물 생일을 입력해주세요" name="birthdate"
							class="form-control datepicker-here" data-auto-close="true" data-position="right top" data-language='kr'/>
						</div>
					</div>
					<div class="line"></div>
					<div class="form-group row registerfileupload">
						<label class="col-md-3 form-control-label">프로필등록</label>
						<div class="col-md-9 input-group-prepend">
							<label for="ex_file" class="col-md-3">프로필선택</label> <input
								type="file" class="form-control file-hidden" id="ex_file" accept="image/*" name="imgdata"> 
								<input type="text" placeholder="파일을 등록해주세요" class="form-control col-md-5 " name="petimg" readonly > 
								<img alt="" class="col-md-2 fileuploadimg"
								src="/plzdaengs/template/img/basic_pet_profile.jpg" >
						</div>
					</div>
					<div class="line"></div>
					<div class="form-group row">
						<label class="col-md-3 form-control-label">대표 반려동물 설정</label>
						<div class="col-md-8">
							<div class="custom-control custom-checkbox">
								<input id="mainpetCheck" type="checkbox"
									class="custom-control-input" name="pettype" value="T"> 
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
							<label class="form-control-label">최근 6개월이내 예방접종을 입력해주세요.</label>
							<div class="vaccinlist">
								<div class="input-group-prepend vaccinlistitem">
									<input type="hidden" value="" name="vaccincode">
									<button type="button" data-toggle="dropdown"
										aria-haspopup="true" aria-expanded="false"
										class="btn btn-outline-primary dropdown-toggle col-md-3">예방접종 종류</button>
									<div class="dropdown-menu">
										<span class="dropdown-item" data="1">종합백신</span> 
										<span class="dropdown-item" data="2">코로나 장염 예방접종</span>
										<span class="dropdown-item" data="3">켄넬코프 예방접종</span>
									</div>
									<input type='text' class="form-control datepicker-here col-md-5" name="vaccindate"
									data-position="right top" data-language='kr' readonly/>
									<button type="button" class="btn btn-outline-primary plus">+</button>
								</div>
							</div>
						</div>
					</div>
					<div class="line"></div>
					<div class="form-group row button-group">
						<div class="col-md-9 ml-auto">
							<button type="reset" class="btn btn-primary" id="cancelBtn">취소</button>
							<button type="button" class="btn btn-primary" id="registerBtn">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>