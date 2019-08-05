<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
사용방법
1. 해당 파일을 자신의 section안에 include
<%@ include file="/template/alert_success.jsp"%>

2. 해당 알림창을 보여줘야할 일이 있을때는 showSuccessAlertModal 을 통해호출
 - title : 알림창의 제목
 - body : 알림창에 보여줄 내용
 - footer : 아직 개발 안함
 
3. 성공에 관련된 안내창이므로 해당 modal 이 닫히는 버튼이 눌렸을 시에 대한 로직 처리는 따로 진행한다.
 - $("#alertSuccess .modal-footer button").click();

 --%>
<script>
function showSuccessAlertModal(title, body, footer) {
	//아짓 footer는 고려안함..
	$("#alertSuccess .modal-title").html(title);
	$("#alertSuccess .modal-body>p").html(body);
	$("#alertSuccess").modal("show");
}
</script>
<style>
#alertSuccess .modal-dialog{
	margin-top: 15%;
}
</style>
<!-- 성공창 -->
<div class="modal fade" id="alertSuccess" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title"></h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<p></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" name="alertOK">확인</button>
			</div>
		</div>
	</div>
</div>