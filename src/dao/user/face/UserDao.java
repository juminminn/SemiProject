package dao.user.face;

import java.sql.Connection;
import java.util.List;

import dto.RefundPoint;
import util.Paging;

public interface UserDao {

	
	/**
	 * 조인 결과 전체 개수 구하기
	 * @param conn - 디비 연결 객체
	 * @return 전체개수
	 */
	public int selectCntAll(Connection conn, int uNo);
	
	/**
	 * 페이징 처리
	 * @param conn - DB연결 객체
	 * @param paging - 페이징
	 * @param uNo - 유저번호
	 * @return 페이징 처리된 리스트
	 */
	
	public List<RefundPoint> selectAll(Connection conn, Paging paging, int uNo);
	
	/**
	 * 챌린지 번호
	 * @param conn - DB연결 객체
	 * @param refundPoint - chNo와 uNo
	 * @return refundPoint 반환
	 */
	public RefundPoint selectRefundPoint(Connection connection, RefundPoint refundPoint);

}
