<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/bootHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootNavigation.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('#key').attr("value",<%=request.getParameter("key")%>)
})
</script>
<style type="text/css">
.container{
	width : 900px;
	height : 500px;
	border : 1px solid #ccc;
}
.detail{
	border : 1px solid #ccc;
	
}
</style>
<body>
<div class="container">
	<div class="detail">
		<h4>카테고리</h4>
		<form class="form-horizontal">
		<div class="checkbox">
		<label><input type="checkbox" id = "all" class="checkbox" name="ca_no" value = "0">전체</label>
		<label><input type="checkbox" id = "exercise" class="checkbox" name="ca_no" value = "1">운동</label>
		<label><input type="checkbox" id = "selfincrement" class="checkbox" name="ca_no" value = "2">자기관리</label>
		<label><input type="checkbox" id = "study" class="checkbox" name="ca_no" value = "3">공부</label>
		<label><input type="checkbox" id = "hobby" class="checkbox" name="ca_no" value = "4">취미</label>
		<label><input type="checkbox" id = "language" class="checkbox" name="ca_no" value = "5">외국어</label>
		<label><input type="checkbox" id = "financial" class="checkbox" name="ca_no" value = "6">재테크</label>
		<label><input type="checkbox" id = "habbits" class="checkbox" name="ca_no" value = "7">생활습관</label>
		</div>
		<div class="selectbox">
		<select name="orderby">
			<option value="ch_create_date">최근순</option>
			<option value="ch_likes">좋아요순</option>
			<option value="p_uno">참여인원순</option>
		</select>
		</div>
		<button type="button">상세검색</button>
		</form>
	</div>
</div>
</body>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>