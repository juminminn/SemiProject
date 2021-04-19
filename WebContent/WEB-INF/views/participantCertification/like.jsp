<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Map<String, Boolean> whethers = (Map<String, Boolean>)request.getAttribute("whethers"); %>
<script type="text/javascript">
$(document).ready(function () { 
    startDate();
    
    $("#paLike").click(function(){
    	$.ajax({
    		type:"get"
    		, url: "/participant/like"
    		,data:{
    			paLike : <%=whethers.get("paLike")%>
    		}, dataType: "html"
    		, success: function(res){
    			
    			$("#paLikeResult").html(res) //결과값 반영
    		}
    		,error: function(){
    			console.log("실패");
    		}
    		
    	});
    });
    
    
}); 
</script>



<%if(whethers.get("paLike")){ %>
	<button id="paLike" class="btn btn-info" style="color:red;"><i class="far fa-thumbs-up fa-2x"></i></button>
<%}else{ %>
	<button id="paLike" class="btn btn-info" style="color:#ccc"><i class="far fa-thumbs-up fa-2x"></i></button>
<%} %>