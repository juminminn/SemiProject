<%@page import="dto.Post"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include  file="/WEB-INF/views/layout/bootHeader.jsp"%>
<%@include file="/WEB-INF/views/layout/navigation.jsp" %>
<%
	Post post = (Post)request.getAttribute("post");
	String nick = request.getAttribute("u_nick").toString();
%>
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<div style="width:900px; margin:0 auto;">
	<h2>게시판이름</h2>
	<hr>
	<h3>쓰기</h3>
	<hr>
	
	<form id="writeFrm" action="/board/write" method="POST" enctype="multipart/form-data">
		<div>
			<table style="width:900px;">
				<tr><td>닉네임</td><td><%= nick %></td></tr>
				<tr><td>제목</td>
					<td><%if(post == null){ %>
						<input style="width:90%;" type="text" id="title" name="p_title" placeholder="제목"/>
						<%}else{ %>
						<input style="width:90%;" type="text" id="title" name="p_title" value="<%=post.getP_title()%>"/>
						<%} %>
					</td>
				</tr>
				<tr><td colspan="2">내용</td></tr>
				<tr><td colspan="2"><%if(post == null){ %>
						<textarea style="width:100%; height:100%;" id="content" name="p_content"></textarea>
						<%}else { %>
						<textarea style="width:100%; height:100%;" id="content" name="p_content"><%=post.getP_content() %></textarea>
						<%} %>
					</td>
				</tr>

			</table>
			<%if(post == null){ %>
				<input multiple="multiple" type="file" name="file" value="파일첨부" />
			<%}else{ %>
				<input multiple="multiple" type="file" name="file" value="<%=post.getP_origin_name() %>" />
			<%} %>
			<br><br><br>
		</div>	
	</form>
	
	<div>
			<button id="btnWrite">등록</button>
			<button id="btnCancel">취소</button>
	</div>	
	
	

</div>

<script type="text/javascript">
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		// <form>태그의 submit 수행
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", 
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
});


$(document).ready(function(){
	$("#btnWrite").click(function(){
		<%if(post == null){%>
			submitContents( $("#btnWrite") )
			$("form").submit();
		<%}else{%>
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
			$.ajax({
				url : "/board/update",
				type : "POST",
				data : {title : $("#title").val(), content : $("#content").val(), pno: <%=post.getP_no()%>},
				success : function(){
					location.href="/board/view?pno=<%=post.getP_no()%>";
				}
			});
		<%}%>
	});
	
})
</script>



<%@include file="/WEB-INF/views/layout/footer.jsp"%>
