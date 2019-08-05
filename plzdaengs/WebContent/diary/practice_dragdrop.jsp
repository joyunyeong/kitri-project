<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<title>drag&drop 예제</title>

<style type="text/css">
div {width:320px; height:320px; text-align:center; border:1px solid #000; padding:5px; margin:20px;}
img {width:75px; height:60px; padding:5px;}
h2 {font-size:.8em; color:#000; font-weight:bold;}
.fl {float:left;}
</style>

<script type="text/javascript">
	// 드래그 시작시 호출 할 함수
	function drag(target, food) {
		console.log('여기까지 넘어왔슈');
		food.dataTransfer.setData('Text', target.id);
	};
	
	// 드롭시 호출 할 함수
	function drop(target, food) {		
		var id = food.dataTransfer.getData('Text');
		target.appendChild(document.getElementById(id));
		food.preventDefault();	
	};
</script>

</head>

<body>
<div ondrop="drop(this, event);" ondragenter="return false;" ondragover="return false;">
	<h2>아래 음식이미지를 종류별로 드레그 해서 넣어보세요.</h2>
	<img src="img/krfood1.jpg" alt="한국음식1" draggable="true" ondragstart="drag(this, event)" />
	<img src="img/krfood2.jpg" alt="한국음식2" draggable="true" id="kor2" ondragstart="drag(this, event)" />
	<img src="img/krfood3.jpg" alt="한국음식3" draggable="true" id="kor2" ondragstart="drag(this, event)" />
	<img src="img/ufood1.jpg" alt="서양음식1" draggable="true" id="u1" ondragstart="drag(this, event)" />
	<img src="img/ufood2.jpg" alt="서양음식2" draggable="true" id="u2" ondragstart="drag(this, event)" />
	<img src="img/ufood3.jpg" alt="서양음식3" draggable="true" id="u3" ondragstart="drag(this, event)" />
	<img src="img/chfood1.jpg" alt="중국음식1" draggable="true" id="ch1" ondragstart="drag(this, event)" />
	<img src="img/chfood2.jpg" alt="중국음식2" draggable="true" id="ch2" ondragstart="drag(this, event)" />
	<img src="img/chfood3.jpg" alt="중국음식3" draggable="true" id="ch3" ondragstart="drag(this, event)" />
</div>
<div class="fl" ondrop="drop(this, event);" ondragenter="return false;" ondragover="return false;">
	<h2>한국음식</h2>
</div>
<div class="fl" ondrop="drop(this, event);" ondragenter="return false;" ondragover="return false;">
	<h2>중국음식</h2>
</div>
<div class="fl" ondrop="drop(this, event);" ondragenter="return false;" ondragover="return false;">
	<h2>서양음식</h2>
</div>
</body>
</html>
