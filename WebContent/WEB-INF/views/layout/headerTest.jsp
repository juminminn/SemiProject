<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"></script>
<link rel="stylesheet" href="/resources/css/mainPageTest.css">
<script type="text/javascript" src="/resources/js/mainPage.js"></script>

<style type="text/css">
headerContainer {
  position: relative;
  width: 900px;
  box-sizing: border-box;
  margin: 0;
  background: #143642;
  background-size: cover;
}

div#headerText {
  min-height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}
header {
  text-align: center;
  color: #143642;
  transition: .4s;
}
header:hover {
  background: #EC9A29;
  color: #A8201A;
}

#headerContainer > h1 {
  text-align: center;
  font-size: 2rem;
  width: 100%;
  letter-spacing: .5rem;
}
#headerContainer > h1 > nav a {
  text-decoration: none;
  padding: 1rem;
}


#searchForm{
	display: inline;
	margin-left: 20px;
}

#loginUl{
	position: absolute;
	margin-left:120px;
	display: inline;

}

</style>


</head>
<body>
<header>
<div id="headerContainer">
	<h1>Achievement</h1>

	<div>
		<form action="#" method="get" id="searchForm">
			<input type="text" id="search" name="search" />
				<button class="startBtn"><i class="fas fa-search"></i></button>
		</form>
		<ul id="loginUl">
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
					<li><a href="/mypage/home">마이페이지</a></li>
				</ul>
			<%} %>
			</li>
		</ul>
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	 	<%-- <div id="loginDiv">
		<ul >
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
						<li><a href="/mypage/home">마이페이지</a></li>
					</ul>
				<%} %>
			</li>
		</ul>	
	</div> --%>
</div>



</header>
