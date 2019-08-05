<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="//code.jquery.com/jquery.min.js"></script>
<script>
$(function() {
  $('#button1').click( function() {
    if( $(this).html() == '접기' ) {
      $(this).html('펼치기');
    }
    else {
      $(this).html('접기');
    }
  });
});
</script>
 
<button id='button1'>접기</button>

<!-- 1. 파일 경로 가져오기
파일업로드<input type="file" id="fileup1" onchange="document.getElementById('filetext1').value=this.value;" />
실제 값<input type="text" id="filetext1" />
-->

<!-- 2. form에 있는값 serialize하기 > 근데 formdata가 나음
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $("#btn").text($("form").serialize());
  });
});
</script>
</head>
<body>

<form action="">
  First name: <input type="text" name="FirstName" value="Mickey"><br>
  Last name: <input type="text" name="LastName" value="Mouse"><br>
</form>

<button>Serialize form values</button>

<div id = "btn"></div>

</body>
</html>
-->