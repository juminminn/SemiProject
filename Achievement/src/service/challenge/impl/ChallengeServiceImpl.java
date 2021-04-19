package service.challenge.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.challenge.face.ChallengeDao;
import dao.challenge.impl.ChallengeDaoImpl;
import dto.ChallengeCategory;
import service.challenge.face.ChallengeService;

public class ChallengeServiceImpl implements ChallengeService {
	private ChallengeDao challengeDao = new ChallengeDaoImpl();
	@Override
	public ChallengeCategory getCategory(HttpServletRequest req) {
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
	public ChallengeCategory getCategoryInfo(ChallengeCategory number) {
		Connection conn =  JDBCTemplate.getConnection();
		
		ChallengeCategory info = challengeDao.getCategorybyNum(conn, number);
		return info;
	}
}
