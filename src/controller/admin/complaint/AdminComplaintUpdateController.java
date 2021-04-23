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

@WebServlet("/admin/complaint/update")
public class AdminComplaintUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ComplaintService 객체 생성
	private AdminComplaintService complaintService = new AdminComplaintServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		AdminComplaint comNo = complaintService.getComNo(req); //comNo 가져오기
		
		AdminComplaint updateComplaint = complaintService.view(comNo); //상세보기 결과 조회
		
		req.setAttribute("updateComplaint", updateComplaint); //조회결과 model값 전달
		
		req.getRequestDispatcher("/WEB-INF/views/adminComplaint/adminComplaintUpdate.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		complaintService.update(req); //수정글 등록
		
		resp.sendRedirect("/admin/complaint/list"); //목록으로 리다이렉션
	}
	
	
}
