<%@page import="dto.Comment"%>
<%@page import="java.util.List"%>
<%@page import="dto.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%Post post = (Post)request.getAttribute("post"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		url : "/comment",
		type : "GET",
		data : {pno : <%=post.getP_no() %>},
		dataType : "html",
		success : function(result){
			$("#comment").html(result);
		}
	});
	
	
	$("#btnInsert").click(function(){
		var login = <%=session.getAttribute("login")%>
		if(login){
			$.ajax({
				url : "/comment/insert",
				type : "POST",
				data : {content : $("#c_content").val(), pno: <%=post.getP_no()%>},
				success : function(){
					$.ajax({
						url : "/comment",
						type : "GET",
						data : {pno : <%=post.getP_no() %>},
						dataType : "html",
						success : function(result){
							$("#comment").html(result);
							$("#c_content").val('');
						}
					});
					//내부ajax끝
				}
				//success끝
			});
			//if문 이전
		}else{
			alert('로그인후 등록이 가능합니다');
			location.href="/member/login";
		}
	});
	
	
	$("#boardDelete").click(function(){
		var check = confirm('정말로 삭제 하시겠습니까?');
		if(check){
			$.ajax({
				url:"/board/delete",
				type:"POST",
				data:{pno:<%=post.getP_no()%>},
				success:function(){
					location.replace("/board/3");	
				}
			});
		}
	});
	
})
</script>
</head>
<body>
<%@include  file="/WEB-INF/views/layout/bootHeader.jsp"%>
<%@include file="/WEB-INF/views/layout/navigation.jsp" %>



<div class="container" style="width:900px; text-align:center; margin:0 auto;">
	<div class="content">
		<table class="table table-bordered" style="margin:0 auto; width:100%; text-align:left;" border="1" >
			<tr>
				<td class="info">글번호</td><td><%=post.getP_no() %></td>
			</tr>
			<tr>
				<td class="info">제목</td><td><%=post.getP_title() %></td>
			</tr>
			<tr>
				<td class="info">작성자</td><td><%=post.getU_nick() %></td>
			</tr>
			<tr>
				<td class="info">조회수</td><td><%=post.getP_views() %></td>
			</tr>
			<tr>
				<td class="info">작성일</td><td><%=post.getP_create_date() %></td>
			</tr>
			<tr><td colspan="2"><%=post.getP_content() %></td></tr>
		</table>
		
		<div>
			<span>첨부파일</span>
			<%if(post.getP_stored_name() != null) {%>
				<a href="/upload/<%=post.getP_stored_name() %>"
 				download="<%=post.getP_origin_name() %>">
				<%=post.getP_origin_name() %></a>
			<%} %>
		</div>
		
		
		<br>
		
		<%if(session.getAttribute("u_id") != null){ %>
 		<%if(session.getAttribute("u_id").equals(post.getU_id())){ %> 
		<a id="boardDelete" style="cursor:pointer">삭제</a>
		<a href="/board/update?pno=<%=post.getP_no()%>">수정</a>
		<%} 
 		}%>
	</div>
	
	<br>
	<hr>
	
	<div id="comment">
		
	</div>
	
	<div>
		<h3 style="float:left;">댓글 쓰기</h3>
		<textarea id="c_content" style="width :900px; height:80px;"></textarea><br>
		<button id="btnInsert" style="float : right;">댓글 등록</button>
	</div>
</div>

<%@include file="/WEB-INF/views/layout/footer.jsp"%>
</body>
</html>