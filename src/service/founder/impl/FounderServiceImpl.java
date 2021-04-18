package service.founder.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.founder.face.FounderDao;
import dao.founder.impl.FounderDaoImpl;
import dto.Challenge;
import service.founder.face.FounderService;
import util.Paging;

public class FounderServiceImpl implements FounderService {
	private FounderDao founderDao = new FounderDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		Challenge challenge = null;

		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		if(req.getSession().getAttribute("chNo") !=null) {
			challenge = new Challenge();
			int chNo=(Integer)req.getSession().getAttribute("chNo");
			challenge.setChNo(chNo);
		}


		//participation 테이블의 참가자 수를 조회한다
		int totalCount = founderDao.selectCntAll(JDBCTemplate.getConnection(),challenge);
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}
	@Override
	public int getChallengeno(HttpServletRequest req) {
		int chNo =0; 
		//chNo을 세션에서 구한다
		if(req.getSession().getAttribute("chNo")!=null) {
				chNo = (Integer)req.getSession().getAttribute("chNo");
		}
			
		return chNo;
	}
	@Override
	public Map<String, Object> getMap(Paging paging, int chNo) {
		
		return founderDao.selectAll(JDBCTemplate.getConnection(), paging, chNo);
	}
}
