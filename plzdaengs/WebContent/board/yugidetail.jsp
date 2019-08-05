<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Bubbly - Boootstrap 4 Admin template by Bootstrapious.com</title>
<meta name="description" content="">
<%@ include file="/template/default_link.jsp" %>
<%request.setCharacterEncoding("UTF-8");%>
</head>
  <body>
    <!-- navbar-->
    <header class="header">
      <nav class="navbar navbar-expand-lg px-4 py-2 bg-white shadow"><a href="#" class="sidebar-toggler text-gray-500 mr-4 mr-lg-5 lead"><i class="fas fa-align-left"></i></a><a href="index.jsp" class="navbar-brand font-weight-bold text-uppercase text-base">Bubbly Dashboard</a>
        <ul class="ml-auto d-flex align-items-center list-unstyled mb-0">
          <li class="nav-item">
            <form id="searchForm" class="ml-auto d-none d-lg-block">
              <div class="form-group position-relative mb-0">
                <button type="submit" style="top: -3px; left: 0;" class="position-absolute bg-white border-0 p-0"><i class="o-search-magnify-1 text-gray text-lg"></i></button>
                <input type="search" placeholder="Search ..." class="form-control form-control-sm border-0 no-shadow pl-4">
              </div>
            </form>
          </li>
          <li class="nav-item dropdown mr-3"><a id="notifications" href="http://example.com" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle text-gray-400 px-1"><i class="fa fa-bell"></i><span class="notification-icon"></span></a>
            <div aria-labelledby="notifications" class="dropdown-menu"><a href="#" class="dropdown-item">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-violet text-white"><i class="fab fa-twitter"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">You have 2 followers</p>
                  </div>
                </div></a><a href="#" class="dropdown-item"> 
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-green text-white"><i class="fas fa-envelope"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">You have 6 new messages</p>
                  </div>
                </div></a><a href="#" class="dropdown-item">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-blue text-white"><i class="fas fa-upload"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">Server rebooted</p>
                  </div>
                </div></a><a href="#" class="dropdown-item">
                <div class="d-flex align-items-center">
                  <div class="icon icon-sm bg-violet text-white"><i class="fab fa-twitter"></i></div>
                  <div class="text ml-2">
                    <p class="mb-0">You have 2 followers</p>
                  </div>
                </div></a>
              <div class="dropdown-divider"></div><a href="#" class="dropdown-item text-center"><small class="font-weight-bold headings-font-family text-uppercase">View all notifications</small></a>
            </div>
          </li>
          <li class="nav-item dropdown ml-auto"><a id="userInfo" href="http://example.com" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle"><img src="/plzdaengs/template/img/avatar-6.jpg" alt="Jason Doe" style="max-width: 2.5rem;" class="img-fluid rounded-circle shadow"></a>
            <div aria-labelledby="userInfo" class="dropdown-menu"><a href="#" class="dropdown-item"><strong class="d-block text-uppercase headings-font-family">Mark Stephen</strong><small>Web Developer</small></a>
              <div class="dropdown-divider"></div><a href="#" class="dropdown-item">Settings</a><a href="#" class="dropdown-item">Activity log       </a>
              <div class="dropdown-divider"></div><a href="login.html" class="dropdown-item">Logout</a>
            </div>
          </li>
        </ul>
      </nav>
    </header>
    <div class="d-flex align-items-stretch" id="document">
    <!-- 사이드 sidebar -->
		<%@ include file="/template/sidebar.jsp" %>
		<!-- 사이드 sidebar -->
      <div class="page-holder w-100 d-flex flex-wrap">
        <div class="container-fluid px-xl-5" id="contents">
          <section class="py-5" style="max-height: none;">
					<div class="row">
						<div class="col-xl-12 col-lg-auto">
							<div class="card">
							<!-- <div class="card-header">
									<h6 class="text-uppercase mb-0">유기견 정보 게시판</h6>
								</div> -->
								<div class="card-body" align="center">
									<img src="<%=request.getParameter("popfile")%>" style="width: 28rem; height: 26rem; max-height: 80%; max-width: 80%; border: solid; border-width: thin thin thin thin;" />
								</div>
								<div class="card-body">
									<table class="table card-text col-xl-auto">
									<tbody>
											<tr>
												<th scope="row">
													<li>공고번호:<%=request.getParameter("noticeNo")%></li>
													<li>색상:<%=request.getParameter("colorCd")%></li>
													<li>나이/체중:<%=request.getParameter("age")%>/<%=request.getParameter("weight")%></li>
													<li>접수일:<%=request.getParameter("happenDt")%></li>
													<li>보호상태:<%=request.getParameter("processState")%></li>
													<li>보호센터:<%=request.getParameter("careNm")%></li>
													<li>보호센터연락처:<%=request.getParameter("careTel")%></li>
													<li>특징:<%=request.getParameter("specialMark")%></li>
												</th>
												<th scope="row">
													<li>품종:<%=request.getParameter("kindCd")%></li>
													<li>성별:<%=request.getParameter("sexCd")%></li>
													<li>발견장소:<%=request.getParameter("happenPlace")%></li>
													<li>관할기관:<%=request.getParameter("orgNm")%></li>
													<li>RFID_CD:<%=request.getParameter("desertionNo")%></li>
													<li>보호장소:<%=request.getParameter("careAddr")%></li>
													<li>중성화:<%=request.getParameter("neuterYn")%></li>
												</th>
											</tr>
									</tbody>
									</table>
								</div>
							</div>
							<button class="btn btn-primary " type="button" style="background-color: #dc3545; float: right; padding: 0.2rem 0.8rem;" onclick="javascript:history.go(-1)">목록</button>
						</div>
					</div>
				</section>
        </div>
        <%@ include file="/template/footer.jsp" %>
      </div>
    </div>
    <%@ include file="/template/default_js_link.jsp" %>
  </body>
</html>