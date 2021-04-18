package dao.member.face;

import java.sql.Connection;

import dto.Member;
import dto.Users;

public interface MemberDao {
	/**
	 * uid 와 upassword가 일치하는 회원의 수 조회
	 *
	 *@param conn - DB연결 객체
	 *@param member - 조회할 회원의 정보
	 *@return int - 1(존재하는 회원), 0(존재하지 않은 회원), -1(에러)
	 */
	public int selectCntMemberByUseridUserpw(Connection conn, Users mem);
	
	/**
	 * uid를 이용해 회원정보 조회
	 * 
	 * @param conn - DB연결 객체
	 * @param mem - 조회할 회원
	 * @return member - 조회 객체
	 */
	
	public Users selectMemberByUserid(Connection conn, Users mem);
}
