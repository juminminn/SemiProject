<%@page import="dto.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%List<Board> boardList = (List)request.getAttribute("boardList");
      List<Board> mvList = (List)request.getAttribute("MvList");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include  file="/WEB-INF/views/layout/bootHeader.jsp"%>
<%@include file="/WEB-INF/views/layout/navigation.jsp" %>

<!-- 부트스트랩 3.2.2 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#btnSearch").click(function(){
		var selected = $("#selection").val();

		if(selected == '제목및 내용')
			location.href="?search_type=title_content&keyword=" + $("#pSearch").val();
		else
			location.href="?search_type=comment&keyword=" + $("#pSearch").val();
	})
	
	$("#btnWrite").click(function(){
		location.href="/board/write";
	})

})
</script>

<div class="container" style="float:center; width:900px">

<h3><%=boardList.get(0).getB_name() %></h3>

<div class="mostView">
	<p style="font-size:20px;">최근 인기글</p>
	<div style="float:left; width:50%;" class="left shadow">
		<%for(int i=0; i<mvList.size(); i++){ %>
		<p><a href="/board/view?pno=<%=mvList.get(i).getPno() %>"><%=mvList.get(i).getP_Title() %></a></p>
		<%if(i == 4) break; }%>
	</div>
	<div style="float:left; width:50%;" class="right shadow">
		<%if(mvList.size() > 5){
			for(int i=5; i < mvList.size(); i++){%>
		<p><a href="/board/view?pno=<%=mvList.get(i).getPno() %>"><%=mvList.get(i).getP_Title() %></a></p>
		<%} } %>
	</div>
</div>

<table class="table table-striped table-hover table-condensed">
<tr>
	<th>No.</th>
	<th style="text-align : center">제목</th>
	<th style="text-align : center" >작성자</th>
	<th style="text-align : center">날짜</th>
	<th style="text-align : center">조회수</th>
</tr>
<%for(int i=0; i<boardList.size(); i++){ %>
<tr>
	<td style="width : 10%"><%= boardList.get(i).getPno() %></td>
	<td style="width : 55%;">
		<a href="/board/view?pno=<%=boardList.get(i).getPno() %>">
			<%=boardList.get(i).getP_Title() %>
		</a>
	</td>
	<td style="width : 10%; text-align : center;"><%= boardList.get(i).getU_nick() %></td>
	<td style="width : 15%; text-align : center;"><%= boardList.get(i).getP_Create_Date() %></td>
	<td style="width : 10%; text-align : center;" ><%= boardList.get(i).getP_views() %></td>
</tr>
<%} %>
</table>

<input type="text" id="pSearch"/>
<select id="selection">
	<option>제목및 내용</option>
	<option>댓글</option>
</select>
<button id="btnSearch"><i class="fas fa-search"></i></button>

<button id="btnWrite" class="btn btn-primary" style="float:right;">글쓰기</button>

</div>
<%if(request.getParameter("keyword") == null){ %>
<%@include file="/WEB-INF/views/layout/paging.jsp"%>
<%}else{ %>
<%@include file="/WEB-INF/views/post/postPaging.jsp"%>
<%} %>
<%@include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>