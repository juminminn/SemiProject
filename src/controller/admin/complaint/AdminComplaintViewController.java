package controller.admin.complaint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AdminComplaint;
import service.admin.complaint.face.AdminComplaintService;
import service.admin.complaint.impl.AdminComplaintServiceImpl;

@WebServlet("/admin/complaint/view")
public class AdminComplaintViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ComplaintService 객체 생성
	private AdminComplaintService complaintService = new AdminComplaintServiceImpl();
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("/admin/complaint/view [doGet]");
	
		AdminComplaint comNo = complaintService.getComNo(req); //전달파라미터 얻기 - comNo
			
		AdminComplaint viewComplaint = complaintService.view(comNo); //상세보기 결과 조회
		
		req.setAttribute("chUid", complaintService.getChUid(viewComplaint)); //챌린자개설자 아이디 전달
		
		req.setAttribute("viewComplaint", viewComplaint); //조회결과 model값 전달

		
		//View 지정 및 응답
		req.getRequestDispatcher("/WEB-INF/views/adminComplaint/adminComplaintView.jsp").forward(req, resp);
	
	}
	
}
