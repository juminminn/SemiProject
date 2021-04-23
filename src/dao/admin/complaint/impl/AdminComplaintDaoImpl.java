package dao.admin.complaint.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import dao.admin.complaint.face.AdminComplaintDao;
import dto.AdminComplaint;
import util.AdminComplaintPaging;


public class AdminComplaintDaoImpl implements AdminComplaintDao {

	private PreparedStatement ps = null;	//SQL수행 객체
	private ResultSet rs = null;			//SQL조회 결과 객체
	
	
	//-----------------신고 목록(페이징 적용)------------------
	@Override
	public List<AdminComplaint> selectAll(Connection conn, AdminComplaintPaging complaintPaging) {
		
		//SQL 작성
		String sql = "";
		sql += "SELECT * FROM (";
		sql += "	SELECT rownum rnum, COM.* FROM (";
		sql += "		SELECT com_no, com_date, ch_title, ca_title, com_content, ch_caution, com_manage";
		sql += "			FROM complaint com join challenge ch";
		sql += "       		 on com.ch_no = ch.ch_no join category ca";
		sql += "       		 on ch.ca_no = ca.ca_no";
		sql += "			ORDER BY com_no DESC";
		sql += "		) COM";
		sql += " 	) COMPLAINT";
		sql += "  WHERE rnum BETWEEN ? AND ?";
		
		//결과 저장할 complaintList
		List<AdminComplaint> complaintList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, complaintPaging.getStartNo()); //페이지 시작번호
			ps.setInt(2, complaintPaging.getEndNo());	//페이지 끝번호
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			//조회결과 처리
			while(rs.next()) {
				AdminComplaint listComplaint = new AdminComplaint(); //결과값 저장 객체
			
				//결과값 한 행 처리
				listComplaint.setComNo(rs.getInt("com_no"));
				listComplaint.setComDate(rs.getDate("com_date"));
				listComplaint.setChTitle(rs.getString("ch_title"));
				listComplaint.setCaTitle(rs.getString("ca_title"));
				listComplaint.setComContent(rs.getString("com_content"));
				listComplaint.setChCaution(rs.getInt("ch_caution"));
				listComplaint.setComManage(rs.getString("com_manage"));
				
				//리스트에 결과값 저장
				complaintList.add(listComplaint);
			}
		} catch (SQLException e) {
			System.out.println("list.SQLException e: "+e);
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//최종 결과 반환
		return complaintList;
	}
	
	
	//-----------------총 신고글 수------------------
	@Override
	public int selectCountAll(Connection conn) {
		
		String sql = "";
		sql += "SELECT count(*) count FROM complaint";
		
		int count = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("selectCount SQLException e: "+e);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return count;
	}
	
	
	//-----------------신고 상세조회------------------
	@Override
	public AdminComplaint selectComplaintByComNo(Connection conn, AdminComplaint comNo) {
		
		//SQL작성
		String sql = "";
		sql += 
		sql += "SELECT com_no, com_date, ch_title, ca_title, com_content, ch_caution, com_manage";
		sql += "       ,ch_title, ca_title, ch_content, ch_create_date, ch_start_date, ch_end_date";
		sql += "       , ch_money, cec_title, ch_start_time, ch_end_time, ch_way, ch_caution";
		sql += "       , u_id, u_name, com_date, com_content, com_admin_content, com_manage";
		sql += "   FROM complaint com join challenge ch";
		sql += "    on com.ch_no = ch.ch_no join category ca";
		sql += "    on ch.ca_no = ca.ca_no join certification_cycle cec";
		sql += "    on ch.cec_no = cec.cec_no join users u";
		sql += "  	on com.u_no = u.u_no";
		sql += " 	WHERE com_no = ?";
		
		AdminComplaint viewComplaint = null; 	//결과 저장할 viewComplaint 객체
		
		
		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			
			ps.setInt(1, comNo.getComNo());	//조회할 신고글번호(comNo) 적용
			
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장
			
			//조회결과 처리
			while(rs.next()) {
				viewComplaint = new AdminComplaint();	//결과값 저장 객체

				//결과값 한 행 처리
				viewComplaint.setComNo(rs.getInt("com_no"));
				viewComplaint.setChTitle(rs.getString("ch_title"));
				viewComplaint.setCaTitle(rs.getString("ca_title"));
				viewComplaint.setChContent(rs.getString("ch_content"));
				viewComplaint.setChCreateDate(rs.getDate("ch_create_date"));
				viewComplaint.setChStartDate(rs.getDate("ch_start_date"));
				viewComplaint.setChEndDate(rs.getDate("ch_end_date"));
				viewComplaint.setChMoney(rs.getInt("ch_money"));
				viewComplaint.setCecTitle(rs.getString("cec_title"));
				viewComplaint.setChStartTime(rs.getTime("ch_start_time"));
				viewComplaint.setChEndTime(rs.getTime("ch_end_time"));
				viewComplaint.setChWay(rs.getString("ch_way"));
				viewComplaint.setChCaution(rs.getInt("ch_caution"));
				viewComplaint.setComUid(rs.getString("u_id"));
				viewComplaint.setComUname(rs.getString("u_name"));
				viewComplaint.setComDate(rs.getDate("com_date"));
				viewComplaint.setComContent(rs.getString("com_content"));
				viewComplaint.setComAdminContent(rs.getString("com_admin_content"));
				viewComplaint.setComManage(rs.getString("com_manage"));
	
			}
		} catch (SQLException e) {
			System.out.println("viewComplaint SQLException e: "+e);
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		//최종결과 반환
		return viewComplaint;
	}
	
	
	//-----------챌린지개설자 아이디 조회-------
	@Override
	public String selectChUidByChNo(Connection conn, AdminComplaint viewComplaint) {
		
		//SQL작성
		String sql = "";
		sql += "SELECT u_id FROM users u JOIN challenge ch";
		sql += "	ON u.u_no = ch.u_no JOIN complaint com ON ch.ch_no = com.ch_no";
		sql += " where com_no = ?";
		
		String chUid = null;  //결과 저장할 String 변수

		try {
			ps = conn.prepareStatement(sql); //SQL수행 객체
			ps.setInt(1, viewComplaint.getChNo());  //조회할 chNo 적용
				
			rs = ps.executeQuery(); //SQL수행 및 결과집합 저장	
				
			//조회결과
			while(rs.next()) {
				chUid = rs.getString("u_id");
			}	
		
		} catch (SQLException e) {
			System.out.println("selectChUid SQLException e:" +e);
			e.printStackTrace();
		} finally {
			//DB객체 닫기
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return chUid;
	}
	
	
	//----------------신고 등록-----------------------
	@Override
	public int insert(Connection conn, AdminComplaint complaint) {
	
		String sql = "";
		sql += "INSERT INTO complaint(com_no, u_no, ch_no, com_content, com_admin_content, com_manage)";
		sql += " VALUES(complaint_seq.nextval., ?, ?, ?, ?, ?)";
		
		int result = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, complaint.getComUno());
			ps.setInt(2, complaint.getChNo());
			ps.setString(3, complaint.getComContent());
			ps.setString(4, complaint.getComAdminContent());
			ps.setString(5, complaint.getComManage());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("insert SQLException e:" +e);
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
			
	}
	

	
	
	//-----------------신고 수정------------------
	@Override
	public int update(Connection conn, AdminComplaint complaint) {
		
		//수정할 글번호 조회 쿼리
		String sql = "";
		sql += "UPDATE complaint SET com_admin_content = ?,	com_manage = ? WHERE com_no = ?";
		
		//DB객체
		PreparedStatement ps = null;
		int result = -1;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, complaint.getComAdminContent());
			ps.setString(2, complaint.getComManage());
			ps.setInt(3, complaint.getComNo());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
				try {
					if(ps!=null)	ps.close(); //DB객체 닫기
					
				} catch (SQLException e) {
					System.out.println("update SQLException e: "+e);
					e.printStackTrace();
				}
		}
		return result;
	}
	
	
	//-----------------신고 삭제------------------
	@Override
	public int delete(Connection conn, AdminComplaint complaint) {
		
		//삭제할 글번호 조회 쿼리
		String sql = "";
		sql += "DELETE complaint WHERE com_no = ?";
	
		//DB객체
		PreparedStatement ps = null;
		int result = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, complaint.getComNo());
			
			result = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
				try {
					if(ps!=null)	ps.close(); //DB객체 닫기
					
				} catch (SQLException e) {
					System.out.println("delete SQLException e: "+e);
					e.printStackTrace();
				}
		}
		return result;
		
	}

	
}
