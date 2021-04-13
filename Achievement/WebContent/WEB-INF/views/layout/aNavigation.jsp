<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$( document ).ready( function() {
	
	$( '#user' ).click( function() {
		$('#user').css({"background":"#EC9A29"})
		$('#user').nextAll().css({"background":"#FFFFFF"})
		
	})//end user
	
	
	$( '#board' ).click( function() {
		$('#board').css({"background":"#EC9A29"})
		$('#board').prevAll().css({"background":"#FFFFFF"})
		$('#board').nextAll().css({"background":"#FFFFFF"})
		
		
	}) //end board
	
	
	$( '#notice' ).click( function() {
		
		$('#notice').css({"background":"#EC9A29"})
		$('#notice').prevAll().css({"background":"#FFFFFF"})
		$('#notice').nextAll().css({"background":"#FFFFFF"})

	}) // end mainNew
	
	
	$( '#challenge' ).click( function() {

		$('#challenge').css({"background":"#EC9A29"})
		$('#challenge').prevAll().css({"background":"#FFFFFF"})
		$('#challenge').nextAll().css({"background":"#FFFFFF"})
	})//Challenge
	
	$( '#complaint' ).click( function() {

		
		$('#complaint').css({"background":"#EC9A29"})
		$('#complaint').prevAll().css({"background":"#FFFFFF"})
		$('#complaint').nextAll().css({"background":"#FFFFFF"})
	})//Complaint
})

</script>

<nav>
<div>
	<ul class="ndropdown">
		<li id="user"><a href="/admin/user/list">회원</a></li>
		<li id="board"><a href="#">게시판</a></li>
		<li id="notice"><a href="#">공지사항</a></li>
		<li id="challenge"><a href="#">챌린지</a></li>
		<li id="complaint"><a href="#">신고 </a></li>
	</ul>
	</div>
</nav>