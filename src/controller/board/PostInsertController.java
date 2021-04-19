package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.face.PostService;
import service.board.impl.PostServiceImpl;

/**
 * Servlet implementation class PostInsertController
 */
@WebServlet("/board/write")
public class PostInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImpl();   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String u_nick = postService.getNick(request.getSession());
		
		request.setAttribute("u_nick", u_nick);
		
		
		request.getRequestDispatcher("/WEB-INF/views/post/postWrite.jsp")
			.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//postService.InsertPost(request);
		System.out.println("post도착");
		postService.write(request);
		response.sendRedirect("/board/3");
	}

}
