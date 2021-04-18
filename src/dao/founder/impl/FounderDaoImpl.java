package dao.founder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.JDBCTemplate;
import dao.founder.face.FounderDao;
import dto.Certification;
import dto.Challenge;
import dto.Member;
import util.Paging;

public class FounderDaoImpl implements FounderDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntAll(Connection conn, Challenge challenge) {
		//sql 쿼리문
		String sql = "";
		sql += "select count(*) as cnt from participation P";
		sql += " inner join certification C";
		sql += " on P.pa_no = C.pa_no";
		sql += " where ch_no = ?";
		
		//총 인증글 수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1,challenge.getChNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return cnt;
	}
	@Override
	public Map<String, Object> selectAll(Connection conn, Paging paging, int chNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, PCU.* FROM (";
		sql += " 		SELECT";
		sql += " 			ce_no, u_id, u_name, u_nick, ce_is_success";
		sql += " 		from participation P";
		sql += "		inner join certification C";
		sql += " 		on P.pa_no = C.pa_no";
		sql += " 		inner join users U";
		sql += " 		on U.u_no = P.u_no";
		sql += " 		where ch_no = ?";
		sql += " 		ORDER BY DECODE(ce_is_success,'W',1),ce_no DESC";
		sql += "	) PCU";
		sql += " ) participation";
		sql += " WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 List
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Certification> certificationList = new ArrayList<>();
		List<Member> memberList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, chNo);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				Certification ce = new Certification();
				Member mem = new Member();
				//결과값 한 행 처리
				mem.setUid(rs.getString("u_id"));
				mem.setUsername(rs.getString("u_name"));
				mem.setNick(rs.getString("u_nick"));
				ce.setCeNo(rs.getInt("ce_no"));
				ce.setCeIsSuccess(rs.getString("ce_is_success"));
				//리스트에 결과값 저장
				certificationList.add(ce);
				memberList.add(mem);
			}
			result.put("memberList", memberList);
			result.put("certificationList", certificationList);
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
