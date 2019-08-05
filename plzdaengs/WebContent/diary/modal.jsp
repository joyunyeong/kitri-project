<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	/*
	.modal-content {
		background-color: fuchsia;
}
	*/
	.body {
	background-image: url('diary/img/footprint.png');
	}
	
	.button {
		color : white;
		font-size : large;
	    background-color: lightcoral;
	    border-color: #ccc;
	}
	.modal-dialog {
	    max-width: 800px;
	    margin: 1.75rem auto;
	}
	.form-group {
		font-size: medium;
	}
	
</style>
<script type="text/javascript">
	$("#realmodal").modal('show').css({
	    'margin-top': function () { //vertical centering
	        return -($(this).height() / 2);
	    },
	    'margin-left': function () { //Horizontal centering
	        return -($(this).width() / 2);
	    }
	});


	$(document).ready(function() { // 아예 시작할 때
        $("#submit").on("click", function(){
			
			sendDiaryInfo(); // servlet으로 form의 정보 전송
			
        	var input1 = document.getElementById('title').value;
			var input2 = document.getElementById('description').value;
			
        	var schedule = cell.siblings(".cal-schedule")[0];
        	var element = $(document.createElement("div")); // div element 만들꺼얌
        	
        	element.attr("class", "schedule");
        	element.text(input1);
        	element.click(scheduleClick); // 생성된 일정 클릭하면 썼던 내용 그대로 있도록
        	
        	$(schedule).append(element);
        	alert('등록되었습니다 : ' + input1);
        	
        	$("#enroll #title").val("");
        	$("#description").val("");
			$('#enroll').modal("hide");
			
			/* 망쳐버린 나의 ajax......
			
			// [1] servlet 이동 : 해결
        	$("#result").text($("#form").serialize());
            //var str = $("#filetext").serialize(); 
            var path = document.getElementById('filetext').value;
            //alert('string : ' + path);
        	//console.log(aaa);
        	
            var str = $("#form").serialize(); // file은 직렬화 안됨.. > form으로 ㄱㄱ
            //alert('str : ' + );
            
			
			//var form = $("#form")[0];
			//var formData = new FormData(form);
			
            $.ajax({
              type:"POST",
              url:"/plzdaengs/diary",
              contentType: "application/x-www-form-urlencoded; charset=utf-8",
              data: str,
              datatype:"json",
              success: function(data) {
                alert("ajax 성공 : " + data.result);			
              },
              error: function(e) {
                alert("ajax 실패 : 에러가 발생하였습니다.");
              }			
            });
			*/
			return false;
        });
	});
	
	function sendDiaryInfo() {
		var form = $("#form")[0];
		var formData = new FormData(form);
		
		$.ajax({
			url : "/plzdaengs/enrolldiary",
			method : "post",
			processData : false,
			contentType : false,
			data : formData,
			success : function (result) {
				if(formData != null) {
					console.log('formData 넘어가염 '+formData);
				}
			}
		});
		
		return false;
	}
	
	function scheduleClick() {
		$('#enroll').modal("show");
		$("#enroll #title").val($(this).text());
		$("#enroll #description").val($(this).text());
		$("#enroll #imgdata").val($(this).text());
		console.log('여기까진 와');
		// 그리고 버튼도 submit이 아니라 modify로 바꿔야함
		//if($(this).html() == 'submit') {
		//	$("#enroll #submit").html('modify');
		//}
	}
	
	function register() {
		if(document.getElementById("title").value == "") {
			alert("다이어리 제목을 입력하세요.");
			$('#enroll').modal("show");
			return;
		} else {
			
		}
	}
	
</script>
<script>
$(function() {
	// 모달에서 사진 클릭하고 바뀔 때
	$("#imgdata").change(fileUploadChange);
});


function fileUploadChange() {
	alert('들어왔담');
	var filename = this.files[0].name;
	var imgtag = $(this).siblings("img");
	imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");
	
	
	if (!this.files[0].type.startsWith("image/")) {
		this.files[0].value = "";
		showAlertModal("이미지 업로드 경고", "올릴수 없는 확장자입니다.");
		return;
	}

	//imgtag.prop("src", "/plzdaengs/template/img/basic_user_profile.png");
	var reader =new FileReader();
	reader.onload = function (e) {
		imgtag.prop("src", e.target.result);
	}
	reader.readAsDataURL(this.files[0]);
	//$(this).siblings("input[type=text]").val(filename);

}
</script>
</head>
<body>
<!-- 
	Calendar 날짜 클릭했을시에 모달창 FE 
	original : <form method = "get" action = "/plzdaengs/diary/mparam.jsp">
-->

<div id="enroll" class="modal fade">
	<div id = "realmodal" class="modal-dialog" width = "800">
		<div class="modal-content">
			<form id = "form" enctype="multipart/form-data">
				<input type="hidden" name="date" value="">
				<div class="modal-header">다이어리 추가</div>
				<div class="modal-body">
					<!--div id="ipAlertTitle" class="alert alert-danger" role="alert">다이어리를 입력해주세요. </div-->
					<div id="ipTitle-group" class="form-group" align="left">
						<label for="ipTitle" >Title : </label>
						<input type="text" class="form-control" id="title" placeholder="Title" name="title">
					</div>
					<div id="ipDesc-group" class="form-group" align="left">
						<label for="ipDesc">Description : </label>
						<textarea class="form-control" rows="3" id="description" placeholder="Description" name="description"></textarea><br>
					</div>		
					<div>			
						<input type="file" id = "imgdata" name="imgdata" accept=".jpg,.jpeg,.png,.gif,.bmp">
						<img alt="" class="realimage" src="/plzdaengs/template/img/profile.jpg" width="200px" height="200px">
					</div>	
				</div>
				<div class="modal-footer">
					<button type="button" class="button" data-dismiss="modal">Close</button>
					<button type=button class="button" id="submit">Submit</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>