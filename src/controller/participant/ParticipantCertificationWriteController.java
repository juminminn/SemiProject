package controller.participant;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.participant.face.ParticipantService;
import service.participant.impl.ParticipantServiceImpl;

/**
 * Servlet implementation class ParticipantCertificationWriteController
 */
@WebServlet("/participant/certification/write")
public class ParticipantCertificationWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantService participantService = new ParticipantServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();		
		
		int chNo = (Integer)session.getAttribute("chNo"); //세션에 있는 chNo을 가져온다
		
		
		String title = participantService.getTitle(chNo); //chNo을 통한 제목 반환
		
		String chWay = participantService.getChway(chNo); //chNo을 통한 인증 방법 반환
		
		req.setAttribute("title", title);
		req.setAttribute("chWay", chWay);
		
		req.getRequestDispatcher("/WEB-INF/views/participantCertification/write.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		participantService.certicicationWrite(req);
		
		HttpSession session = req.getSession();
		int chNo = (Integer)session.getAttribute("chNo"); //세션에 있는 chNo을 가져온다
		
		resp.sendRedirect("/participant/certification/list?chNo="+chNo);
	}
}
