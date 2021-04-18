package dao.challenge.face;

import java.sql.Connection;

import dto.ChallengeCategory;

public interface ChallengeDao {
	/**
	 * 카테고리 번호로 카테고리 전체 정보 조회
	 * 
	 * @param conn - DB접속
	 * @param number - 저장된 카테고리번호
	 * @return - 번호에 해당하는 카테고리 정보
	 */
	ChallengeCategory getCategorybyNum(Connection conn, ChallengeCategory number);

}
