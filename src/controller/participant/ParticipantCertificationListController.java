package controller.participant;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Certification;
import service.participant.face.ParticipantService;
import service.participant.impl.ParticipantServiceImpl;
import util.Paging;

/**
 * Servlet implementation class ParticipantCertificationListController
 */
@WebServlet("/participant/certification/list")
public class ParticipantCertificationListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ParticipantService participantService = new ParticipantServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging =null;
		paging = participantService.getCertificaitonPaging(req);
		List<Certification> certificationList = null;
		//챌린지 번호 얻어오기
		int chNo = participantService.getChallengeno(req);
		//참여 챌린지 번호 얻어오기
		int paNo = participantService.getParticipationno(req);
		//참여중인 챌린지 제목 얻어오기
		String title = participantService.getTitle(req);
		
		certificationList = participantService.getList(paging,paNo);
		
		//세션 객체 사용
		HttpSession session = req.getSession();		
		session.setAttribute("chNo", chNo); //chNo저장
		session.setAttribute("paNo", paNo); //paNo저장
		
		//페이징 객체를 MODEL값으로 전달
		req.setAttribute("paging", paging);
		req.setAttribute("title", title);
		req.setAttribute("chNo", chNo);
		
		//좋아요 등록 여부와 신고 등록 여부를 가져온다
		Map<String, Boolean> whethers = participantService.getWhethers(req); 
		
		//조회결과 MODEL값 전달
		req.setAttribute("certificationList", certificationList);
		req.setAttribute("whethers", whethers);
		
		req.getRequestDispatcher("/WEB-INF/views/participantCertification/list.jsp")
		.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
