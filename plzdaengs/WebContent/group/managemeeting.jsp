<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/template/default_link.jsp"%>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	$('#gMeet').attr("class", "nav-link active");
	$('#gInfo').removeClass("active");
	$('#gMember').removeClass("active");
});
function moveManagegroupinfo(){
	$.ajax({
		url : '/plzdaengs/groupfront',
		method : 'POST',
		data : {act : 'groupmanage'},
		success : function(groupdetail) {
			$("section").html(groupdetail);
		}

	});
}


</script>
<style type="text/css">
.cal_top {
	text-align: center;
	font-size: 30px;
}

.cal {
	text-align: center;
}

table.calendar {
	border: 1px solid black;
	display: inline-table;
	text-align: left;
}

table.calendar td {
	vertical-align: top;
	border: 1px solid skyblue;
	width: 100px;
}
</style>
</head>
<body>
	<!-- navbar-->
	<header class="header">
		<nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow">
			<a href="#" class="sidebar-toggler text-gray-500 mr-4 mr-lg-5 lead"><i
				class="fas fa-align-left"></i></a><a href="index.html"
				class="navbar-brand font-weight-bold text-uppercase"></a>
			<ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
				<li class="nav-item">
					<form id="searchForm" class="ml-auto d-none d-lg-block">
						<div class="form-group position-relative mb-0">
							<button type="submit" style="top: -3px; left: 0;"
								class="position-absolute bg-white border-0 p-0">
								<i class="o-search-magnify-1 text-gray text-lg"></i>
							</button>
							<input type="search" placeholder="Search ..."
								class="form-control form-control-sm border-0 no-shadow pl-4">
						</div>
					</form>
				</li>
				<li class="nav-item dropdown mr-3"><a id="notifications"
					href="http://example.com" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"
					class="nav-link dropdown-toggle text-gray-400 px-1"><i
						class="fa fa-bell"></i><span class="notification-icon"></span></a>
					<div aria-labelledby="notifications" class="dropdown-menu">
						<a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-violet text-white">
									<i class="fab fa-twitter"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">You have 2 followers</p>
								</div>
							</div>
						</a><a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-green text-white">
									<i class="fas fa-envelope"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">You have 6 new messages</p>
								</div>
							</div>
						</a><a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-blue text-white">
									<i class="fas fa-upload"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">Server rebooted</p>
								</div>
							</div>
						</a><a href="#" class="dropdown-item">
							<div class="d-flex align-items-center">
								<div class="icon icon-sm bg-violet text-white">
									<i class="fab fa-twitter"></i>
								</div>
								<div class="text ml-2">
									<p class="mb-0">You have 2 followers</p>
								</div>
							</div>
						</a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item text-center"><small
							class="font-weight-bold headings-font-family text-uppercase">View
								all notifications</small></a>
					</div></li>
				<li class="nav-item dropdown ml-auto"><a id="userInfo"
					href="http://example.com" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"
					class="nav-link dropdown-toggle"><img
						src="/plzdaengs/template/img/avatar-6.jpg" alt="Jason Doe"
						style="max-width: 2.5rem;" class="img-fluid rounded-circle shadow"></a>
					<div aria-labelledby="userInfo" class="dropdown-menu">
						<a href="#" class="dropdown-item"><strong
							class="d-block text-uppercase headings-font-family">Mark
								Stephen</strong><small>Web Developer</small></a>
						<div class="dropdown-divider"></div>
						<a href="#" class="dropdown-item">Settings</a><a href="#"
							class="dropdown-item">Activity log </a>
						<div class="dropdown-divider"></div>
						<a href="/plzdaengs/login.html" class="dropdown-item">Logout</a>
					</div></li>
			</ul>
		</nav>
	</header>

	<div class="d-flex align-items-stretch" id="document">
		<!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp"%>
		<!-- 사이드 sidebar -->

		<div class="page-holder w-100 d-flex flex-wrap">
			<div class="container-fluid" id="contents">

				<section class="py-5">
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
					<div class="col-lg-8 mb-4 mb-lg-0" style="padding-right: 0">
					<div class="card">
						<div class="card-header">
							<h5 class="text-uppercase mb-0">캘린더</h5>
						</div>
						<div class="card-body">
							<div class="cal_top">
								<a href="#" id="movePrevMonth"><span id="prevMonth"
									class="cal_tit">&lt;</span></a> <span id="cal_top_year"></span> <span
									id="cal_top_month"></span> <a href="#" id="moveNextMonth"><span
									id="nextMonth" class="cal_tit">&gt;</span></a>
							</div>
							<div id="cal_tab" class="cal"></div>
						</div>
					</div>
					</div>
					
					
					<div class="col-lg-4" style="padding-left: 0">
                <div class="card">
                  <div class="card-header">
                    <h5 class="text-uppercase mb-0">일정리스트</h5>
                  </div>
                  <div class="card-body">
                    <table class="table card-text">
                      <thead>
                        <tr>
                          <th>날짜</th>
                          <th>시간</th>
                          <th>일정</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td>Mark</td>
                          <td>Otto</td>
                          <td>@mdo</td>
                        </tr>
                        <tr>
                          <td>Jacob</td>
                          <td>Thornton</td>
                          <td>@fat</td>
                        </tr>
                        <tr>
                          <td>Larry</td>
                          <td>the Bird</td>
                          <td>@twitter</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              
          
                <div class="card">
                  <div class="card-header">
                    <h5 class="text-uppercase mb-0">일정상세</h5>
                  </div>
                  <div class="card-body" id="meetingdetail">
                   <label id="">2019.05.22</label><label id="">오후 07:00</label><label id="">정기 한강산책</label><br>
                   <textarea class="form-control" rows="4" cols="3" contenteditable="false">내용을 입력하세요.</textarea>
                  </div>
                </div>
             
					
					
					
                <div class="card">
                  <div class="card-header">
                    <h5 class="text-uppercase mb-0">참가자 명단</h5>
                  </div>
                  <div class="card-body">
                   <textarea class="form-control" rows="4" cols="3" contenteditable="false"></textarea>
                 
              </div>
                </div>
              </div>
					
					
					</div>

					<script type="text/javascript">
						var today = null;
						var year = null;
						var month = null;
						var firstDay = null;
						var lastDay = null;
						var $tdDay = null;
						var $tdSche = null;

						$(document).ready(function() {
							drawCalendar();
							initDate();
							drawDays();
							$("#movePrevMonth").on("click", function() {
								movePrevMonth();
							});
							$("#moveNextMonth").on("click", function() {
								moveNextMonth();
							});
							
							
							//클릭시 모달창 생성이벤트
							var dArr = $(".cals");
							$(dArr).click(function(){
								var dayText = $(this).find("div[class=cal-day]").text();
								//alert(text);
								
								if(dayText == "")
										return;
								else
									var yearText = $("#cal_top_year").text();
									var monthText = $("#cal_top_month").text();
									alert(dayText);
									$("#date").text(yearText + "년 " + monthText+ "월 " + dayText + "일");
									//$("select[name=month]>option").append(monthText+ " / ");
									//$("select[name=day]>option").append(dayText);
									$("#gmmeetingModal").modal();
								
							});
							
							
						});

						//calendar 그리기
						function drawCalendar() {
							var setTableHTML = "";
							setTableHTML += '<table class="calendar">';
							setTableHTML += '<tr><th>SUN</th><th>MON</th><th>TUE</th><th>WED</th><th>THU</th><th>FRI</th><th>SAT</th></tr>';
							for (var i = 0; i < 6; i++) {
								setTableHTML += '<tr height="100">';
								for (var j = 0; j < 7; j++) {
									setTableHTML += '<td class="cals" style="text-overflow:ellipsis;overflow:hidden;white-space:nowrap">';
									setTableHTML += '    <div class="cal-day"></div>';
									setTableHTML += '    <div class="cal-schedule"></div>';
									setTableHTML += '</td>';
								}
								setTableHTML += '</tr>';
							}
							setTableHTML += '</table>';
							$("#cal_tab").html(setTableHTML);
						}

						//날짜 초기화
						function initDate() {
							$tdDay = $("td div.cal-day")
							$tdSche = $("td div.cal-schedule")
							dayCount = 0;
							today = new Date();
							year = today.getFullYear();
							month = today.getMonth() + 1;
							firstDay = new Date(year, month - 1, 1);
							lastDay = new Date(year, month, 0);
						}

						//calendar 날짜표시
						function drawDays() {
							$("#cal_top_year").text(year);
							$("#cal_top_month").text(month);
							for (var i = firstDay.getDay(); i < firstDay
									.getDay()
									+ lastDay.getDate(); i++) {
								$tdDay.eq(i).text(++dayCount);
							}
							for (var i = 0; i < 42; i += 7) {
								$tdDay.eq(i).css("color", "red");
							}
							for (var i = 6; i < 42; i += 7) {
								$tdDay.eq(i).css("color", "blue");
							}
						}

						//calendar 월 이동
						function movePrevMonth() {
							month--;
							if (month <= 0) {
								month = 12;
								year--;
							}
							if (month < 10) {
								month = String("0" + month);
							}
							getNewInfo();
						}

						function moveNextMonth() {
							month++;
							if (month > 12) {
								month = 1;
								year++;
							}
							if (month < 10) {
								month = String("0" + month);
							}
							getNewInfo();
						}

						function getNewInfo() {
							for (var i = 0; i < 42; i++) {
								$tdDay.eq(i).text("");
							}
							dayCount = 0;
							firstDay = new Date(year, month - 1, 1);
							lastDay = new Date(year, month, 0);
							drawDays();
						}
					</script>
					<!-- <button type="button" data-toggle="modal" data-target="#gmmeetingModal" class="btn btn-primary">Form in a simple modal </button> -->
				</section>
			</div>
			<div id="gmmeetingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" class="modal fade text-left">
                      <div role="document" class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h4 id="exampleModalLabel" class="modal-title">소모임 일정 생성</h4>
                            <button type="button" data-dismiss="modal" aria-label="Close" class="close"><span aria-hidden="true">×</span></button>
                          </div>
                          <div class="modal-body">
                            <p>소모임원들과 함께할 즐거운 소모임 일정을 등록하세요.</p>
                            <form>
                              <div class="form-group">
                                <label>일정 제목</label>
		                        <div class="col-md-9">
                                <input type="text" placeholder="일정을 입력하세요." class="form-control">
		                        </div>
                   			   </div>
                              <div class="line"></div>
                              <div class="form-group">       
                                <label>일정 상세내용</label>
                                <textarea class="form-control" rows="4" cols="3" placeholder="일정 상세내용을 입력하세요."></textarea>
                              </div>
                              <div class="line"></div>
                              <div class="form-group" id="managemeetingdate">       
                                <label>날짜</label>
                                <div style="inline:right;">
                                <label id="date"></label>
                                </div>
                                <div style="inline:right;">
                                <label></label>
                                </div>
                                <label>시간</label>
                                <div style="inline:right;">
                                <select name="ampm">
                             		<option>오전</option>
                             		<option>오후</option>
                                </select>
                                <select name="hour">
                             		<option>1</option>
                             		<option>2</option>
                             		<option>3</option>
                             		<option>4</option>
                             		<option>5</option>
                             		<option>6</option>
                             		<option>7</option>
                             		<option>8</option>
                             		<option>9</option>
                             		<option>10</option>
                             		<option>11</option>
                             		<option>12</option>
                                </select>
                                <select name="minute">
                             		<option>00</option>
                             		<option>15</option>
                             		<option>30</option>
                             		<option>45</option>
                                </select>
                                <label><input name="endtime" type="checkbox">해산시간 추가</label>
                                </div>
                              </div>
                               <div class="line"></div>
 								<div class="form-group">
                                <label>장소</label>
		                        <div class="col-md-9">
                                <input type="text" placeholder="한강고수부지 지도api" class="form-control">
		                        </div>
                   			   </div>
                               <div class="line"></div>
                            </form>
                          </div>
                          <div class="modal-footer">
                          
                            <button type="submit" data-dismiss="modal" class="btn btn-primary">소모임생성</button>
                            <button type="button" data-dismiss="modal" class="btn btn-primary">취소</button>
                            
                          </div>
                        </div>
                      </div>
                    </div>
			<%@ include file="/template/footer.jsp"%>
		</div>
	</div>
	<%@ include file="/template/default_js_link.jsp"%>
</body>
</html>