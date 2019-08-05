<%@page import="org.w3c.dom.NodeList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="result" value="${requestScope.zipsearchwebresult}"></c:set>
<x:parse var="doc" xml="${result}"></x:parse>
<x:set scope="request" var="totalCount" select="$doc/NewAddressListResponse/cmmMsgHeader/totalCount" />
<x:set scope="request" var="totalPage" select="$doc/NewAddressListResponse/cmmMsgHeader/totalPage" />
<x:set scope="request" var="currentPage" select="$doc/NewAddressListResponse/cmmMsgHeader/currentPage" />
<x:set scope="request" var="countPerPage" select="$doc/NewAddressListResponse/cmmMsgHeader/countPerPage" />
<c:set var="totalCount" value="${requestScope.totalCount[0].textContent}"></c:set>
<c:set var="totalPage" value="${requestScope.totalPage[0].textContent}"></c:set>
<c:set var="currentPage" value="${requestScope.currentPage[0].textContent}"></c:set>
<c:set var="countPerPage" value="${requestScope.countPerPage[0].textContent}"></c:set>
<c:set var="pageGroupNumber" value="7"></c:set>
<c:set var="url" value="/plzdaengs/zipsearchweb"></c:set>
<script>
$(function(){
	$(".zipcode").click(zipcodeClick);
	$(".lnmAdres").click(lnmAdresClick);
	$(".rnAdres").click(rnAdresClick);
	$(".pagination .pagenumber").click(pagenumberClick);
	$(".pagination .prev").click(prevClick);
	$(".pagination .next").click(nextClick);
});

function prevClick(){
	if(${currentPage} != 1){
		var doro = document.getElementById("doro").value;
		zipSearchAjax(doro, ${currentPage-1});
	}
	return false;
}
function nextClick(){
	if(${currentPage} != ${totalPage}){
		var doro = document.getElementById("doro").value;
		zipSearchAjax(doro, ${currentPage+1});
	}
	return false;
}
function pagenumberClick() {
	var doro = document.getElementById("doro").value;
	zipSearchAjax(doro, $(this).find("a").text());
	return false;
}

function rnAdresClick() {
	var tr = $(this).parent().siblings("tr")[0];
	selectZip($(tr).find(".zipcode").text(), $(this).text());
}
function lnmAdresClick() {
	selectZip($(this).siblings(".zipcode").text(), $(this).text());
}
function zipcodeClick() {
	selectZip($(this).text(), $(this).siblings(".lnmAdres").text());
}
</script>
<div style="overflow: auto; height: 500px; margin-bottom: 20px">
<table class="table-bordered text-center">
	<thead>
		<tr>
			<th style="width: 150px;">우편번호</th>
			<th style="width: 600px;">주소</th>
		</tr>
	</thead>
	<tbody id="zip_codeList">
	<x:forEach select="$doc/NewAddressListResponse/newAddressListAreaCdSearchAll">
		<tr>
			<td rowspan="2" class="zipcode"><x:out select="zipNo"/></td>
			<td class="lnmAdres"><x:out select="lnmAdres"/></td>
		</tr>
		<tr>
			<td class="rnAdres"><x:out select="rnAdres"/></td>
		</tr>
	</x:forEach>
	</tbody>
</table>
</div>
<ul class="pagination">
  	<li class="page-item">
	  	<a class="page-link prev" href="#">이전</a> 
	</li>
	<c:if test="${(currentPage-pageGroupNumber/2) > 1}">
		<li class="page-item pagenumber"><a class="page-link" href="#">1</a></li>
		<li class="page-item pagenumber">...</li>
	</c:if>
	<c:forEach begin="1" end="${totalPage}" step="1" var="i">
		<c:choose>
			<c:when test="${(i >= currentPage-pageGroupNumber/2 && i <= currentPage+pageGroupNumber/2)
								|| (i <= pageGroupNumber && currentPage <= pageGroupNumber/2+1)
								|| (i >= totalPage-pageGroupNumber && currentPage >= totalPage-pageGroupNumber/2)}">
			<li class="page-item pagenumber <c:if test="${i==currentPage}">active</c:if>" >
			<a class="page-link" href="#">${i}</a></li>
			</c:when>
			<c:otherwise>
			<li class="page-item pagenumber" style="display: none;"><a class="page-link" href="#">1</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${(currentPage+pageGroupNumber/2) < totalPage}">
		<li class="page-item pagenumber">...</li>
		<li class="page-item pagenumber"><a class="page-link" href="#">${totalPage}</a></li>
	</c:if>
	<li class="page-item next"><a class="page-link" href="#">다음</a></li>  	
</ul>
