<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
사용방법
1. 해당 파일을 자신의 section안에 include
<%@ include file="/template/alert_danger.jsp"%>

2. 해당 알림창을 보여줘야할 일이 있을때는 showAlertModal(title, body); 을 통해호출
 - title : 알림창의 제목
 - body : 알림창에 보여줄 내용
 
3. 실패한 모달창이므로 창이 닫힐 시에 대한 이벤트 처리는 해당 파일에서 미리 해두었음.
 - 실패한 경우 페이지에 대한 변화가 안일어난다고 판단

 --%>
<script>
$(function() {
	$("#alert button[name=alertOK]").click(function () {
		$("#alert").modal("hide");
	});
});
function showAlertModal(title, body, footer) {
	//아짓 footer는 고려안함..
	$("#alert .modal-title").html(title);
	$("#alert .modal-body>p").html(body);
	$("#alert").modal("show");
}
</script>
<style>
#alert .modal-dialog{
	margin-top: 15%;
}
</style>
<!-- 경고창 -->
<div class="modal fade" id="alert" role="dialog">
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
				<button type="button" class="btn btn-danger" name="alertOK">확인</button>
			</div>
		</div>
	</div>
</div>