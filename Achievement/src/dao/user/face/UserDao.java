package dao.user.face;

import java.sql.Connection;
import java.util.List;

import dto.Users;
import util.Paging;

public interface UserDao {
	/**
	 * 검색한 단어와 맞는 유저목록 조회
	 * 
	 * @param conn - DB접속
	 * @param user - 검색옵션(아이디/닉네임)
	 * @param search - 검색단어
	 * @param paging - 현재 페이지 번호가 담긴 페이징 정보 객체
	 * @return 일치하는 유저목록
	 */
	List<Users> getUserList(Connection conn, String grade, String fieldVal, String search, Paging paging);
	
	/**
	 * 검색한 총 회원 리스트 갯수 가져오기
	 * 회원 테이블에서 검색한 키워드를 가진 리스트를 조회한다
	 * 
	 * @param conn - DB접속
	 * @param grade - 회원등급
	 * @param fieldVal - 검색할 컬럼(아이디/닉네임)
	 * @param search - 검색할 키워드
	 * @return - 조회한 회원데이터 갯수 
	 */
	int selectCntAll(Connection conn, String grade, String fieldVal, String search);
	
	
}
