package service.founder.face;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Certification;
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
	 *chNo을 통하여 개설자 아이디를 반환한다
	 * 
	 * @param int - chNo을 통하여 개설자를 구한다
	 * @return 개설자 ID를 반환한다
	 */
	
	public Map<String, Integer> getuId(int chNo);
	
	
	/***
	 * 페이징과 챌린지 번호로 join된 테이블에 데이터를 map형식으로 받아온다
	 * 
	 * @param paging - 페이징 객체
	 * @param chNo - 챌린지 번호
	 * @return
	 */
	public Map<String, Object> getMap(Paging paging, int chNo);


	/***
	 * 인증 번호를 받아온다
	 * 
	 * @param req - 인증 번호 추출
	 * @return certification - 인증 번호가 담긴 certification을 반환한다
	 */
	
	public Certification getCertificationno(HttpServletRequest req);
	/***
	 * 인증 내역을 받아온다
	 * 
	 * @param certification - 인증 번호
	 * @return certification - 전체 정보가 담기 certification
	 */

	public Certification certificationView(Certification certification);

	/***
	 * 인증 성공 여부를 입력한다
	 * @param req - 인증 성공에 관한 데이터
	 */
	public void whatherUpdate(HttpServletRequest req);

}
