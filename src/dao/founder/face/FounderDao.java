package dao.founder.face;

import java.sql.Connection;
import java.util.Map;

import dto.Certification;
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
	
	
	/***
	 * chNo을 통하여 개설자 아이디를 반환한다
	 * 
	 * @param connection - DB연결 객체
	 * @param chNo - 챌린지 번호
	 * @return 개설자 아이디
	 */
	public Map<String,Integer> selectByUno(Connection conn, int chNo);
	
	/***
	 * 인증 내역 조회
	 * 
	 * @param connection - DB연결 객체 
	 * @param certification - 인증 번호가 담겨져 있는 객체
	 * @return 조회된 인증
	 */
	public Certification selectCertification(Connection conn, Certification certification);
	
	/***
	 * 인증 수정
	 * 
	 * @param conn - DB연결 객체
	 * @param certification - 인증 정보를 수정할 데이터가 담겨져 있는 객체
	 * @return 인증 수정 여부
	 */
	public int whatherUpdate(Connection conn, Certification certification);

}
