package controller.mypage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import dto.Mypage;
import dto.Participation;
import dto.Payback;
import dto.Payment;
import dto.Refunds;
import service.member.face.MypageService;
import service.member.impl.MypageServiceImpl;


@WebServlet("/mypage/reword/ajax")
public class MypageRewordAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// memberService 공용 객체
	private MypageService mypageService = new MypageServiceImpl();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); // 세션 객체 생성
		System.out.println("MypageRewordAjaxController do get");

		// 유저 정보를 가져온다.
		Member member = (Member)session.getAttribute("memberInfo");

		// 유저의 결제 정보를 가져오는 메소드
		List<Payment> paymentList = new ArrayList<>();
		paymentList = mypageService.getPaymentInfo(member.getUno());
		session.setAttribute("paymentList", paymentList); //세션의 정보 저장

		// 유저의 환급 정보를 가져오는 메소드(사용자취소, 첼린지 삭제시)
		List<Payback> paybackList = new ArrayList<>();
		paybackList = mypageService.getPaybackInfo(member.getUno());
		session.setAttribute("paybackList", paybackList); //세션의 정보 저장

		// 유저의 참여챌린지중 성공한 챌린지 정보를 가져오는 메소드
		List<Participation> parList = new ArrayList<>();
		parList = mypageService.getSuccessChallInfo(member.getUno());
		session.setAttribute("parList", parList); //세션의 정보 저장

		//유저가 환불받을 금액(상금)을 가져오는 메소드
		List<Refunds> refundsList = new ArrayList<>();
		refundsList = mypageService.getRefundAmount(parList);
		session.setAttribute("refundsList", refundsList); //세션의 정보 저장

		//년도와 월을 입력받아 해당기간의 상금목록을 가져온다.
		List<Refunds> reqRefundsPeriodList = mypageService.getReqFunds(req, refundsList);
		session.setAttribute("reqRefundsPeriodList", reqRefundsPeriodList); //세션의 정보 저장

		//년도와 월을 입력받아 해당기간의 결재목록을 가져온다.
		List<Payment> reqPaymentPeriodList = mypageService.getReqPayment(req, paymentList);
		session.setAttribute("reqPaymentPeriodList", reqPaymentPeriodList); //세션의 정보 저장

		//년도와 월을 입력받아 해당기간의 환급목록을 가져온다.
		List<Payback> reqPaybackPeriodList = mypageService.getReqPayback(req, paybackList);
		session.setAttribute("reqPaybackPeriodList", reqPaybackPeriodList); //세션의 정보 저장


		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myRewordAjax.jsp")
		.forward(req, resp);	
	}
}
