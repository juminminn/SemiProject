<%@page import="dto.AdminComplaint" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<AdminComplaint> list = (List) request.getAttribute("complaintList");
%>    
    
<%@ include file="/WEB-INF/views/layout/bootAdminHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootAdminNavigation.jsp" %>


<script type="text/javascript">
	$(document).ready(function() {
	
		// + 버튼 누르면 신고등록 페이지로 이동
		$("#btnInsert").click(function() {
			location.href="/admin/complaint/insert";	
		});
	});
	
</script>


<style type="text/css">
@media (min-width: 992px) {
.container {
	   font-family: "Noto Sans KR", sans-serif !important;
	   width: 900px;
}
}
</style>


<div class="container">		

	<!-- 페이지 타이틀 -->
	<h2 class="display-3 text-left">신고 목록	
	   <button type=button id="btnInsert" class="btn btn-danger">
		 <span class="glyphicon glyphicon-plus"></span>
	   </button>
	</h2>
	<hr style= "border: 1px solid #DAD2D8  ">
	
	
	<!-- 목록 테이블 -->
	<div class="card-body center-block">
		<div class="row">	
			<table class = "table table-hover table-striped" style="text-align: center;">
					
			<!-- 항목 -->
			<thead>
			<tr>
				<th><center>번호</center></th>
				<th><center>신고일</center></th>
				<th><center>챌린지 제목</center></th>
				<th><center>카테고리</center></th>
				<th><center>신고내용</center></th>
				<th><center>경고누적횟수</center></th>
				<th><center>조치내역</center></th>
			</tr>
			</thead>
					
			<!-- 내용 -->
			<tbody>		
			<%	for(int i=0; i<list.size(); i++) { %>
			<tr>
				<td><%=list.get(i).getComNo() %></td>
				<td><%=list.get(i).getComDate() %></td>
				<td><a href="/admin/complaint/view?comNo=<%=list.get(i).getComNo() %>"><%=list.get(i).getChTitle() %></a></td>
				<td><%=list.get(i).getCaTitle() %></td>
				<td><%=list.get(i).getComContent() %></td>
				<td><%=list.get(i).getChCaution() %></td>
				<td><%=list.get(i).getComManage() %></td>
			</tr>
			<% } %>
			</tbody>		
			</table>
		</div> <!-- row -->
	</div> <!-- card-body -->
	
	<!-- 검색창 -->
	<div class="row">
	  <div class="col-md-2 col-md-offset-3">
	 	<select name="searchType" class="form-control">
			<option value="comNo">번호</option>
			<option value="caTitle">카테고리</option>
			<option value="chTitle">챌린지제목</option>
			<option value="comManagae">조치내역</option>
		</select>
	  </div>
	
	 <div class="col-md-4">
	  <div class="input-group">
		<input type="text" name="searchKeyword" value="" class="form-control" />
		  <span class="input-group-btn">
			 <button id="searchBtn" class="btn btn-default">검색</button>
		  </span>
	  </div> <!-- input-group -->
	 </div> <!-- col-md-4 -->
	</div> <!-- row -->

</div><!-- container --> 

<%@ include file="/WEB-INF/views/layout/complaintPaging.jsp" %>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
