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
 * Servlet implementation class ParticipantCertificationDeleteController
 */
@WebServlet("/participant/certification/delete")
public class ParticipantCertificationDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ParticipantService participantService = new ParticipantServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		//로그인이 되어있지 않으면 리다이렉트
		if(req.getSession().getAttribute("login")==null) {
			resp.sendRedirect("/");
			return;
		}
		//인증 상태가 없을 경우
		if(req.getSession().getAttribute("ceIsSuccess")==null) {
			resp.sendRedirect("/");
			return;
		}else { 
			//인증 상태가 W가 아닐 경우
			if(!"W".equals(String.valueOf(req.getSession().getAttribute("ceIsSuccess")))){
				resp.sendRedirect("/");
				return;
			}
		}
		participantService.certificationDelete(req);
		
		HttpSession session = req.getSession();
		int chNo = (Integer)session.getAttribute("chNo"); //세션에 있는 chNo을 가져온다
		
		resp.sendRedirect("/participant/certification/list?chNo="+chNo);
	}
}
