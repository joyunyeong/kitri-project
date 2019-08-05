<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set value="${sessionScope.userInfo}" var="user"></c:set>
<c:if test="${empty user}">
<script>document.location.href = "/plzdangs/menu?act=home";</script>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp"%>
<!-- datepicker -->
<script src="/plzdaengs/template/airdatepicker/js/datepicker.js"></script>
<script src="/plzdaengs/template/airdatepicker/js/i18n/datepicker.kr.js"></script>
<link href="/plzdaengs/template/airdatepicker/css/datepicker.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(function () {
	//애완동물 표시하는 로직
	//추가버튼 이벤트
	$(".plusimg").click(registerAnimal);
	
	//펫상세페이지로 이동
	$("div[class=card]").click(cardClick);
});

function cardClick(){
	var petname = $(this).find(".petname").text();
	var contents = $("#contents");
	//alert(petname);
	$.ajax({
		url : "/plzdaengs/petinfo"
		, method : "get"
		, data : {
			petname : petname
		}
		, success : function (result) {
			//alert(result);
			contents.html(result);
		}
	});
}

function registerAnimal() {
	$.ajax({
		url : "/plzdaengs/animal"
		, data: {
			act : "animalregister"
		}
		, success: function(result){
			$("#contents").html(result);
		}
	});
}
</script>
<c:set var="petlist" value="${sessionScope.petList}"></c:set>
<c:set var="user" value="${sessionScope.userInfo}"></c:set>
<c:choose>
	<c:when test="${empty user}">
	<script>document.location.href="/plzdaengs/index.jsp"</script>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>

<style type="text/css">
.animals{
	padding-left: 10%;
	padding-right: 10%;
}
.animals .plusimg{
	background-image: url("/plzdaengs/template/img/plus.png");
	background-position: center;
	background-repeat: no-repeat;
	background-size: inherit;
	width: 100%;
	height: 100%;
	opacity: 0.5;
}
.animals .plusimg:hover{
	opacity: 1;
	border-color: #c6d8ff;
	box-shadow: 0 0 0 0.2rem rgba(70, 128, 255, 0.25);
}
.animals .card{
	min-height: 15rem;
	max-height: 20rem;	
	cursor: pointer;
}
.animals .card:hover{
	opacity: 1;
	border-color: #c6d8ff;
	box-shadow: 0 0 0 0.2rem rgba(70, 128, 255, 0.25);
}

.animals .card .card-header{
	text-align: center;
}

.animals .card .petimg>img{
	width: 8rem;
	height: 8rem;
}

.animals .petimg{
	width: 50%;
	float: left;
}

.animals .petinfo{
	width: 50%;
	float: left;
}

.animals .petinfo>.row{
	margin-bottom: 5%;
	font-size: 1.3rem;
}
</style>
</head>
<body>
	<!-- 경고창 모달 -->
	<%@ include file="/template/alert_danger.jsp"%>
	<%@ include file="/template/alert_success.jsp"%>
	<!-- navbar-->
	<%@ include file="/template/header.jsp"%>

	<div class="d-flex align-items-stretch" id="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp"%>
		<!-- 사이드 sidebar -->
		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">
				<!-- section 1 -->
				<section class="py-5 animals">
					<div class="row">
						<c:forEach var="pet" items="${petlist}">
						<div class="col-lg-6 mb-4">
							<div class="card">
								<div class="card-header ">
									<h3><span class="petname">${pet.pet_name}</span><c:if test="${pet.pet_type == 'T'}">(대표펫)</c:if>
									</h3>
								</div>
								<div class="card-body">
									<div class="petimg">
										<img src="${pet.pet_img}"/>
									</div>
									<div class="petinfo">
										<div class="row">품종 : <span class="petbreed">${pet.breedDto.breed_name}</span></div>
										<div class="row">성별 : <span class="petgender">
											<c:choose>
												<c:when test="${pet.pet_gender == 'F'}">여아</c:when>
												<c:when test="${pet.pet_gender == 'M'}">남아</c:when>
											</c:choose></span></div>
										<div class="row">생일 : <span class="petbirthdate">${pet.birth_date}</span></div>
									</div>									
								</div>
							</div>
						</div>
						</c:forEach>
						<div class="col-lg-6 mb-4">
							<div class="card plusimg">
							</div>
						</div>
					</div>
				</section>
			</div>
			<%@ include file="/template/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/template/default_js_link.jsp"%>
	<script src="/plzdaengs/template/airdatepicker/js/datepicker.js"></script>
	<script src="/plzdaengs/template/airdatepicker/js/i18n/datepicker.kr.js"></script>
</body>
</html>