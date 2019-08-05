<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- template -->
<title></title>

<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/plzdaengs/template/grouptems/photoboard/main.css" />
<noscript>
	<link rel="stylesheet"
		href="/plzdaengs/template/grouptems/photoboard/noscript.css" />
</noscript>

	
<script>

</script>
<%@ include file="/template/default_link.jsp"%>
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
				<div></div>
					<div class="is-preload-0 is-preload-1 is-preload-2">
						<!-- Main -->
						<div id="main">
							<!-- Header -->
							<header id="header">
								<h1>Lens</h1>
								<p>
									Just another fine responsive site template by <a
										href="http://html5up.net">HTML5 UP</a>
								</p>
								<ul class="icons">
									<li><a href="#" class="icon fa-twitter"><span
											class="label">Twitter</span></a></li>
									<li><a href="#" class="icon fa-instagram"><span
											class="label">Instagram</span></a></li>
									<li><a href="#" class="icon fa-github"><span
											class="label">Github</span></a></li>
									<li><a href="#" class="icon fa-envelope-o"><span
											class="label">Email</span></a></li>
								</ul>
							</header>

							<!-- Thumbnail -->
							<section id="thumbnails">
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"
										data-position="left center"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Diam tempus accumsan</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Vivamus convallis libero</h2>
									<p>Sed velit lacus, laoreet at venenatis convallis in lorem
										tincidunt.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"
										data-position="top center"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Nec accumsan enim felis</h2>
									<p>Maecenas eleifend tellus ut turpis eleifend, vitae
										pretium faucibus.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Donec maximus nisi eget</h2>
									<p>Tristique in nulla vel congue. Sed sociis natoque
										parturient nascetur.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"
										data-position="top center"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Nullam vitae nunc vulputate</h2>
									<p>In pellentesque cursus velit id posuere. Donec vehicula
										nulla.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Phasellus magna faucibus</h2>
									<p>Nulla dignissim libero maximus tellus varius dictum ut
										posuere magna.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Proin quis mauris</h2>
									<p>Etiam ultricies, lorem quis efficitur porttitor,
										facilisis ante orci urna.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Gravida quis varius enim</h2>
									<p>Nunc egestas congue lorem. Nullam dictum placerat ex
										sapien tortor mattis.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Morbi eget vitae adipiscing</h2>
									<p>In quis vulputate dui. Maecenas metus elit, dictum
										praesent lacinia lacus.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Habitant tristique senectus</h2>
									<p>Vestibulum ante ipsum primis in faucibus orci luctus ac
										tincidunt dolor.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Pharetra ex non faucibus</h2>
									<p>Ut sed magna euismod leo laoreet congue. Fusce congue
										enim ultricies.</p>
								</article>
								<article>
									<a class="thumbnail" href="/plzdaengs/group/img/001.jpg"><img
										src="/plzdaengs/group/img/001.jpg" alt="" /></a>
									<h2>Mattis lorem sodales</h2>
									<p>Feugiat auctor leo massa, nec vestibulum nisl erat
										faucibus, rutrum nulla.</p>
								</article>
							</section>

							<!-- <!-- Footer -->
							<footer id="footer">
								<ul class="copyright">
									<li>&copy; Untitled.</li>
									<li>Design: <a href="http://html5up.net">HTML5 UP</a>.
									</li>
								</ul>
							</footer>
							-->
						</div>
					</div>
				</section>
			</div>
			<%@ include file="/template/footer.jsp"%>
		</div>
	</div>
							<!-- Scripts -->

	<%@ include file="/template/default_js_link.jsp"%>
</body>
</html>