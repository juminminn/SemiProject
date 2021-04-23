<%@page import="dto.AdminComplaint" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/layout/bootAdminHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootAdminNavigation.jsp" %>


<script type="text/javascript">
$(document).ready(function() {

	//완료버튼 누르면 <form>.submit 동작
	$("#btnComplete").click(function() {
		$("form").submit();
		
	});
	
	//취소버튼 누르면 신고목록 페이지로 이동
	$("#btnCancle").click(function() {
		location.href="/admin/complaint/list";
	});
	
});
</script>

<style type="text/css">
@media (min-width: 992px) {
  .container {
    width: 900px;
  }
  .card-body {
  	width: 50%;
  	text-align: center
  }
}
</style>

<div class="container center-block">
	
<!-- 페이지 타이틀 -->	
<div class="jumbotron">
	<h2 class="display-3">신고 등록</h2>
</div><!-- jumbotron -->


<!-- 상세조회 폼 -->
<form action="/admin/complaint/insert" method="post">
<div class="card-body center-block">
	<div class="table-responsive">
	<table class="table">
		<tbody>
		<tr style="line-height:32px;">	
			<tr>
			<td>챌린지 번호</td>
			<td><input type="text" name="chNo" class="form-control" ></td>                            
     		</tr>                                                                                                        
            
            <tr>                
            <td>관리자 번호</td>
			<td><input type="text" name="comUno" class="form-control" value="<%=session.getAttribute("comNo") %>" readonly ></td>              
            </tr>                   
            
            <tr>                        
   			<td>신고내용</td>                              
    		<td><textarea name="comContent" class="form-control" style="resize:none;" ></textarea></td>                                         
			</tr> 
            
            <tr>
   			<td>관리자 의견</td>                              
    		<td><textarea name="comAdminContent" class="form-control" style="resize:none;" ></textarea></td>                                                
 			</tr> 
 			
 			<tr>
            <td>조치내역</td>                              
    		<td><select name="comManage" class="form-control" >
				<option>Y</option>
				<option>W</option>
				<option>N</option>
				</select></td>              
 			</tr>                          
		</tbody>                          
		</table>                     
	</div><!-- table-responsive 끝-->                   
</div><!-- card-body 끝 -->

<p></p>

	<!-- 완료, 취소 버튼 -->
	<div class="text-center">
		<input type="submit" id="btnComplete" class="btn btn-default" value='완료'>
		<button id="btnCancle" class="btn btn-default">취소</button>
	</div>
  </form>   
</div><!-- container 끝 -->

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>

