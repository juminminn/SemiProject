package dao.participant.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.participant.face.ParticipantDao;
import dto.Certification;
import dto.Challenge;
import dto.Participation;
import dto.Payment;
import util.Paging;

public class ParticipantDaoImpl implements ParticipantDao {
	private PreparedStatement ps= null;
	private ResultSet rs = null;
	
	
	@Override
	public int selectPaNo(Connection conn) {
		String sql="";
		sql += "select participation_seq.nextval";
		sql += " from dual";
		
		int paNo = 0;
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			if(rs.next()) {
				paNo=rs.getInt(1);			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return paNo;
	}
	@Override
	public int selectPaymNo(Connection conn) {
		String sql="";
		sql += "select payment_seq.nextval";
		sql += " from dual";
		
		int paymNo = 0;
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			if(rs.next()) {
				paymNo=rs.getInt(1);			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return paymNo;
	}
	@Override
	public int selectCeNo(Connection conn) {
		String sql="";
		sql += "select certification_seq.nextval";
		sql += " from dual";
		
		int ceNo = 0;
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			if(rs.next()) {
				ceNo=rs.getInt(1);			
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		
		return ceNo;
	}
	
	
	@Override
	public int attendInsert(Connection conn, Participation participation) {
		//참가자 삽입 쿼리
		
		String sql = "";
		sql += "insert into Participation(";
		sql += " pa_no,";
		sql += " ch_no,";
		sql += " u_no,";
		sql += " pa_is_success,";
		sql += " pa_review)";
		sql += "  VALUES(?,?,?,?,?)";
				
		int res = 0;

		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, participation.getPaNo());
			ps.setInt(2, participation.getChNo());
			ps.setInt(3, participation.getuNo());
			ps.setString(4, participation.getPaIsSuccess());
			ps.setString(5, participation.getPaReview());
			
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	@Override
	public int paymentInsert(Connection conn, Payment payment) {
		//결제내역 삽입 쿼리

		String sql = "";
		sql += "insert into payment(";
		sql += " paym_no,";
		sql += " u_no,";
		sql += " ch_no,";
		sql += " paym_name,";
		sql += " paym_amount,";
		sql += " paym_apply_num,";
		sql += " imp_uid,";
		sql += " merchant_uid)";
		sql += "  VALUES(?,?,?,?,?,?,?,?)";

		int res = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, payment.getPaymNo());
			ps.setInt(2, payment.getuNo());
			ps.setInt(3, payment.getChNo());
			ps.setString(4, payment.getPaymName());
			ps.setInt(5, payment.getPaymAmount());
			ps.setString(6, payment.getPaymApplyNum());
			ps.setString(7, payment.getImpUid());
			ps.setString(8, payment.getMerchantUid());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	@Override
	public int certificationInsert(Connection conn, Certification certification) {
		//인증내역 삽입 쿼리

		String sql = "";
		sql += "insert into certification(";
		sql += " ce_no,";
		sql += " pa_no,";
		sql += " ce_origin_name,";
		sql += " ce_stored_name)";
		sql += "  VALUES(?,?,?,?)";

		int res = 0;

		try {

			ps = conn.prepareStatement(sql);
			ps.setInt(1, certification.getCeNo());
			ps.setInt(2, certification.getPaNo());
			ps.setString(3, certification.getCeOriginName());
			ps.setString(4, certification.getCeStoredName());
			res = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}

		return res;
	}
	@Override
	public Certification selectByStoredName(Connection conn, Certification certification) {
		String sql="";
		sql += "select ce_stored_name";
		sql += " from certification";
		sql += " where ce_no =?";
		
		Certification result = null;
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, certification.getCeNo());
			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장
			//조회 결과 처리
			if(rs.next()) {
				result = new Certification();
				result.setCeStoredName(rs.getString("ce_stored_name"));			
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
	
	@Override
	public int selectByPaNo(Connection conn, int chNo, int uNo) {
		//참여 챌린지 번호 구하기
		String sql = "";
		sql += "SELECT * FROM participation";
		sql += " WHERE ch_no=? AND u_no=?";
		
		int paNo=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, chNo);
			ps.setInt(2, uNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				paNo = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return paNo;
	}
	
	@Override
	public String selectByTitle(Connection conn, int chNo) {
		//참여 챌린지 제목 구하기
		String sql = "";
		sql += "SELECT ch_title FROM challenge";
		sql += " WHERE ch_no=?";
		
		String chTitle=null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, chNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				chTitle = rs.getString("ch_title");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return chTitle;
	}
	
	@Override
	public String selectByChWay(Connection conn, int chNo) {
		//참여 챌린지 제목 구하기
		String sql = "";
		sql += "SELECT ch_way FROM challenge";
		sql += " WHERE ch_no=?";

		String chWay=null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, chNo);
			rs = ps.executeQuery();

			while(rs.next()) {
				chWay = rs.getString("ch_way");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return chWay;
	}
	
	@Override
	public int selectCntAll(Connection conn, Participation participation) {
		String sql = "";
		sql += "SELECT count(*) cnt FROM certification";
		sql += " WHERE pa_no=?";
		
		//총 인증글 수
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, participation.getPaNo());
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
	public List<Certification> selectAll(Connection conn, Paging paging) {
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += " 	SELECT rownum rnum, C.* FROM (";
		sql += " 		SELECT";
		sql += " 			ce_no, pa_no, ce_origin_name, ce_stored_name";
		sql += " 			,ce_create_date, ce_is_success";
		sql += " 		FROM certification";
		sql += " 		ORDER BY ce_no DESC";
		sql += "	) C";
		sql += " ) certification";
		sql += " WHERE rnum BETWEEN ? AND ?";

		//결과 저장할 List
		List<Certification> certificationList = new ArrayList<>();

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery(); //SQL 수행 및 결과집합 저장

			//조회 결과 처리
			while(rs.next()) {
				Certification ce = new Certification();
				//결과값 한 행 처리
				ce.setCeNo(rs.getInt("ce_no"));
				ce.setPaNo(rs.getInt("pa_no"));
				ce.setCeOriginName(rs.getString("ce_origin_name"));
				ce.setCeStoredName(rs.getString("ce_stored_name"));
				ce.setCeCreateDate(rs.getDate("ce_create_date"));
				ce.setCeIsSuccess(rs.getString("ce_is_success"));
				//리스트에 결과값 저장
				certificationList.add(ce);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		//최종 결과 반환

		return certificationList;
	}
	@Override
	public Certification selectCertification(Connection conn, Certification certification) {
		//인증 조회하기
		String sql = "";
		sql += "SELECT * FROM certification";
		sql += " WHERE ce_no=?";

		Certification result = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, certification.getCeNo());
			rs = ps.executeQuery();

			while(rs.next()) {
				result = new Certification();
				result.setCeNo(rs.getInt("ce_no"));
				result.setCeIsSuccess(rs.getString("ce_is_success"));
				result.setPaNo(rs.getInt("pa_no"));
				result.setCeCreateDate(rs.getDate("ce_create_date"));
				result.setCeUpdateDate(rs.getDate("ce_update_date"));
				result.setCeOriginName(rs.getString("ce_origin_name"));
				result.setCeStoredName(rs.getString("ce_stored_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}

		return result;
	}
	@Override
	public int certificationUpdate(Connection conn, Certification certification) {
		String sql="";
		sql+="UPDATE certification SET";
		sql+=" ce_origin_name=?,";
		sql+=" ce_stored_name=?,";
		sql+=" ce_update_date=sysdate";
		sql+=" WHERE ce_no=?";
		
		int res = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, certification.getCeOriginName());
			ps.setString(2, certification.getCeStoredName());
			ps.setInt(3, certification.getCeNo());
			res = ps.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
	}
	@Override
	public int certificationDelete(Connection conn, Certification certification) {
		//인증 삭제
		String sql="";
		sql+="DELETE FROM certification";
		sql+=" WHERE ce_no=?";
		
		int res = -1;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, certification.getCeNo());
			res = ps.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		
		return res;
		
	}
}
