<%@page import="com.plzdaeng.group.model.GroupMember"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
<% List<GroupMember> list = (List)request.getAttribute("memberlist");
int x = 1;
  %>
  <% for(GroupMember member : list){
	  %>
 						
						 <tr>
                          <th scope="row"><%=x %>
                          <input type="hidden" name="member_status" value="<%=member.getMember_status()%>"></th>
                          
                          <td>
                          <%if(member.getMember_status().equals("M")){
                        	  %>일반<%
                          }else if(member.getMember_status().equals("L")){
                        	  %>모임장<%
                          }else if(member.getMember_status().equals("A")){
                        	  %>가입요청<%
                          }%>
                          </td>
                          <td class="member_id"><%= member.getUser_id() %></td>
	                      <td><%= member.getNickName() %></td>
                          
                          <td><%=member.getGroup_joindate() %></td>
                          <td class="button-group">
                          <%if(member.getMember_status().equals("M")){
                        	  %><button class="btn btn-danger" type="button">추방</button><%
                          }else if(member.getMember_status().equals("L")){
                        	  %><button class="btn btn-success" type="button">모임장위임 </button><%
                          }else if(member.getMember_status().equals("A")){
                        	  %><button class="btn btn-info" type="button">가입승인 </button><%
                          }%>
                         </td>
                        </tr>
                        
<%x++; }%>