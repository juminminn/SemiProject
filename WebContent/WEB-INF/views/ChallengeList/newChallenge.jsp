<%@page import="dto.Challenge"%>
<%@page import="java.util.List"%>
<%@page import="dto.ChallengeCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Challenge> list = (List<Challenge>)request.getAttribute("newChallenges"); %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/layout/bootHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootNavigation.jsp" %>
<body>
<%@ include file="/WEB-INF/views/ChallengeList/category.jsp" %>
<div class="participant"><h3><span class="fas fa-user"></span> 참여자 현황</h3></div>
<div class="wrapper">
<%for(int i = 0; i< list.size(); i++){ %>
<%if(list.get(i).getChStoredName()== null || list.get(i).getChStoredName().contains("저장")){ %>
<div class = "imgarea">
<img src="/resources/img/challenge.png" alt="챌린지 임시페이지">
<p><%= list.get(i).getChTitle() %> <span class="fas fa-user sm"></span></p>
</div>
<%}else{ %>
<div class = "imgarea">
<img src="/resources/img/challenge.png" alt="챌린지 저장이미지">
<p><%= list.get(i).getChTitle() %> <span class="fas fa-user sm"></span></p>
<%-- <%= request.getServletContext().getRealPath("/") %>\<%=list.get(i).getChStoredName() %> --%>
</div>
<%	} %>
<%} %>
</div>
</div>
</body>
<style type="text/css">
.container{
	width : 900px;
	height : 900px;
	border : 1px solid #ccc;
	text-align : center;
	padding : 10px;
}
.participant{
	text-align : right;
}
.wrapper{
	border : 1px solid #ccc;
	width : 572px;
	margin : 0 auto;
	height : 100%;
}
.imgarea{
	width : 225px;
	float: left;
	box-sizing: border-box;
	margin : 30px;
	border : 1px solid #ccc;
}
</style>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>