package service.user.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.user.face.UserDao;
import dao.user.impl.UserDaoImpl;
import dto.Users;
import service.user.face.UserService;
import util.Paging;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();
	@Override
	public List<Users> searchUser(HttpServletRequest req) {
		
		
		//검색 파라미터 저장할 변수 생성
		String isUser = req.getParameter("grade");
		String field = req.getParameter("field");
		String param = req.getParameter("search");
		String page = req.getParameter("curPage"); 
		
		//등급정보
		String grade = "U";
		if(isUser!=null && !isUser.equals("")){ grade = isUser;}
		//아이디 정보
		String fieldVal = "u_id";
		if(field!=null && !field.equals("")) {fieldVal = field;}
		//검색한 단어
		String search = "";
		if(param!=null && !param.equals("")) {search = param;}
		//현재페이지 정보
		int curPage = 0;
		if(page!=null && !page.equals("")) {curPage = Integer.parseInt(page);}
		
		
		
		
		//DB연결하기
		Connection conn = JDBCTemplate.getConnection();
		
		//검색한 총 회원 리스트 갯수 가져오기
		int cntAll = userDao.selectCntAll(conn, grade, fieldVal, search);
		
		//Paging 객체 생성
		Paging paging =  new Paging(cntAll, curPage);
		
		//페이징을 적용한 회원 리스트 가져오기
		List<Users> userlist = userDao.getUserList(conn, grade, fieldVal, search, paging);
		
//		System.out.println(cntAll);
//		for(Users users : userlist) System.out.println(users);
		
		req.setAttribute("paging", paging);
		
		return userlist;
	}

}
