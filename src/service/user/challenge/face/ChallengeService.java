package service.user.challenge.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Challenge;
import dto.ChallengeCategory;

public interface ChallengeService {
	/**
	 * 카테고리 정보 dto 저장하기
	 * 
	 * @param req 카테고리 번호가 담긴 요청객체
	 * @return dto에 저장된 카테고리 번호
	 */
	ChallengeCategory getCategory(HttpServletRequest req);
	/**
	 * 카테고리 정보 불러오기
	 * 
	 * @param number 파라미터로 요청받아 dto에 저장된 카테고리 번호
	 * @return 카테고리 정보
	 */
	ChallengeCategory getCategoryInfo(ChallengeCategory number);
	/**
	 * 카테고리별 신규챌린지 리스트 조회하기
	 * 조건 : 한달 이내 생성된 챌린지만 가져오기
	 * 
	 * @param number 파라미터로 요청받아 dto에 저장된 카테고리 번호
	 * @return 조회된 챌린지 리스트
	 */
	List<Challenge> getNewChallenges(ChallengeCategory number, int curPage);

}
