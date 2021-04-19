package service.user.challenge.impl;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.user.face.ChallengeDao;
import dao.user.impl.ChallengeDaoImpl;
import dto.Challenge;
import dto.ChallengeCategory;
import service.user.challenge.face.ChallengeService;
import util.Paging;

public class ChallengeServiceImpl implements ChallengeService {
	private ChallengeDao challengeDao = new ChallengeDaoImpl();
	@Override
	public ChallengeCategory getCategory(HttpServletRequest req) { //dto에 요청받은 카테고리 번호 저장
		String param = req.getParameter("category");
		//System.out.println(param);
		
		ChallengeCategory category = new ChallengeCategory(); 
		int categoryNo = 0;
		if(param!=null && !param.equals("")) {
			categoryNo = Integer.parseInt(param);
			category.setCategoryNo(categoryNo);
		}
		return category;
	}
	@Override
	public ChallengeCategory getCategoryInfo(ChallengeCategory number) { //카테고리 이름 조회하기
		Connection conn =  JDBCTemplate.getConnection();
		
		ChallengeCategory info = challengeDao.getCategorybyNum(conn, number);
		return info;
	}
	@Override
	public List<Challenge> getNewChallenges(ChallengeCategory number, int curPage) {// 신규챌린지 리스트 조회하기
		Connection conn =  JDBCTemplate.getConnection();
		int cntList = challengeDao.getCntnewChallenges(conn, number); //조회된 챌린지 갯수
		int listCount = 4; //보여질 게시글 갯수		
		
		//페이징하기
		Paging paging = new Paging(cntList, curPage, listCount);
		
		List<Challenge>list = challengeDao.newChallengebyCategory(conn, number, paging);
		return list;
	}
}
