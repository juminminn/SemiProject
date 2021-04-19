package service.challenge.face;

import javax.servlet.http.HttpServletRequest;

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
	 * @param number dto에 저장된 카테고리 번호
	 * @return 카테고리 정보
	 */
	ChallengeCategory getCategoryInfo(ChallengeCategory number);

}
