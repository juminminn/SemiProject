package service.member.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.member.face.MemberDao;
import dao.member.impl.MemberDaoImpl;
import dto.Member;
import dto.Users;
import service.member.face.MemberService;

public class MemberServiceImpl implements MemberService{
	
	private MemberDao memberDao = new MemberDaoImpl();
	@Override
	public Users getLoginMember(HttpServletRequest req) {
		Users member = new Users();
		
		member.setUserId(req.getParameter("userid"));
		member.setUserPw(req.getParameter("userpwd"));
		
		return member;
	}
	
	@Override
	public boolean login(Users mem) {
		if(memberDao.selectCntMemberByUseridUserpw(JDBCTemplate.getConnection(), mem)>0) {
			return true; //로그인 성공
		}else {
			return false; //로그인 실패
		}
	}
	@Override
	public Users info(Users mem) {
		//조회된  정보
		return memberDao.selectMemberByUserid(JDBCTemplate.getConnection(), mem);
	}
	
}
