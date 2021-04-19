package controller.founder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Certification;
import dto.Member;
import service.founder.face.FounderService;
import service.founder.impl.FounderServiceImpl;
import util.Paging;

/**
 * Servlet implementation class UserFounderCertificationList
 */
@WebServlet("/founder/certification/list")
public class FounderCertificationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FounderService founderService = new FounderServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인이 되어있지 않으면 리다이렉트
		if(req.getSession().getAttribute("login")==null) {
			resp.sendRedirect("/");
			return;
		}
		
		Paging paging = null;
		paging = founderService.getPaging(req);
		//chNo 가져오기
		int chNo = founderService.getChallengeno(req);
		Map<String, Object> map = founderService.getMap(paging,chNo);
		
		
		//객체 저장
		req.setAttribute("result", map);
		req.setAttribute("paging", paging);
		
		
		HttpSession session = req.getSession();
		
		
		if(session.getAttribute("u_grade")!=null) {
			if("M".equals(String.valueOf(session.getAttribute("u_grade")))) {
				req.getRequestDispatcher("/WEB-INF/views/adminFounderCertification/list.jsp")
					.forward(req, resp);
			}else {
				req.getRequestDispatcher("/WEB-INF/views/userFounderCertification/list.jsp")
					.forward(req, resp);
			}
			return;
		}
		resp.sendRedirect("/");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}
}
