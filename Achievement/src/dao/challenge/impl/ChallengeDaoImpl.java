package dao.challenge.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.challenge.face.ChallengeDao;
import dto.ChallengeCategory;

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
		} 
		return category;
	}
}
