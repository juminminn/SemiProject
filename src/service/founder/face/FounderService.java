package service.founder.face;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import util.Paging;

public interface FounderService {
	/***
	 * chNo을 통하여 inner join으로 참여한 챌린지의 인증 페이징을 불러옴 
	 * @param req - 전달 객체 chNo
	 * @return 페이징 객체 
	 */
	public Paging getPaging(HttpServletRequest req);

	
	/***
	 * 세션에서 chNo을 추출한다 
	 * 
	 * @param req - chNo을 구한다
	 * @return chNo을 반환한다
	 */
	public int getChallengeno(HttpServletRequest req);

	/***
	 * 페이징과 챌린지 번호로 join된 테이블에 데이터를 map형식으로 받아온다
	 * 
	 * @param paging - 페이징 객체
	 * @param chNo - 챌린지 번호
	 * @return
	 */
	public Map<String, Object> getMap(Paging paging, int chNo);

}
