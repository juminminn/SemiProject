<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<link rel="stylesheet" href="/resources/css/bootMainPage(new).css">
<script type="text/javascript" src="/resources/js/mainPage.js"></script>
<script type="text/javascript" src="/resources/js/search.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>


</head>
<body>
<header>
	<div class="left">
		<a href = "/">
			<img src="/resources/img/AchievementWhite.png" width="250" height="100"
		alt="Achievement"  title="Achievement"> </a>
	</div>
	
	<div class="right" style="padding:30px 20px;">
	<ul class="hdropdown">
		<li>
			<a href="#" class="fa fa-user fa-3x"></a>
			<%if(session.getAttribute("login")==null || !(boolean)session.getAttribute("login")){ %>
			<!-- 비로그인 상태 -->
			<ul>
				<li><a href="/member/login">로그인</a></li>
				<li><a href="#">회원가입</a></li>
			</ul>
			<%}else if((boolean)session.getAttribute("login") && "U".equals((String)session.getAttribute("u_grade"))) {%>
				<ul>
					<li><a href="/member/logout">로그아웃</a></li>
					<li><a href="#">마이페이지</a></li>
				</ul>
			<%} %>
		</li>
	</ul>
	</div>
	<div class="center" style="padding:30px 20px;">
		<form action="/search" method="get" id ="form">
			<input type="text" id="key" name="key"/>
				<button class="startBtn" id="sumbit" type="button"><i class="fas fa-search"></i></button>
		</form>
	</div>
	
</header>