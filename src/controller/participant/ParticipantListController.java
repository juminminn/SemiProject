package controller.participant;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Member;
import service.participant.face.ParticipantService;
import service.participant.impl.ParticipantServiceImpl;
import util.Paging;

/**
 * Servlet implementation class ParticipantListController
 */
@WebServlet("/participant/list")
public class ParticipantListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantService participantService = new ParticipantServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging = null;
		paging = participantService.getParticipantPaging(req);
		//chNo 가져오기
		int chNo = participantService.getChallengeno(req);
		
		HttpSession session = req.getSession();
		
		//참가자 조회하기 
		List<Member> memberList = participantService.getParticipantList(paging, chNo);
		//참가자 삽입하기
		req.setAttribute("memberList", memberList);
		req.setAttribute("paging", paging);
		
		if(session.getAttribute("u_grade")!=null) {
			if("M".equals(String.valueOf(session.getAttribute("u_grade")))) {
				req.getRequestDispatcher("/WEB-INF/views/adminParticipant/list.jsp")
				.forward(req, resp);
			}else {
				req.getRequestDispatcher("/WEB-INF/views/userParticipant/list.jsp")
				.forward(req, resp);
			}
			return;
		}
		resp.sendRedirect("/");
	}
}
