package controller.participant;

import java.io.IOException;
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

/**
 * Servlet implementation class ParticipantCertificationViewController
 */
@WebServlet("/participant/certification/view")
public class ParticipantCertificationViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantService participantService = new ParticipantServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//로그인이 되어있지 않으면 리다이렉트
		if(req.getSession().getAttribute("login")==null) {
			resp.sendRedirect("/");
			return;
		}
		Certification certification = participantService.getCertificationno(req);
		certification = participantService.certificationView(certification);
		
		HttpSession session = req.getSession();
		session.setAttribute("ceIsSuccess", certification.getCeIsSuccess()); //현재 참가자의 성공여부 상태를 session에 저장한다(W외에 상태시 delete, update 수행 불가)
		
		int chNo = (Integer)session.getAttribute("chNo"); //세션에 있는 chNo을 가져온다
		String title = participantService.getTitle(chNo); //chNo을 통한 제목 반환
		String chWay = participantService.getChway(chNo); //chNo을 통한 인증 방법 반환
		
		
		req.setAttribute("certification", certification);
		req.setAttribute("title", title);
		req.setAttribute("chWay", chWay);
		
		
		req.getRequestDispatcher("/WEB-INF/views/participantCertification/view.jsp")
			.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
