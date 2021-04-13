package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Users;
import service.user.face.UserService;
import service.user.impl.UserServiceImpl;
import util.Paging;

@WebServlet("/admin/user/list")
public class AdminUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/user/list - [GET]");
		
		//회원 검색하기, 요청한 회원목록 반환		
		List<Users> user = userService.searchUser(req);
		
		
		req.setAttribute("userlist", user);
		
		req.getRequestDispatcher("/WEB-INF/views/admin/userlist.jsp").forward(req, resp);
	}
}
