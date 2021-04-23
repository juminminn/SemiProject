package dao.admin.complaint.face;

import java.sql.Connection;
import java.util.List;

import dto.AdminComplaint;
import util.ComplaintPaging;


public interface AdminComplaintDao {
	

	/**
	 * 신고 목록 조회 (페이징 적용)
	 * 
	 * @param conn
	 * @param paging - 페이징 정보 객체
	 * @return List<Complaint> - Complaint 전체 조회 결과 목록
	 */
	public List<AdminComplaint> selectAll(Connection conn, ComplaintPaging complaintPaging);
	
	
	/**
	 * 총 신고글 수 조회
	 * 
	 * @param conn
	 * @return 총 신고글 수
	 */
	public int selectCountAll(Connection conn);
	
	
	/**
	 * 특정 게시글 조회
	 * 
	 * @param conn
	 * @param comNo - 조회할 comNo를 가진 객체
	 * @return Complaint - 조회된 결과 객체
	 */
	public AdminComplaint selectComplaintByComNo(Connection conn, AdminComplaint comNo);
	
	
	/**
	 * 챌린지번호(chNo)를 이용해 챌린지개설자 아이디(chUid)를 조회
	 * 
	 * @param conn
	 * @param complaint - 조회할 chNo를 가진 객체
	 * @return String chUid - 챌린지개설자 아이디
	 */
	public String selectChUidByChNo(Connection conn, AdminComplaint viewComplaint);
	
	
	/**
	 * 신고 등록
	 * 
	 * @param conn
	 * @param complaint
	 * @return
	 */
	public int insert(Connection conn, AdminComplaint complaint);
	
	
	/**
	 * 신고글 수정
	 * 
	 * @param conn
	 * @param complaint - 수정할 내용을 담은 객체
	 * @return
	 */
	public int update(Connection conn, AdminComplaint complaint);
	
	
	/**
	 * 신고글 삭제
	 * 
	 * @param conn 
	 * @param complaint - 삭제할 신고글번호를 담은 객체
	 * @return
	 */
	public int delete(Connection conn, AdminComplaint complaint);
	

	
}	