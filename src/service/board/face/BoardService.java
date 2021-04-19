package service.board.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Board;
import util.Paging;

public interface BoardService {
	public List<Board> Select(Paging paging, int bno);
	public List<Board> cSearch(Paging paging, int bno, String word);
	public List<Board> pSearch(Paging paging, int bno, String word);
	public Paging getPaging(HttpServletRequest req, int bno);
}
