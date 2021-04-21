package service.user.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.user.face.UserDao;
import dao.user.impl.UserDaoImpl;
import dto.RefundPoint;
import service.user.face.UserService;
import util.BankCode;
import util.Paging;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();
	@Override
	public Paging getPaging(HttpServletRequest req) { //페이징 구하기
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		int uNo = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		if(req.getSession().getAttribute("u_no")!=null) {
			uNo = (Integer)req.getSession().getAttribute("u_no");
		}
		//challenge 테이블의 총 챌린지 수를 조회한다
		int totalCount = userDao.selectCntAll(JDBCTemplate.getConnection(), uNo);

		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}
	@Override
	public List<RefundPoint> getList(Paging paging, int uNo) {
		List<RefundPoint> list = userDao.selectAll(JDBCTemplate.getConnection(), paging, uNo);
		BankCode bankCode = new BankCode();
		for(int i=0; i<list.size(); i++) {
			String bank=bankCode.getBankTable().get(list.get(i).getPaybRefundBank());
			list.get(i).setPaybRefundBank(bank); //code to name
		}
		
		return list;
	}
	@Override
	public int getuNo(HttpServletRequest req) {
		//유저 번호 추출
		String param = req.getParameter("uNo");
		int uNo = 0;
		if(param != null && !"".equals(param)) {
			uNo = Integer.parseInt(param);
		}
		if(req.getSession().getAttribute("u_no")!=null) {
			uNo= (Integer)req.getSession().getAttribute("u_no");
		}
		
		return uNo;
	}
	
	@Override
	public int getChNo(HttpServletRequest req) {
		//챌린지 번호
		String param = req.getParameter("chNo");
		int chNo = 0;
		if(param != null && !"".equals(param)) {
			chNo = Integer.parseInt(param);
		}
		
		return chNo;
	}
	@Override
	public RefundPoint view(int uNo, int chNo) {
		RefundPoint refundPoint = new RefundPoint();
		refundPoint.setChNo(chNo); //챌린지 번호
		refundPoint.setuNo(uNo); //유저 번호
		BankCode bankCode = new BankCode();
		refundPoint = userDao.selectRefundPoint(JDBCTemplate.getConnection(), refundPoint);
		String bank=bankCode.getBankTable().get(refundPoint.getPaybRefundBank());
		refundPoint.setPaybRefundBank(bank); //code to name
		
		
		return refundPoint;
	}
}
