<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/bootHeader.jsp" %>
<%@ include file="/WEB-INF/views/layout/bootNavigation.jsp" %>
<%
List<String> cycle = (List<String>)request.getAttribute("cycle");
List<String> category = (List<String>)request.getAttribute("category");
String name = (String)request.getAttribute("name");

%>

<script type="text/javascript"
src ="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
function submitContents(elClickedObj){
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["ch_way"].exec("UPDATE_CONTENTS_FIELD",[]);
	
	try{
		//<form>태그의 submit수행
		elClickedObj.form.submit();
	}catch(e){
		
	}
}
</script>

<script type="text/javascript">
$(document).ready(function() {
	//작성버튼 동작
	$("#btnWrite").click(function() {
		
		//스마트 에디터의 내용을 <textarea>에 적용하는 함수를 호출한다
		submitContents($("#btnWrite"))
		//<form> submit
		$("form").submit();
	});
	
	//취소버튼 동작
	$("#btnCancel").click(function() {
		history.go(-1);
	});
	//이미지 미리보기
	$('#file').change(function(){
	    setImageFromFile(this, '#preview');
	});
	
	$("#ch_title").blur(function(){ //포커스를 잃었을떄
		var regex =/^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\s]+$/;
		if(!regex.test($(this).val())){
			$("#messageTitle").css({"color":"#d9534f"})
			$("#messageTitle").html("한글, 영어, 숫자만 가능합니다.")
		}else{
			$("#messageTitle").css({"color":"#5cb85c"})
			$("#messageTitle").html("멋진 제목이네요!")
		}
		
	})
	
	
	
});

//미리 보기
function setImageFromFile(input, expression) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(expression).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}

</script>

<style type="text/css">
#ch_way {
	width: 88%;
}
span{
	float:left;

}
</style>

<div class="container">

<h3>챌린지 등록</h3>
<hr>

<div>
<%--/user/challenge/write --%>
<form action="#" method="post" enctype="multipart/form-data">
<table class="table table-responsive text-center">
		<tr><td colspan="2"><label for="file"><img id="preview" src="/resources/img/challenge.png" width="400" height="300"/></label><input type="file" accept="image/*" id="file" name="file" style="width:100%; display: none;"/></td></tr>
		<tr><td>제목</td><td><input type="text" id="ch_title" name="ch_title" style="width:100%" class="form-control"/><span id="messageTitle"></span></td></tr>
		<tr><td>카테고리</td><td>
		<select style="width:100%" id="ch_category" name="ch_category" class="form-control">
			<%for(int i=0; i<category.size(); i++){ %>
			<option value="<%=category.get(i)%>"><%=category.get(i)%></option>
			<%} %>
		</select>
		</td></tr>
		<tr><td>내용</td><td><input type="text" class="form-control" id="ch_content" name="ch_content" style="width:100%"/></td></tr>
		<tr><td>참가비</td><td><input type="number" class="form-control" name="ch_money" step="1000" min="0" style="width:100%"/></td></tr>
		<tr><td>개설자</td><td class="text-left" ><input type="text" class="form-control" id="ch_user_name" name="ch_user_name" style="width:100%" value="<%=name%>" readonly/></td></tr>
		<tr><td>시작날짜</td><td><input type="date" class="form-control" id="ch_start_date" name="ch_start_date" style="width:100%"/></td></tr>
		<tr><td>마감날짜</td><td><input type="date" class="form-control" id="ch_start_date" name="ch_end_date" style="width:100%"/></td></tr>
		<tr><td >인증빈도</td><td>
		<select style="width:100%" id="ch_cycle" name="ch_cycle" class="form-control">
			<%for(int i=0; i<cycle.size(); i++){ %>
			<option value="<%=cycle.get(i)%>"><%=cycle.get(i)%></option>
			<%} %>
		</select>
		</td></tr>
		<tr><td >인증 가능 시작 시간</td><td><input type="time" id="ch_start_time" name="ch_start_time" class="form-control" style="width:100%"/></td></tr>
		<tr><td >인증 가능 끝나는 시간</td><td><input type="time" id="ch_end_time" name="ch_end_time" class="form-control" style="width:100%"/></td></tr>
		<tr><td >인증 방법</td><td colspan="2"><textarea id="ch_way" name="ch_way"></textarea></td></tr>
	</table>
</form>
</div>

<div class="text-center">	
	<button type="button" id="btnWrite" class="btn btn-info">작성</button>
	<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
</div>
</div>

<!-- .container -->
</div>

<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "ch_way", //에디터가 적용될<textarea>의 ID를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})

</script>


<%@ include file="/WEB-INF/views/layout/footer.jsp"%>