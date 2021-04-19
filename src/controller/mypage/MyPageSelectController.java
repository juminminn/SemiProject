package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import dto.Mypage;
import service.member.face.MypageService;
import service.member.impl.MypageServiceImpl;

@WebServlet("/mypage/info")
public class MyPageSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// memberService 공용 객체
	private MypageService mypageService = new MypageServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("------ [MyPageSelectController] Do Get ------");
		HttpSession session = req.getSession(); // 세션 객체 생성
		
		// 유저 정보를 가져온다.
		Member member = (Member)session.getAttribute("memberInfo");
		
		// 유저 정보(마이페이지)를 가져온다.
		Mypage mypage = (Mypage) session.getAttribute("mypageInfo");
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myInfo.jsp")
		   .forward(req, resp);
	}
	
}
