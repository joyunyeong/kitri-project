<%@page import="com.plzdaeng.group.model.GroupDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% GroupDto dto = (GroupDto)session.getAttribute("groupdetail");
System.out.println(dto);%>
<script>
$(function(){
	$('#gInfo').attr("class", "nav-link active");
	$('#gMeet').removeClass("active");
	$('#gMember').removeClass("active");
	
	
	var form = $('form');
	form.submit(function(){
		$.ajax({
			url:'/plzdaengs/groupfront',
			method:"POST",
			data:$(this).serialize(),
			success:function(result){
				//var result = request.getAttribute("result");
				//if(result == 1){
				alert("소모임 정보가 변경되었습니다.");
				//moveManagegroupinfo()
				$("section").html();
				//}
			}
			
			
		});
		return false;
	});


	var deleteBtn = $('#deletegroup');
	$(deleteBtn).click(function(){
		$("#deletegroupmodal").modal('show');
		
	});


});

function moveManagegroupinfo(){
	$.ajax({
		url : '/plzdaengs/groupfront',
		method : 'POST',
		data : {act : 'groupmanage',
			group_id : '<%=dto.getGroup_id()%>' },
		success : function(groupdetail) {
			$("section").html(groupdetail);
		}

	});
}
</script>
<!-- <div id="deletegroupmodal" tabindex="-1" role="dialog" 
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		class="modal fade text-left">
		<div role="document" class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					
					<button type="button" data-dismiss="modal" aria-label="Close"
						class="close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
				<h4 id="exampleModalLabel" class="modal-title">소모임을 탈퇴하시겠습니까?</h4>
						<input type="hidden" name="act" value="creategroup">
					
				</div>
				<div class="modal-footer">
					<input name="deletegroup" type="submit" value="탈퇴"
						class="btn btn-primary"> <input name="modalcancel"
						type="button" data-dismiss="modal" value="취소"
						class="btn btn-primary">
				</div>
			</div>
		</div>
	</div> -->
     
          
          <ul style="display: inline-block" class="nav nav-tabs nav-pills nav-justified grouplisttype">
		    <li class="nav-item" style="float:left;margin-right: 0.5rem;border-radius:0.5rem;" >
		      <a id=gInfo class="nav-link" style="border-radius:0.5rem;" href='#' onclick="moveManagegroupinfo()">소모임 정보변경</a>
		    </li>
		    <li class="nav-item" style="float:left;border-radius:0.5rem;">
		      <a id=gMeet class="nav-link" style="border-radius:0.5rem;" href='/plzdaengs/group/managemeeting.jsp'>소모임 일정관리</a>
		    </li>
		    <li class="nav-item" style="float:left;border-radius:0.5rem;">
		      <a id=gMember class="nav-link" style="border-radius:0.5rem;" href='/plzdaengs/group/managemember.jsp'>소모임원 관리</a>
		    </li>
		 </ul>
          
            <div class="row">
              <!-- Form Elements -->
              <div class="col-lg-12 mb-5">
                <div class="card">
                  <div class="card-header">
                    <h3 class="h6 text-uppercase mb-0">소모임 정보변경</h3>
                  </div>
                  <div class="card-body">
                    <form class="form-horizontal">
                      <div class="form-group row">
                      <input type="hidden" name="act" value="changedetail"/>
                      <input type="hidden" name="group_id" value="<%= dto.getGroup_id() %>"/>
                        <label class="col-md-3 form-control-label" >소모임 이름</label>
                        <div class="col-md-9">
                        <input name="group_name" type="text" class="form-control" readonly="readonly" style="width: 70%" value="<%= dto.getGroup_name() %>">
                        <div class="invalid-feedback ml-3">소모임 이름은 수정할 수 없습니다.</div>
                        </div>
                      </div>
                      <div class="line"></div>
                     <div class="form-group row">
		                        <label class="col-md-3 form-control-label">소모임 키워드</label>
		                        <div class="col-md-9">
		                          <label class="checkbox-inline">
		                            <input name="group_cate" type="text" class="form-control" readonly="readonly" style="width: 70%" value="<%=dto.getGroupCategory().getGroup_category_name() %>">
		                          </label>
		                        </div>
                   			   </div>
                      <div class="line"></div>
                       <div class="form-group row"> 
                                <label class="col-md-3 form-control-label">소모임 소개</label>
                                <div class="col-md-9">
                                <textarea name="group_description" class="form-control" style="width: 70%"><%=dto.getGroup_description() %></textarea>
                            </div>
                              </div>
                      <div class="line"></div>
                   <div class="form-group row">       
                                <label class="col-md-3 form-control-label">지역</label>
							<div class="col-md-9">
                                <select class="form-control col-md-3" style="float:left" name="group_sido">
                             		<option><%=dto.getAddress_sido() %></option>
                             		<option>서울특별시</option>
									<option>경기도</option>
									<option>강원도</option>
									<option>광주광역시</option>
									<option>대구광역시</option>
									<option>대전광역시</option>
									<option>부산광역시</option>
									<option>인천광역시</option>
									<option>울산광역시</option>
									<option>세종특별자치시</option>
									<option>전라북도</option>
									<option>전라남도</option>
									<option>충청북도</option>
									<option>충청남도</option>
									<option>경상북도</option>
									<option>경상남도</option>
									<option>제주특별자치도</option>
                                </select>
                             
                                <label style="position: absolute;"><input name="groupdontselect" type="checkbox">지역무관</label>
                               </div>
                              </div>
                      <div class="line"></div>
                       <div class="form-group row">
                               <label class="col-md-3 form-control-label">소모임 대표이미지</label>
                               <div class="col-md-9">
                               <input name="group_img" type="file" value="<%=dto.getGroup_img() %>">
                               </div>
                               </div>
                      <div class="line"></div>
                          <button style="background-color: red;float: left;" name="deletegroup" type="button" class="btn btn-secondary">소모임 해체</button>
                          <button style="float: right;" type="submit" class="btn btn-primary">취소</button>
                          <button style="float: right;" type="submit" class="btn btn-primary">변경</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>