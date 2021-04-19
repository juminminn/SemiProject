package service.founder.impl;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.founder.face.FounderDao;
import dao.founder.impl.FounderDaoImpl;
import dto.Certification;
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
	public Map<String, Integer> getuId(int chNo) {
		
		return founderDao.selectByUno(JDBCTemplate.getConnection(), chNo);
	}
	
	@Override
	public Map<String, Object> getMap(Paging paging, int chNo) {
		
		return founderDao.selectAll(JDBCTemplate.getConnection(), paging, chNo);
	}
	@Override
	public Certification getCertificationno(HttpServletRequest req) {
		Certification certification = null;
		
		//객체가 있으면 코드 삽입
		if(req.getParameter("ceNo")!=null && !"".equals(req.getParameter("ceNo"))) {
			certification = new Certification();
			certification.setCeNo(Integer.parseInt(req.getParameter("ceNo")));
		}
		return certification;
	}
	
	@Override
	public Certification certificationView(Certification certification) {
		
		return founderDao.selectCertification(JDBCTemplate.getConnection(), certification);
	}
	
	@Override
	public void whatherUpdate(HttpServletRequest req) {
		int ceNo = Integer.parseInt(req.getParameter("ceNo"));
		String whather = req.getParameter("whather");
		Connection conn = JDBCTemplate.getConnection();
		
		Certification certification = new Certification();
		certification.setCeNo(ceNo);
		certification.setCeIsSuccess(whather);
		
		if(founderDao.whatherUpdate(conn, certification)>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
	}
	
}
