package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Post;
import service.board.face.PostService;
import service.board.impl.PostServiceImpl;

/**
 * Servlet implementation class PostUpdateController
 */
@WebServlet("/board/update")
public class PostUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Post post = postService.SelectPost(request);
		String u_nick = postService.getNick(request.getSession());
		
		request.setAttribute("u_nick", u_nick);
		request.setAttribute("post", post);
		
		request.getRequestDispatcher("/WEB-INF/views/post/postWrite.jsp")
			.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		postService.UpdatePost(request);
		
		response.sendRedirect("/board/3");
	}

}
