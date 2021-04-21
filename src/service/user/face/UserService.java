package service.user.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.RefundPoint;
import util.Paging;

public interface UserService {
	/**
	 * 페이징 구하기
	 * @param req - 페이징 정보
	 * @return 페이징 객체
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 페이징 처리된 리스트 구하기
	 * @param paging - 페이징
	 * @param uNo - 유저번호
	 * @return 페이징 처리된 리스트
	 */
	
	public List<RefundPoint> getList(Paging paging, int uNo);
	
	/**
	 * 유저 번호 추출
	 * @param req - 요청 객체 
	 * @return 유저 번호
	 */
	public int getuNo(HttpServletRequest req);
	
	/**
	 * 조회할 객체 반환
	 * @param uNo - 유저 번호
	 * @param chNo - 챌린지 번호
	 * @return
	 */
	
	public RefundPoint view(int uNo, int chNo);
	
	/**
	 * 챌린지 번호 추출
	 * @param req - 요청 객체
	 * @return 챌린지 번호
	 */
	public int getChNo(HttpServletRequest req);
	

}
