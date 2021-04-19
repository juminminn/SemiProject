package controller.mypage;

import java.io.File;
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


@WebServlet("/mypage/update")
public class MypageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// memberService 공용 객체
	private MypageService mypageService = new MypageServiceImpl();
	Member member= null;
	Mypage mypage = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("------ [MypageUpdateController] Do Get ------");
		HttpSession session = req.getSession(); // 세션 객체 생성
		
		// 업데이트에 필요한 세션객체 받아오기
		member =(Member) session.getAttribute("memberInfo"); 
		mypage = (Mypage) session.getAttribute("mypageInfo"); 
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myUpdate.jsp")
		   .forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(); // 세션 객체 생성
		
		int check = mypageService.updateInfo(req, member, mypage);
		resp.sendRedirect("/mypage/home"); // 성공시 mypage 메인으로 이동
	}
}
