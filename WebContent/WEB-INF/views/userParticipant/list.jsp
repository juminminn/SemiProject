<%@page import="dto.Member"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Member> list = (List)request.getAttribute("memberList"); %>

<%@ include file="/WEB-INF/views/layout/bootHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootNavigation.jsp" %>
<style type="text/css">
.container{
width:900px;
}


th{
background-color: #A8201A;
}

#move:hover tbody tr:hover td{
background:#143642;
color:#FFFFFF;
}

#btnTitle{
background-color: #143642;
color: white;
border-radius: 4px;
}

.right{

margin:0px 3px 5px 3px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	//리스트
	$("#btnList").click(function(){
		$(location).attr("href","/user/challenge/view?chNo=<%=(Integer)session.getAttribute("chNo")%>");
		
	})
})
</script>

<div class="container">
<div>
	<div><span>&nbsp</span></div>
	<div><span>&nbsp</span></div>
	<div class="left" style="font-size:30px; font-weight:bold;">참가자 목록 </div>
	<div><hr></div>
</div>


<table class="table table-striped table-hover table-condensed text-center">
<tr>
	<th style="width: 25%; text-align:center;">아이디번호</th>
	<th style="width: 25%; text-align:center;">아이디</th>
	<th style="width: 25%; text-align:center;">이름</th>
	<th style="width: 25%; text-align:center;">닉네임</th>
</tr>
<%	for(int i=0; i<list.size(); i++) { %>
<tr>
	<td><%=list.get(i).getUno() %></td>
	<td><%=list.get(i).getUid() %></td>
	<td><%=list.get(i).getUsername() %></td>
	<td><%=list.get(i).getNick() %></td>
	
</tr>
<%	} %>
</table>
<div class="text-left">	
	<button type="button" id="btnList" class="btn btn-info">돌아가기</button>
</div>

</div>


<%@ include file="/WEB-INF/views/userParticipant/participantPaging.jsp" %>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>