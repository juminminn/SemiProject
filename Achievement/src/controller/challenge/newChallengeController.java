package controller.challenge;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ChallengeCategory;
import service.challenge.face.ChallengeService;
import service.challenge.impl.ChallengeServiceImpl;

@WebServlet("/user/challenge/new")
public class newChallengeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChallengeService challengeService = new ChallengeServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    	System.out.println("chllenge/new - [GET]");
    	//파라미터 저장하기
    	ChallengeCategory number = challengeService.getCategory(req);
    	//파라미터로 카테고리 정보 불러오기
    	ChallengeCategory info = challengeService.getCategoryInfo(number);
    	// view에 카테고리 정보 전송하기
    	req.setAttribute("ca_title", info);
    	
    	req.getRequestDispatcher("/WEB-INF/views/userChallenge/newChallenge.jsp").forward(req, resp);
    }

}
