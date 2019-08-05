<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String zipSearchWebRoot = request.getContextPath();
%>
<script type="text/javascript">
$(document).ready(function() {
	 	
	document.getElementById("searchBtn").addEventListener("click", zipSearch, "false");	
	zipListView = document.getElementById("zip_codeList");
	
});

var zipListView;

function zipSearchAjax(doro, currentPage) {
	$.ajax({
		url: "<%=zipSearchWebRoot%>/zipsearchweb"
		, type:"get"
		//, dataType:"xml"
		, data : "doro="+doro + "&currentPage=" + currentPage
		, success: function(result){
			$("#addressdiv").html(result);
			/* var totalCount = $(xml).find("totalCount");
		 	var countPerPage = $(xml).find("countPerPage");
		 	var totalPage = $(xml).find("totalPage");
		 	var currentPage = $(xml).find("currentPage");
		 	var addressList = $(xml).find("newAddressListAreaCdSearchAll");
		 	
		 	var length = addressList.length;
		 	for(var i=0 ; i < length ; i++){
		 		var zipNo = $(addressList[i]).find("zipNo") .text();
		 		var lnmAdres = $(addressList[i]).find("lnmAdres") .text();
		 		var rnAdres = $(addressList[i]).find("rnAdres") .text();
		 		
		 		var tr = $("<tr>");
				var tdzipNo = $("<td>").html(zipNo).attr("rowspan", "2");
				var tdlnmAdres = $("<td>").html(lnmAdres);
				var tdrnAdres = $("<td>").html(rnAdres);
				var tr2 = $("<tr>");
				
				tdzipNo.click(function() {
					selectZip(tdzipNo.text(), tdlnmAdres.text());
				});
				tdlnmAdres.click(function() {
					selectZip(tdzipNo.text(), tdlnmAdres.text());
				});
				tdrnAdres.click(function() {
					selectZip(tdzipNo.text(), tdrnAdres.text());
				});
				
				tr.append(tdzipNo).append(tdlnmAdres);
				tr2.append(tdrnAdres);
				$("#zip_codeList").append(tr).append(tr2);
		 	} */
		 	
		 	//페이지 처리..
		}
	});
}

function zipSearch() {
	
	var doro = document.getElementById("doro").value;
	if(doro.length == 0){
		alert("검색할 도로명을 입력하세요.");
		return;
	} else{
		$("#zip_codeList").empty();
		zipSearchAjax(doro, 1);
	}
}

var zipcodetag;
var addresstag;

function zipsearchWebSetting(zipcodeselector, addressselector) {
	zipcodetag = zipcodeselector;
	addresstag = addressselector;
}

function selectZip(zipcode, address) {
	//console.log(zipcodetag);
	//console.log(addresstag);
	
	zipcodetag.text(zipcode);
	//hidden 부분도 변경
	zipcodetag.siblings("input[type=hidden]").val(zipcode);
	addresstag.val(address);
	$('#zipModal').modal("hide");
}
</script>
<style>
#zipModal, #zipModal .form-control{
	font-size: 1rem;
}
</style>
<div id="zipModal" class="modal fade" role="dialog">
	<h5 class="modal-title" id="myModalLabel">우편번호검색</h5>
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header text-center">
				<label style="margin-left: auto; margin-bottom:auto; margin-top:auto; font-size: 1.5rem">도로명 주소검색</label>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">X</span>
				</button>
			</div>
			<div class="modal-body text-center">
				<div class="input-group" align="left">
					<input type="text" class="form-control" id="doro" name="doro"
						placeholder="검색 할 도로명 입력(예: 구로디지털로, 여수울로)"> <span
						class="input-group-btn"> <input type="button"
						class="btn btn-warning" value="검색" id="searchBtn">
					</span>
				</div>
				<div
					style="width: 100%; margin-top: 1%;" id="addressdiv">
					<table class="table-bordered text-center">
						<thead>
							<tr>
								<th style="width: 150px;">우편번호</th>
								<th style="width: 600px;">주소</th>
							</tr>
						</thead>
						<tbody id="zip_codeList">
							<tr>
								<td rowspan="2">우편번호</td>
								<td>도로명</td>
							</tr>
							<tr>
								<td>구주소</td>
							</tr>
						</tbody>
					</table>
					<ul class="pagination" style="padding-left: 30%">
						  <li class="page-item"><a class="page-link" href="#">Previous</a></li>
						  <li class="page-item"><a class="page-link" href="#">1</a></li>
						  <li class="page-item active"><a class="page-link" href="#">2</a></li>
						  <li class="page-item"><a class="page-link" href="#">3</a></li>
						  <li class="page-item"><a class="page-link" href="#">Next</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>