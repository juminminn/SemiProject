<%@page import="dto.Users"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% List<Users> list = (List)request.getAttribute("userlist"); %>
<%@ include file="/WEB-INF/views/layout/aHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/aNavigation.jsp" %>
<script type="text/javascript">

</script>
<body>
<div class="content">
<fieldset class="search">
<form>
<div class="form-group">
	<select name="grade" class="contoller">
		<option value="U">회원</option>	
		<option value="M">관리자</option>	
	</select>
</div>
<div class="form-group">
	<select name="field" class="contoller">
		<option value="u_id">아이디</option>
		<option value="u_nick">닉네임</option>
	</select>
</div>
<div class="form-group">
	<label for="search" >
	<input type="text" id="search" name="search" value="" class="select textbox"/>
	</label>
</div>
<div class="form-group">
	<button class="btn">검색</button>
</div>
</form>
</fieldset>
<table>
<tr>
	<th style="width:5%">No.</th>
	<th style="width:5%">등급</th>
	<th style="width:10%">이름</th>
	<th style="width:15%">아이디</th>
	<th style="width:10%">닉네임</th>
	<th style="width:5%">성별</th>
	<th style="width:15%">생일</th>
	<th style="width:15%">가입일</th>
	<th style="width:20%">이메일</th>
</tr>
	<%for(int i = 0; i< list.size(); i++){ %>
<tr>
	<td><%=list.get(i).getUserNo() %></td>
	<td><%=list.get(i).getGrade()%></td>	
	<td><%=list.get(i).getName() %></td>
	<td><a href="#"><%=list.get(i).getUserId() %></a></td>
	<td><%=list.get(i).getUserNick() %></td>
	<td><%=list.get(i).getGender() %></td>
	<td><%=list.get(i).getBirth() %></td>
	<td><%=list.get(i).getSignupDate() %></td>
	<td><%=list.get(i).getUserEmail() %></td>
</tr>
	<%} %>
</table>
</div>
</body>
<style type="text/css">
.content{
 width : 900px;
 margin : 0 auto;
 text-align: center;
}
.search{
	border : none;
}

.form-group{	
	float : left;
	margin-left : 10px;
}

select{
 width : 8em;
 padding: 0.7em 0.3em;
 border-radius: 5px;
 border:1px solid #0009;
}
.textbox{
 width : 30em;
 line-height: 20px;
 padding: 0.6em 0.8em;
 border-radius: 5px;
 border:1px solid #0009;
}
.btn{
 width : 8em;
 padding: 0.6em 0.9em;
 border-radius: 5px;
 border:1px solid #0009;
 background : #E3564F;
 color:#fff;
 box-shadow: 0 5px 8px rgba(0,0,0,0.19), 0 3px 3px rgba(0,0,0,0.23);
 
}
table{
	width: 100%;
	border-top : 1px solid #ccc;
	border-collapse: collapse;
 	box-shadow: 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
}
table th{
	background : #DAD2D8;
	padding: 7px;
}
table td{
	padding : 5px;
	border-top : 1px solid #ccc;
	font-size: small;
	color: #8C8C8C;
}
tr:hover td{
	background: #EDE9EC;
	color:black;
}

a{
text-decoration: none;
text-align:center;
color: red;
}

.text-center{
width: 900px;
margin:0 auto;
}
.pagination li{
position:relative;
list-style:none;
float:left;
width:5%;
margin: 0 auto;
}

</style>
<%@ include file="/WEB-INF/views/layout/paging.jsp" %>
<style type="text/css">
</style>
<br>
<%@ include file="/WEB-INF/views/layout/footer.jsp"%>

