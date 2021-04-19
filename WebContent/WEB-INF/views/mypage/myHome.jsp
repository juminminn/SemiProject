<%@page import="java.util.List"%>
<%@page import="dto.Challenge"%>
<%@page import="dto.Mypage"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/layout/navigation.jsp" %>

<%
	Member memberInfo =(Member) session.getAttribute("memberInfo");
	Mypage mypageInfo = (Mypage) session.getAttribute("mypageInfo");
	int refundsTotal = (int)session.getAttribute("refundsTotal"); //누적 총상금
	int paymentTotal = (int)session.getAttribute("paymentTotal"); //누적 결재액
%>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#contentDiv").load("/mypage/info") //첫 로드 화면

	
	$("#my1").click(function(){
		$("#contentDiv").load($(this).attr("href")) //메뉴 구현
		return false;
	})
	$("#my2").click(function(){
		$("#contentDiv").load($(this).attr("href")) //메뉴 구현
		return false;
	})
	$("#my3").click(function(){
		$("#contentDiv").load($(this).attr("href")) //메뉴 구현
		return false;
	})
	$("#my4").click(function(){
		$("#contentDiv").load($(this).attr("href")) //메뉴 구현
		return false;
	})
	$("#my5").click(function(){
		$("#contentDiv").load($(this).attr("href")) //메뉴 구현
		return false;
	})
	
	
})
</script>

<style type="text/css">
/* 마이페이지 전체 영역  */ 
#container{
	width: 900px;
	height: 900px;
	margin: 0 auto;
		
} 


/*-------------------------  */


/* 마이페이지 카테고리/프로필 영역 css  */
#categoryDiv{
    width: 30%;
    float: left;
    box-sizing: border-box;

}

/* 프로필  */

#profileImg{
	margin-top: 10px;
	width: 180x;
	height: 120px;
	border-radius: 20px;
}

.profileCard {
  box-shadow: 4px 4px 8px 4px rgba(0, 0, 0, 0.2);
  width: 200px;
  margin: 5px 0px;
  text-align: center;
  
}
.profileCard .title {
  color: grey;
  font-size: 18px;
}


#goToChall {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 15px;
}

#goToChall:hover{
  opacity: 0.7;
}

/* 카테고리  */
#myCategory {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
    background-color: #DAD2D8;
}
li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}
 li a.myHome {
    background-color: #EC9A29;
    color: white;
} 

li a:hover:not(.active) {
    background-color: #143642;
    color: white;
}
/* ------------------------------*/


/* 마이페이지 내용 영역 */
#contentDiv{
    width: 70%;
    float: right;
    box-sizing: border-box;
    boder: 1px solid #ccc;
}

/* ------------------------------*/
</style>

<div id="container">
	<h1>Mypage</h1>
	<hr>
	
	<!--마이페이지 프로필/카테고리  -->
	<div id="categoryDiv">
		<!--마이페이지 프로필  -->
		<div class="profileCard">
		  <% if(mypageInfo.getmStoredname() != null) {%>
			<img src="/upload/<%=mypageInfo.getmStoredname() %>" id="profileImg" >
			<% } else {%>
			<img src="/resources/logoRed" style="width:200px;" >
			<% } %>
		  <h2><%=memberInfo.getNick() %></h2>
		  <span>Total Reword: <%=refundsTotal %> &#8361;</span>
		   <p><button id="goToChall">Go to Challenge</button></p>
		</div>
			
		<!--마이페이지 카테고리  -->
		<ul id="myCategory">
			<li><a href="/mypage/info" class="myHome" id= "my1">Home</a></li>
			<li><a href="/mypage/update" id= "my2">회원정보수정</a></li>
			<li><a href="/mypage/delete" id= "my3">회원탈퇴</a></li>
			<li><a href="/mypage/challenge"id= "my4">회원목표</a></li>
			<li><a href="/mypage/reword"id= "my5">회원상금</a></li>
		</ul>
	</div>	
	<!-- -------------------- -->
	
	
	<div id="contentDiv">
	</div>	
</div>

<%@ include file="/WEB-INF/views/layout/footer.jsp"%>