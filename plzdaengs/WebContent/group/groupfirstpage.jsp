
<%@page import="com.plzdaeng.group.model.GroupDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp"%>
<%
	String level = (String) request.getAttribute("authority");
	int groupId = (int)request.getSession().getAttribute("group_id");
	GroupDto group = (GroupDto)request.getAttribute("group");
	//int result = (int)request.getAttribute("joinrequestresult");
	//System.out.println("에러 잡는중 level: " + level);
	//System.out.println("에러 잡는중 groupId: " + groupId);
	//System.out.println("에러 잡는중 group: " + group);

	String authority = "";
	if (level.equals("L")) {
		authority += "소모임관리";
	} else if (level.equals("M")) {
		authority += "소모임탈퇴";
	} else if (level.equals("A")) {
		authority += "가입요청중";
	} else if (level.equals("X")) {
		authority += "소모임가입";
	}
	
	String group_name = group.getGroup_name();
	String group_description = group.getGroup_description();
%>
<script>

$(function() {
		//groupfirstpageLoding();
		var authority = $("#groupoptbtn").text();
		var Obtn = $("#groupoptbtn");
		
		
		$(Obtn).click(function() {
			var groupId = <%=String.valueOf(groupId)%>;
			if ('<%=level%>' == 'A') {
				alert("가입 승인대기중입니다.");
			} else if ('<%=level%>' == 'X') {
				$("#joingroupmodal").modal('show');
				var join = $('input[name=joingroup]');
				$(join).click(groupJoinProcess);
			}else if('<%=level%>' == 'M'){
				alert("탈퇴되었습다.");
				$("#leavegroupmodal").modal('show');
			} else {
				$.ajax({
					url : '/plzdaengs/groupfront',
					method : 'POST',
					data : {act : 'groupmanage',
						group_id : groupId },
					success : function(groupdetail) {
						$("#joingroupmodal").modal("hide");
						$("section").html(groupdetail);
					}

				});
			}
			return false;
		});
});

function groupJoinProcess(){
	$.ajax({
		url : '/plzdaengs/groupfront',
		method : 'GET',
		data : {group_id : '<%=groupId%>',
				act : 'joingroup'},
		success : function(result) {
			//var resultB = request.getAttribute('joinrequestresult');
			//alert(result);
/* 			if(resultB == 1){
		$("section").html();
			}else{
				alert("이미 소모임 가입요청을 하였습니다.");
				$("#joingroupmodal").modal('hide');
				$("section").html();
			} 
	*/
				alert("소모임 가입을 신청하였습니다.");
				$("#joingroupmodal").modal('hide');
				var join = $('input[name=joingroup]');
			
			
		}
	});	
	return false;
}

function groupfirstpageLoding(){
	$.ajax({
		url : '/plzdaengs/groupfront',
		method : 'GET',
		data : {group_id : '<%=groupId%>',
				act : 'firstPageLoding'},
		success : function(result) {
			
		}
	});	
	return false;
	
	
}
</script>
</head>
<body>
<!-- 그룹가입 -->
	<div id="joingroupmodal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		class="modal fade text-left">
		<div role="document" class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 id="exampleModalLabel" class="modal-title">소모임을 가입하시겠습니까?</h4>
					<button type="button" data-dismiss="modal" aria-label="Close"
						class="close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					
				
						<!-- <input type="hidden" name="act" value="joingroup"> -->
						<div class="form-group">
							<h1><%=group_name%></h1> 
						</div>
						<div class="line"></div>

						<div class="form-group">
							<label>소모임 소개</label>
						</div>
				</div>
				<div class="modal-footer">
					<input name="joingroup" type="button" value="가입신청"
						class="btn btn-primary"> 
					<input name="modalcancel" type="button" data-dismiss="modal" value="취소"
						class="btn btn-primary">
				</div>
			</div>
		</div>
	</div>
	<!-- 그룹탈퇴 -->
<!-- 	<div id="leavegroupmodal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		class="modal fade text-left">
		<div role="document" class="modal-dialog">
			<div class="modal-content">
				<div class="modal-footer">
					<input name="leavegroup" type="submit" value="소모임 탈퇴"
						class="btn btn-primary"> <input name="modalcancel"
						type="button" data-dismiss="modal" value="취소"
						class="btn btn-primary">
				</div>
			</div>
		</div>
	</div> -->

	<!-- navbar-->
	<%@ include file="/template/header.jsp"%>

	<div class="d-flex align-items-stretch" id="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp"%>
		<!-- 사이드 sidebar -->

		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">

				<section class="py-5">
					<div class="row button-group" id="title">
						<div class="col-lg-10">
							<h1 style="display: inline;"><%=group_name%></h1>
							<%if(authority.equals("M")){%>
							<Button class="btn btn-danger" style="margin-bottom: 1em;float:right;" id="groupoptbtn"><%	
							}else{%>
							<Button class="btn btn-info" style="margin-bottom: 1em;float:right;" id="groupoptbtn"><%
							} %><%=authority%></Button>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-10">
							<div class="card">
								<div class="card-header">
									<h5 class="text-uppercase mb-0">소모임 소개글</h5>
								</div>
								<div class="card-body">
								<h5><%=group_description%></h5>
									</div>
							</div>
							<div class="card">
								<div class="card-header">
									<h5 class="text-uppercase mb-0">일반 게시판</h5>
								</div>
								<div class="card-body">
									<table class="table card-text">
										<thead>
											<tr>
												<th>#</th>
												<th>제목</th>
												<th>작성자</th>
												<th>작성일자</th>
												<th>조회수</th>
												<th>추천수</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<th scope="row">1</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
											<tr>
												<th scope="row">2</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
											<tr>
												<th scope="row">3</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
												<tr>
												<th scope="row">3</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
												<tr>
												<th scope="row">3</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
												<tr>
												<th scope="row">3</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
												<tr>
												<th scope="row">3</th>
												<td>우리 댕댕이가 아파요ㅜㅜ</td>
												<td>댕맘</td>
												<td>2019.05.19</td>
												<td>40</td>
												<td>20</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div style="text-align: center;">더보기</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<%@ include file="/chat/chat.jsp"%>
			<%@ include file="/template/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/template/default_js_link.jsp"%>
</body>
</html>