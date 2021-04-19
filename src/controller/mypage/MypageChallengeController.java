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

import dto.Challenge;
import dto.Member;
import service.member.face.MypageService;
import service.member.impl.MypageServiceImpl;

@WebServlet("/mypage/challenge")
public class MypageChallengeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// memberService 공용 객체
	private MypageService mypageService = new MypageServiceImpl();
	Member member= null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("------ [MypageChallengeController] Do Get ------");
		HttpSession session = req.getSession(); // 세션 객체 생성
		member =(Member) session.getAttribute("memberInfo");
		// 현재 사용자의 첼린지 정보를 받을 챈린지 리스트 객체
		List<Challenge> list = new ArrayList<>(); 
		
		//유저가 진행중인 챌린지 정보를 가져오는 메소드
		list = mypageService.getUserChallInfo(member.getUno());
		
		// 현재유저의 챌린지 정보 세션저장
		session.setAttribute("challList", list); 
		
		// 현재유저의 챌린지 정보 세션저장
		session.setAttribute("challList", list); 
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myChallenge.jsp")
		   .forward(req, resp);
	}

}
