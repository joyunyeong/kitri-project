<%@page import="com.plzdaeng.group.model.GroupDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%-- <%!List<GroupDto> list;%> --%>

<%
	List<GroupDto> list = (List) request.getAttribute("grouplist");

	for (GroupDto dto : list) {
		System.out.println(dto.getGroup_img() + "//" + dto.getGroup_id()+ "//" + dto.getGroup_name());
		System.out.println(dto.getGroup_img());

%>

<div class="card" id="<%=dto.getGroup_id()%>">

	<div class="card-header"<%--  id="<%=dto.getGroup_name()%>" --%>>
		<h2 class="h6 text-uppercase mb-0" style="font-size: large;"><%=dto.getGroup_name() %></h2>
	</div>
	<div class="card-body" style="padding: 10;">
		<img style="display: inline; float: left;"
			src="<%=dto.getGroup_img() %>" width="80" height="80">
		<p class="mb-5 text-gray" style="display: inline; float: left"><%=dto.getGroup_description() %></p>
		<div style="display: inline; float: right">
			<div>
				<label>지역 : </label><%=dto.getAddress_sido() %>
			</div>
			<div>
				<label>인원 : </label><%=dto.getGroupMembers().size() %>
			</div>
			<div>
				<label>키워드 : </label><%=dto.getGroupCategory().getGroup_category_name() %>
			</div>
		</div>
	</div>
</div>
<%
	}
%>
<%@ include file="/template/page.jsp"%>
