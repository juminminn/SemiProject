package dao.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import dao.member.face.MemberDao;
import dto.Member;
import dto.Users;

public class MemberDaoImpl implements MemberDao {

	private PreparedStatement ps = null; //SQL수행 객체
	private ResultSet rs = null; //SQL조회 결과 객체
	
	@Override
	public int selectCntMemberByUseridUserpw(Connection conn, Users member) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT count(*) FROM users";
		sql += " WHERE 1=1";
		sql += "	AND u_id = ?";
		sql += "	AND u_password = ?";
		
		//결과 저장할 변수
		int cnt = -1;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, member.getUserId());
			ps.setString(2, member.getUserPw());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return cnt;
	}

	@Override
	public Users selectMemberByUserid(Connection conn, Users member) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM users";
		sql += " WHERE 1=1";
		sql += "	AND u_id = ?";
		
		//조회결과를 저장할 객체
		Users result = null;
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setString(1, member.getUserId());
			
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			
			//조회 결과 처리
			while(rs.next()) {
				result = new Users();
				
				result.setUserId( rs.getString("u_id") );
				result.setUserPw( rs.getString("u_password") );
				result.setGrade(rs.getString("u_grade"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		//최종 결과 반환
		return result;
	}

}
