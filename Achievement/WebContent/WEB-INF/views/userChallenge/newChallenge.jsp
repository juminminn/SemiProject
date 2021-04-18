<%@page import="dto.ChallengeCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%ChallengeCategory category = (ChallengeCategory)request.getAttribute("ca_title"); %>
<%int num = Integer.parseInt(request.getParameter("category")); %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/layout/bootHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootNavigation.jsp" %>
<body>
<div class = "container">
<%if(num == 1){ %>
<h3><%=category.getTitle() %> <span class="fas fa-dumbbell fa-sm"></span></h3>
<%}else if(num == 2){ %>
<h3><%=category.getTitle() %> <span class="fas fa-trophy fa-sm"></span></h3>
<%}else if(num==3){ %>
<h3><%=category.getTitle() %> <span class="fas fa-user-graduate fa-sm"></span></h3>
<%}else if(num==4){ %>
<h3><%=category.getTitle() %> <span class="fas fa-book-reader fa-sm"></span></h3>
<%}else if(num==5){ %>
<h3><%=category.getTitle() %> <span class="fas fa-walking fa-sm"></span></h3>
<%}else if(num==6){ %>
<h3><%=category.getTitle() %> <span class="fas fa-biking fa-sm"></span></h3>
<%}else if(num==7){ %>
<h3><%=category.getTitle() %> <span class="fas fa-language fa-sm"></span></h3>
<%}else if(num==8){ %>
<h3><%=category.getTitle() %> <span class="fas fa-won-sign fa-sm"></span></h3>
<%} %>
<hr>
<div class = "img">

</div>
</div>
</body>
<style type="text/css">
.container{
	width : 900px;
	border : 1px solid #ccc;
	text-align : center;
}

</style>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>