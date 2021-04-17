<%@page import="dto.Challenge"%>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<%@ include file="/WEB-INF/views/layout/navigation.jsp" %>
<%Member member = (Member)request.getAttribute("member"); %>
<%Challenge challenge = (Challenge)request.getAttribute("challenge"); %>
<!-- iamport 1.1.5 라이브러리 추가 -->
<script src="https://service.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>

<!-- jQuery 2.2.4 라이브러리 추가 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	// iamport 변수 초기화
	var IMP = window.IMP;
	IMP.init('imp81015790');	// 가맹점 식별코드, 회원가입해서 직접 넣어야합니다
	//결제 모듈 불러오기
	requestPayment();
});

// 결제 요청 - 결제 모듈 불러오기
function requestPayment() {
	IMP.request_pay({
	    pg : 'html5_inicis', //PG사 - 'kakao':카카오페이, 'html5_inicis':이니시스(웹표준결제), 'nice':나이스페이, 'jtnet':제이티넷, 'uplus':LG유플러스, 'danal':다날, 'payco':페이코, 'syrup':시럽페이, 'paypal':페이팔
	    pay_method : 'card', //결제방식 - 'samsung':삼성페이, 'card':신용카드, 'trans':실시간계좌이체, 'vbank':가상계좌, 'phone':휴대폰소액결제
	    merchant_uid : 'merchant_' + new Date().getTime(), //고유주문번호 - random, unique
	    name : '주문명:참가하기', //주문명 - 선택항목, 결제정보 확인을 위한 입력, 16자 이내로 작성
	    amount : <%=challenge.getChMoney()%>, //결제금액 - 필수항목
	    buyer_email : '<%=member.getEmail()%>', //주문자Email - 선택항목
	    buyer_name : '<%=member.getUsername()%>', //주문자명 - 선택항목
	    buyer_tel : '<%=member.getPhone()%>', //주문자연락처 - 필수항목, 누락되면 PG사전송 시 오류 발생
	    buyer_addr : '<%=member.getAddress()%>', //주문자주소 - 선택항목
	    buyer_postcode : '<%=member.getPost()%>', //주문자우편번호 - 선택항목
	    m_redirect_url : 'https://www.yourdomain.com/payments/complete' //모바일결제후 이동페이지 - 선택항목, 모바일에서만 동작
	    
	}, function(rsp) { // callback - 결제 이후 호출됨, 이곳에서 DB에 저장하는 로직을 작성한다
	    if ( rsp.success ) { // 결제 성공 로직
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	        msg += '결제명' + rsp.name;
	        msg += '[rsp.success]';

			$("input[name='name']").val(rsp.name);
	        $("input[name='imp_uid']").val(rsp.imp_uid);
	        $("input[name='merchant_uid']").val(rsp.merchant_uid);
	        $("input[name='paid_amount']").val(rsp.paid_amount);
	        $("input[name='apply_num']").val(rsp.apply_num);
	        $("input[name='chNo']").val(<%=challenge.getChNo()%>)
	        $("input[name='pay_name']").val(rsp.name)
	        $("form").submit();
	        
	        
	       
	        
	    } else { // 결제 실패 로직
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	        location.href="/user/challenge/list";
	        
	    }
	    alert(msg);
	});
	
}

</script>

<form style="display: none" action="/participant/pay/complete" method="post">
<input type="text" name="chNo" value=<%=challenge.getChNo() %>/>
<input type="text" name="pay_name"/>
<input type="text" name="imp_uid" />
<input type="text" name="merchant_uid" />
<input type="text" name="paid_amount" />
<input type="text" name="apply_num" />
</form>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>