<%@page import="dto.Certification"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%Certification certification = (Certification)request.getAttribute("certification"); %>
<%String title = (String)request.getAttribute("title"); %>
<%String chWay = (String)request.getAttribute("chWay"); %>
<%@ include file="/WEB-INF/views/layout/bootAdminHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootAdminNavigation.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$('#challenge').css({"background":"#EC9A29"})
		
	//성공버튼 동작
	$("#btnSuccess").click(function() {
		$("input[name='whather']").val('Y');
		$("form").submit();
	});

	//실패버튼 동작 
	$("#btnFail").click(function() {
		$("input[name='whather']").val('N');
		$("form").submit();
	});
	
	$("#btnList").click(function(){
		$(location).attr("href","/founder/certification/list");
	})
})
</script>
<div class="container">
<form action="/founder/certification/whather" method="post">
<%--인증 번호 --%>
<input type="hidden" name="ceNo" value="<%=certification.getCeNo()%>">
<%--성공/실패 여부 --%>
<input type="hidden" name="whather" >
<table class="table table-responsive text-center">
	<tr><td colspan="2"><i class="far fa-thumbs-up"></i>&nbsp;&nbsp;<%=title %>인증 상세보기</td></tr>
	<tr><td colspan="2" id="date"></td></tr>
	
	<tr><td colspan="2">	
	<%--더미 데이터와 구분하기 위하여 --%>
		<%if(certification.getCeStoredName().contains("저장")){ %> 
			<img src="/resources/img/challenge.png" width="400" height="300"/>
		<%}else{ %>
			<img src="/upload/<%=certification.getCeStoredName() %>" width="400" height="300"/>
		<%} %>
	</td></tr>
	<tr>
	<%if(certification.getCeIsSuccess().equals("W")) {%>
	<td colspan="2">대기</td>
	<%}else if(certification.getCeIsSuccess().equals("Y")){ %>
	<td colspan="2" style="color:blue">성공</td>
	<%}else{ %>
	<td colspan="2" style="color:red">실패</td>
	<%} %>
	</tr>
	<tr><td colspan="2">인증 방법 <br><%=chWay %></td></tr>
</table>
</form>
<div class="text-center">
<button id="btnList" class="btn btn-primary">목록</button>
<button id="btnSuccess" class="btn btn-info">성공</button>
<button id="btnFail" class="btn btn-danger">실패</button>
</div>
</div>


<%@ include file="/WEB-INF/views/layout/footer.jsp" %>