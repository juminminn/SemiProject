package dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.user.face.ChallengeDao;
import dto.Challenge;
import dto.ChallengeCategory;
import util.Paging;

public class ChallengeDaoImpl implements ChallengeDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	@Override
	public ChallengeCategory getCategorybyNum(Connection conn, ChallengeCategory number) {
		String sql = "";
		sql += "SELECT ca_no, ca_title FROM category WHERE ca_no = ?";
		
		ChallengeCategory category = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, number.getCategoryNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				category = new ChallengeCategory();
				category.setTitle(rs.getString("ca_title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return category;
	}
	@Override
	public int getCntnewChallenges(Connection conn, ChallengeCategory number) {
		String sql = "";
		sql += "SELECT count(*) FROM challenge "
				+ "WHERE months_between(sysdate, ch_create_date) <= 1 "
				+ "and ca_no = ? ";
		//결과를 저장할 int변수
		int challenges = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, number.getCategoryNo());
			
			rs = ps.executeQuery();
			while(rs.next()) {
			challenges = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return challenges;
	}
	@Override
	public List<Challenge> newChallengebyCategory(Connection conn, ChallengeCategory number, Paging paging) {
		String sql = "";
		sql = "SELECT * FROM( " + 
				"SELECT ROWNUM rnum, C.* FROM( " + 
				"SELECT ch_no, ch_title, ch_start_date, ch_end_date, ch_likes, ch_origin_name, ch_stored_name " + 
				"FROM challenge " + 
				"where months_between(sysdate, ch_create_date) <= 1  " + 
				"and ca_no = ?  " + 
				"ORDER BY ch_no DESC)C " + 
				")CHALLENGE " + 
				"WHERE rnum between ? and ?";
		
		
		//결과 저장할 dto객체 생성
		Challenge challenge = null;
		//반환할 list객체 생성
		List<Challenge> list = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, number.getCategoryNo()); //카테고리 번호
			ps.setInt(2, paging.getStartNo()); //페이징 처음번호
			ps.setInt(3, paging.getEndNo()); //페이징 끝번호
			
			rs = ps.executeQuery();
			while(rs.next()) {
				challenge = new Challenge();
				
				challenge.setChNo(rs.getInt("ch_no"));
				challenge.setChTitle(rs.getString("ch_title"));
				challenge.setChStartDate(rs.getDate("ch_start_date"));
				challenge.setChEndDate(rs.getDate("ch_end_date"));
				challenge.setChLikes(rs.getInt("ch_likes"));
				challenge.setChOriginName(rs.getString("ch_origin_name"));
				challenge.setChStoredName(rs.getString("ch_stored_name"));
				
				list.add(challenge);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return list;
	}
}
