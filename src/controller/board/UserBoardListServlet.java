package controller.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.board.face.BoardService;
import service.board.impl.BoardServiceImpl;
import util.Paging;

/**
 * Servlet implementation class UserBoardListServlet
 */
@WebServlet("/board/2")
public class UserBoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_type = request.getParameter("search_type");
		String keyword = request.getParameter("keyword");
		Paging paging = null;
		
		String[] boardData = boardService.BoardData(2);
		
		List<Board> boardList = new ArrayList<Board>();
		List<Board> MvList = new ArrayList<Board>();
		
		if(search_type == null) {
			paging = boardService.getPaging(request, 2);
			boardList = boardService.Select(paging, 2);
			MvList = boardService.MostViewList(2);
			
		}else if(search_type.equals("title_content")) {
			paging = boardService.pSearchGetPaging(request, 2, keyword);
			boardList = boardService.pSearch(paging, 2, keyword);
			MvList = boardService.MostViewList(2);
			
		}else {
			paging = boardService.cSearchGetPaging(request, 2, keyword);
			boardList = boardService.cSearch(paging, 2, keyword);
			MvList = boardService.MostViewList(2);
			
		}
		
		//페이징 객체 전달
		request.setAttribute("paging", paging);
		
		//게시글 조회결과 전달
		request.setAttribute("boardList", boardList);
		
		//인기글 조회결과 전달
		request.setAttribute("MvList", MvList);
		
		//게시판데이터 전달
		request.setAttribute("boardData", boardData);
		
		request.getRequestDispatcher("/WEB-INF/views/post/Board.jsp")
			.forward(request, response);
	}

}
