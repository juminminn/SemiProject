package dao.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.user.face.UserDao;
import dto.Challenge;
import dto.RefundPoint;
import util.Paging;

public class UserDaoImpl implements UserDao {
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntAll(Connection conn, int uNo) {
		String sql = "";
		sql += "SELECT count(*) cnt";
		sql += " from participation P";
		sql += " inner join refunds R";
		sql += " ON p.pa_no = r.pa_no";
		sql += " inner join challenge C";
		sql += " on c.ch_no = p.ch_no";
		sql += " inner join users U";
		sql += " on p.u_no = u.u_no";
		sql += " where pa_is_success = 'N'";
		sql += " and u.u_no = ?";
		//총 실패한 챌린지 개수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uNo);
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
	public List<RefundPoint> selectAll(Connection conn, Paging paging, int uNo) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, C.* FROM (";
		sql += " 		SELECT";
		sql += " 			u.u_no, C.ch_no, ch_title, R.refund_availability, R.refundable_amount,";
		sql += " 			R.payb_refund_holder, payb_refund_bank, payb_refund_account, C.ch_stored_name, C.ch_origin_name";
		sql += " 		from participation P";
		sql += " 		inner join refunds R";
		sql += " 		ON p.pa_no = r.pa_no";
		sql += " 		inner join challenge C";
		sql += " 		on c.ch_no = p.ch_no";
		sql += " 		inner join users U";
		sql += " 		on p.u_no = u.u_no";
		sql += " 		where pa_is_success = 'N'";
		sql += " 		and u.u_no = ?";
		sql += " 		ORDER BY R.re_no DESC";
		sql += "	) C";
		sql += " ) refunds";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List
		List<RefundPoint> list = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, uNo);
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				RefundPoint refundPoint = new RefundPoint();

				//결과값 한 행 처리
				refundPoint.setuNo(rs.getInt("u_no"));
				refundPoint.setChNo(rs.getInt("ch_no"));
				refundPoint.setChTitle(rs.getString("ch_title"));
				refundPoint.setRefundAvailability(rs.getString("refund_availability"));
				refundPoint.setRefundableAmount(rs.getInt("refundable_amount"));
				refundPoint.setPaybRefundHolder(rs.getString("payb_refund_holder"));
				refundPoint.setPaybRefundAccount(rs.getString("payb_refund_account"));
				refundPoint.setPaybRefundBank(rs.getString("payb_refund_bank"));
				refundPoint.setChOriginName(rs.getString("ch_origin_name"));
				refundPoint.setChStoredName(rs.getString("ch_stored_name"));				

				//리스트에 결과값 저장
				list.add(refundPoint);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환
		
		return list;
	}
	@Override
	public RefundPoint selectRefundPoint(Connection conn, RefundPoint refundPoint) {
		String sql = "";
		sql += "SELECT";
		sql += " u.u_no,";
		sql += " C.ch_no,";
		sql += " ch_title,";
		sql += " R.refund_availability,";
		sql += " R.refundable_amount,";
		sql += " R.payb_refund_holder,";
		sql += " payb_refund_bank,";
		sql += " payb_refund_account,";
		sql += " C.ch_stored_name,";
		sql += " C.ch_origin_name";
		sql += " from participation P";
		sql += " inner join refunds R";
		sql += " ON p.pa_no = r.pa_no";
		sql += " inner join challenge C";
		sql += " on c.ch_no = p.ch_no";
		sql += " inner join users U";
		sql += " on p.u_no = u.u_no";
		sql += " where pa_is_success = 'N'";
		sql += " and u.u_no = ?";
		sql += " and c.ch_no = ?";
		
		//결과 저장할 List
		RefundPoint result = null;

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, refundPoint.getuNo());
			ps.setInt(2, refundPoint.getChNo());
			

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			if(rs.next()) {
				result = new RefundPoint();

				//결과값 한 행 처리
				result.setuNo(rs.getInt("u_no"));
				result.setChNo(rs.getInt("ch_no"));
				result.setChTitle(rs.getString("ch_title"));
				result.setRefundAvailability(rs.getString("refund_availability"));
				result.setRefundableAmount(rs.getInt("refundable_amount"));
				result.setPaybRefundHolder(rs.getString("payb_refund_holder"));
				result.setPaybRefundAccount(rs.getString("payb_refund_account"));
				result.setPaybRefundBank(rs.getString("payb_refund_bank"));
				result.setChOriginName(rs.getString("ch_origin_name"));
				result.setChStoredName(rs.getString("ch_stored_name"));				

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return result;
	}
}
