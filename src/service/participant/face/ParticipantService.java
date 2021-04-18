package service.participant.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Certification;
import dto.Challenge;
import dto.Member;
import dto.Participation;
import dto.Payment;
import util.Paging;

public interface ParticipantService {
	
	/**
	 * participation 반환
	 * @param req - 참가자 정보를 가지고 있는 요청 객체
	 * @return 참가자 정보를 반환하는 객체
	 */
	public Payment getPayment(HttpServletRequest req);
	
	
	/**
	 * participation 반환
	 * @param req - 참가자 정보를 가지고 있는 요청 객체
	 * @return 참가자 정보를 반환하는 객체
	 */
	public Participation getParticipation(HttpServletRequest req);
	/***
	 * 참여중인 챌린지 이름 얻어오기
	 * @param req - 챌린지 번호 
	 * @return 제목 반환
	 */
	public String getTitle(HttpServletRequest req);
	
	/***
	 * 참여중인 챌린지 이름 얻어오기
	 * @param int - 챌린지 번호 
	 * @return 제목 반환
	 */
	public String getTitle(int chNo);
	/***
	 * 참여중인 챌린지 인증 방법 얻어오기
	 * @param int - 챌린지 번호 
	 * @return 인증 방법 반환
	 */
	public String getChway(int chNo);
	
	/***
	 * 챌린지 번호 추출
	 * 
	 * @param req - 챌린지 번호
	 * @return 챌린지 번호 
	 */
	
	public int getChallengeno(HttpServletRequest req);
	
	/***
	 * 참여 챌린지 번호 추출
	 * 
	 * @param req - 참여 챌린지 번호
	 * @return 참여 챌린지 번호 
	 */
	public int getParticipationno(HttpServletRequest req);
	
	/***
	 * 인증 번호 추출
	 * 
	 * @param req - 인증 번호
	 * @return 인증 번호 
	 */
	public Certification getCertificationno(HttpServletRequest req);
	/**
	 * 참가자 삽입 
	 * 참가자 삽입하기
	 * @param 참가자 정보를 가지고 있는 객체
	 */
	public void attendWrite(Participation participation);
	
	/**
	 * 결제 내역 삽입 
	 * 결제 삽입하기
	 * @param 결제 정보를 가지고 있는 객체
	 */
	
	public void paymentWrite(Payment payment);
	/**
	 * 인증 페이징 가져오기
	 * 
	 * @param 페이징 정보를 가지고 있는 객체
	 */

	public Paging getCertificaitonPaging(HttpServletRequest req);
	
	/**
	 * 참가자 페이징 가져오기
	 * 
	 * @param 페이징 정보를 가지고 있는 객체
	 */
	public Paging getParticipantPaging(HttpServletRequest req);
	/**
	 * 인증 전체 조회
	 * 	페이징 처리 추가
	 * 
	 * @param paging - 페이징 정보 객체
	 * @return List<Certification> - 자신의 인증 조회 결과 리스트
	 */
	public List<Certification> getList(Paging paging,int paNo);

	/**
	 * 인증 내역 삽입 
	 * 인증 삽입하기
	 * @param 인증 정보를 가지고 있는 객체
	 */
	
	public void certificationWrite(HttpServletRequest req);

	/**
	 * 인증 내역 조회 
	 * 
	 * @param 조회된 인증 정보를 가지고 있는 객체
	 */
	public Certification certificationView(Certification certification);
	
	/**
	 * 인증 내역 수정 
	 * 
	 * @param 수정할 인증 정보를 가지고 있는 객체
	 */

	public void certificationUpdate(HttpServletRequest req);

	/**
	 * 인증 내역 삭제 
	 * 
	 * @param 삭제할 인증 정보를 가지고 있는 객체
	 */

	public void certificationDelete(HttpServletRequest req);

	/***
	 * 
	 * @param paging -참가자 조회
	 * @return 참가자 목록
	 */
	public List<Member> getParticipantList(Paging paging, int chNo);
	

	

}
