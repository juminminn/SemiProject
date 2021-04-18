package dao.founder.face;

import java.sql.Connection;
import java.util.Map;

import dto.Challenge;
import util.Paging;

public interface FounderDao {
	/***
	 * 인증게시물들을 불러온다
	 * 
	 * @param conn DB연결 객체
	 * @param challenge - chNo가 담겨져 있는 객체
	 * @return 총 인증 수
	 */
	public int selectCntAll(Connection conn, Challenge challenge);
	/***
	 * 
	 * @param connection DB연결 객체
	 * @param paging
	 * @param chNo - 데이터
	 * @return member와 certification을 받아온다
	 */
	public Map<String, Object> selectAll(Connection conn, Paging paging, int chNo);

}
