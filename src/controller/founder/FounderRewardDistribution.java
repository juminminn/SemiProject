package controller.founder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Participation;
import dto.Refunds;
import service.founder.face.FounderService;
import service.founder.impl.FounderServiceImpl;

/**
 * Servlet implementation class FounderRewardDistribution
 */
@WebServlet("/founder/reward/distribution")
public class FounderRewardDistribution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private FounderService founderService = new FounderServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		//챌린지 번호 조회
		int chNo = founderService.getChallengeno(req);
		//참가자 챌린지 리스트 반환
		List<Integer> paNoList = founderService.getPaNoList(chNo);
		
		//총 인증해야할 횟수 구하기
		int total = founderService.totalCertification(chNo);
		
		//참가자별 인증 성공률 불러오기
		Map<Integer, Double> paRate = founderService.getPaRate(paNoList, total, chNo);
		
		//챌린지 참가 성공
		founderService.participationIsSuccess(paRate);
		
		//포인트 적립
		founderService.pointInsert(paRate);
		//마이페이지에 포인트 및 데이터 업데이트
		founderService.mypageUpdate(paRate);
		
		//최종 환급
		//환불을 위한 토큰 발급
		String token=founderService.refundsToken();
		//성공자들을 반환
		Map<Integer, Participation> paMap=founderService.refundsPaSuccess(paRate);
		//환급자들를 반환
		Map<Integer, Refunds> reMap = founderService.refunder(paMap);
		
		//최종 환급
		founderService.refunds(reMap, token);
		if(req.getSession().getAttribute("u_grade")!=null) { //등급에 따른 분류
			if("M".equals(String.valueOf(req.getSession().getAttribute("u_grade")))) { //관리자
				resp.sendRedirect("/user/challenge/view?chNo="+chNo);
			}else { //사용자
				resp.sendRedirect("/admin/challenge/view?chNo="+chNo);
			}
			return;
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}

