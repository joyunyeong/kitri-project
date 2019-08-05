<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="result" value="${requestScope.selectkindresult}"></c:set>
<c:forEach items="${result}" var="breed">
	<button class="btn btn-outline-primary col-md-3 dogkind selectkind" data="${breed.breed_code}" type="button">${breed.breed_name}</button>
</c:forEach>
<script>
var selectkinds = $(".selectkind");
selectkinds.click(function() {
	var kindother = $(".kindother");
	var text = $(this).text();
	
	kindother.text(text);
	kindother.attr("data", $(this).attr("data"));
	kindother.siblings("input[type=hidden]").val($(this).attr("data"));
	$("#kindothermodal").modal("hide");
});
</script>