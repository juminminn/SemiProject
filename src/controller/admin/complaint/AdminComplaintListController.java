package controller.admin.complaint;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AdminComplaint;
import service.admin.complaint.face.AdminComplaintService;
import service.admin.complaint.impl.AdminComplaintServiceImpl;
import util.ComplaintPaging;

@WebServlet("/admin/complaint/list")
public class AdminComplaintListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

		//ComplaintService 객체 생성
		public AdminComplaintService complaintService = new AdminComplaintServiceImpl();
	
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//System.out.println("/admin/complaint/list [doGet]");
			
			//요청파라미터를 전달하여 Paging객체 생성하기
			ComplaintPaging complaintPaging = complaintService.getComplaintPaging(req);
			System.out.println("ComplaintListController - " + complaintPaging);
			
			//페이징 적용한 신고글 조회
			List<AdminComplaint> complaintList = complaintService.getList(complaintPaging);
			
			//페이징 객체를 model값으로 전달
			req.setAttribute("complaintPaging", complaintPaging);
			
			//조회결과 model값 전달
			req.setAttribute("complaintList", complaintList);
			
			//View 지정 및 응답
			req.getRequestDispatcher("/WEB-INF/views/complaint/complaintList.jsp").forward(req, resp);
		
		}
	
}
