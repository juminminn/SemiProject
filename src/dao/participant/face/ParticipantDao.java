package dao.participant.face;

import java.sql.Connection;
import java.util.List;

import dto.Certification;
import dto.Participation;
import dto.Payment;
import util.Paging;

public interface ParticipantDao {
	/**
	 *다음 참가 번호 가져오기
	 * 
	 *
	 *@return int - 다음 참가 번호 반환
	 */
	public int selectPaNo(Connection conn);
	
	/**
	 *다음 인증 번호 가져오기
	 * 
	 *
	 *@return int - 다음 인증 번호 반환
	 */
	public int selectCeNo(Connection conn);
	/**
	 * 현재 참가 번호 가져오기
	 * 
	 * @return int - 현재 참가 번호 가져오기
	 */
	
	public int selectByPaNo(Connection conn, int chNo, int uNo);
	/**
	 * 챌린지 제목 얻어오기
	 * 
	 * @return String - 챌린지 이름 가져오기
	 */
	
	public String selectByTitle(Connection conn, int chNo);
	
	/**
	 * 챌린지 인증 방법 얻어오기
	 * 
	 * @return String - 챌린지 이름 가져오기
	 */
	public String selectByChWay(Connection conn, int chNo);

	/**
	 *결제 번호 가져오기
	 * 
	 *
	 *@return int - 챌린지 번호 반환
	 */

	public int selectPaymNo(Connection conn);
	
	/**
	 * 참가자 저장
	 * @param DB에 저장할 객체
	 * @return 저장성공 여부
	 * 
	 */
	public int attendInsert(Connection conn, Participation participation);
	
	/**
	 * 결제 내역 저장 
	 * 
	 * @param DB에 저장할 객체
	 * @return 저장 성공 여부
	 */
	public int paymentInsert(Connection conn, Payment payment);
	
	
	/**
	 *  전체 개수 구하기
	 *  자신의 인증 챌린지 전체 구하기
	 * 
	 * @return 전체 개수 구하기
	 */
	public int selectCntAll(Connection conn, Participation participation);
	
	/**
	 * 자신의 인증 리스트 구하기
	 * 
	 * @param conn - DB연결 객체
	 * @param paging - 페이징 처리
	 * @return certification 자신의 인증 리스트 반환
	 */
	
	public List<Certification> selectAll(Connection conn, Paging paging);
	/**
	 * 인증글 저장
	 * @param DB에 저장할 객체
	 * @return 저장성공 여부
	 * 
	 */
	public int certificationInsert(Connection conn, Certification certification);

	

	
	
	
	
}
