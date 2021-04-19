package dao.user.face;

import java.sql.Connection;
import java.util.List;

import dto.Challenge;
import dto.ChallengeCategory;
import util.Paging;

public interface ChallengeDao {
	/**
	 * 카테고리 번호로 카테고리 전체 정보 조회
	 * 
	 * @param conn - DB접속
	 * @param number - 저장된 카테고리번호
	 * @return - 번호에 해당하는 카테고리 정보
	 */
	ChallengeCategory getCategorybyNum(Connection conn, ChallengeCategory number);
	/**
	 * 카테고리별 신규 챌린지 갯수 조회
	 * 
	 * @param conn - DB접속
	 * @param number - 저장된 카테고리 번호
	 * @return - 신규 챌린지 갯수
	 */
	int getCntnewChallenges(Connection conn, ChallengeCategory number);
	/**
	 * 카테고리별 신규 챌린지 정보 조회
	 *  조건 : 생성날짜가 한달 이내인 챌린지
	 * 
	 * @param conn - DB접속
	 * @param number - 저장된 카테고리번호
	 * @param paging - 보여질 페이지 객체
	 * @return - 신규 챌린지 정보 리스트
	 */
	List<Challenge> newChallengebyCategory(Connection conn, ChallengeCategory number, Paging paging);

}
