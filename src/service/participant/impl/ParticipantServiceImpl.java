package service.participant.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import common.JDBCTemplate;
import dao.participant.face.ParticipantDao;
import dao.participant.impl.ParticipantDaoImpl;
import dto.Certification;
import dto.Challenge;
import dto.Member;
import dto.Participation;
import dto.Payment;
import service.participant.face.ParticipantService;
import util.FileRemove;
import util.Paging;

public class ParticipantServiceImpl implements ParticipantService {
	
	private ParticipantDao participantDao = new ParticipantDaoImpl();
	private FileRemove fileRemove = new FileRemove();
	//저장할 객체
	@Override
	public Participation getParticipation(HttpServletRequest req) {
		int paNo = participantDao.selectPaNo(JDBCTemplate.getConnection());
		int chNo = Integer.parseInt(req.getParameter("chNo"));
		int uNo = (Integer)req.getSession().getAttribute("u_no");
		Participation participation = new Participation();
		participation.setChNo(chNo);
		participation.setPaNo(paNo);
		participation.setuNo(uNo);
		participation.setPaIsSuccess("W");
		participation.setPaReview("없음");
		
		return participation;
	}
	@Override
	public Payment getPayment(HttpServletRequest req) {
		Payment payment = new Payment();
		int paymNo = participantDao.selectPaymNo(JDBCTemplate.getConnection());
		int uNo = (Integer)req.getSession().getAttribute("u_no");
		
		payment.setPaymNo(paymNo);
		payment.setuNo(uNo);
		payment.setImpUid(req.getParameter("imp_uid"));
		payment.setChNo(Integer.parseInt(req.getParameter("chNo")));
		payment.setMerchantUid(req.getParameter("merchant_uid"));
		payment.setPaymApplyNum(req.getParameter("apply_num"));
		payment.setPaymAmount(Integer.parseInt(req.getParameter("paid_amount")));
		payment.setPaymName(req.getParameter("pay_name"));
		
		
		return payment;
	}
	//추출해낼 paNo
	@Override
	public int getParticipationno(HttpServletRequest req) {
		//session에서 값을 가져온다
		int chNo = (Integer)req.getSession().getAttribute("chNo");
		int uNo = (Integer)req.getSession().getAttribute("u_no");
		int paNo = participantDao.selectByPaNo(JDBCTemplate.getConnection(), chNo, uNo);
		return paNo;
	}
	
	//챌린지 타이틀 반환
	@Override
	public String getTitle(HttpServletRequest req) {
		//챌린지 번호
		int chNo = Integer.parseInt(req.getParameter("chNo"));
		String title = participantDao.selectByTitle(JDBCTemplate.getConnection(), chNo);
		
		return title;
	}
	//챌린지 타이틀 반환
	@Override
	public String getTitle(int chNo) {
		String title = participantDao.selectByTitle(JDBCTemplate.getConnection(), chNo);
		return title;
	}
	
	@Override
	public String getChway(int chNo) {
		String chWay = participantDao.selectByChWay(JDBCTemplate.getConnection(), chNo);
		return chWay;
	}
	
	
	@Override
	public int getChallengeno(HttpServletRequest req) {
		
		int chNo = 0;
		if(req.getParameter("chNo")!=null) {
			chNo = Integer.parseInt(req.getParameter("chNo"));
		}else if(req.getSession().getAttribute("chNo")!=null) {
			chNo = (Integer)req.getSession().getAttribute("chNo");
		}
		
		return chNo;
	}
	@Override
	public Certification getCertificationno(HttpServletRequest req) {
		//인증 번호 반환
		String param = req.getParameter("ceNo");
		Certification certification = null;
		if(param!=null && !"".equals(param)) {
			certification= new Certification();
			certification.setCeNo(Integer.parseInt(param));
			
		}
		
		return certification;
	}
	
	@Override
	public Paging getCertificaitonPaging(HttpServletRequest req) {
		//전달파라미터 curPage 파싱
		String param = req.getParameter("curPage");
		
		//챌린지 번호와 유저 번호 구하기
		int chNo = Integer.parseInt(req.getParameter("chNo"));
		int uNo = (Integer)req.getSession().getAttribute("u_no");
		int paNo = participantDao.selectByPaNo(JDBCTemplate.getConnection(), chNo, uNo);
		
		int curPage = 0;
		if(param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}
		//참가 챌린지 번호 생성
		Participation participation = new Participation();
		participation.setPaNo(paNo);
		
		//challenge 테이블의 자신의 인증 수를 조회한다
		int totalCount = participantDao.selectCntAll(JDBCTemplate.getConnection(),participation);

		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage, 3);

		return paging;
	}
	@Override
	public Paging getParticipantPaging(HttpServletRequest req) {
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
		int totalCount = participantDao.selectCntAll(JDBCTemplate.getConnection(),challenge);
		//Paging객체 생성
		Paging paging = new Paging(totalCount, curPage);

		return paging;
	}
	
	@Override
	public Certification certificationView(Certification certification) {
		
		return participantDao.selectCertification(JDBCTemplate.getConnection(), certification);
	}
	@Override
	public List<Certification> getList(Paging paging,int paNo) {
		return participantDao.selectAllCertification(JDBCTemplate.getConnection(), paging,paNo);
	}
	
	@Override
	public List<Member> getParticipantList(Paging paging, int chNo) {
		return  participantDao.selectAllPartification(JDBCTemplate.getConnection(), paging, chNo);
	}
	@Override
	public Participation getLike(HttpServletRequest req) {
		Boolean param = Boolean.parseBoolean(req.getParameter("paLike"));
		//true일때 좋아요 활성화 -> 비활성화
		Participation participation = new Participation();
		if(param) {
			participation.setPaLike("N");
			//false일때 좋아요 비활성화 -> 활성화
		}else {
			participation.setPaLike("Y");
		}
		//좋아요 수를 증감할 챌린지 번호 입력
		if(req.getSession().getAttribute("chNo") !=null) {
			int chNo=(Integer)req.getSession().getAttribute("chNo");
			participation.setChNo(chNo);
		}
		
		return participation;
	}
	@Override
	public void attendWrite(Participation participation) {
		Connection conn = JDBCTemplate.getConnection();
		if( participantDao.attendInsert(conn, participation) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	//증감 챌린지
	@Override
	public void increaseLike(Participation participation) {
		Connection conn = JDBCTemplate.getConnection();
		if(participantDao.increaseChLike(conn, participation)>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	//좋아요 여부
	@Override
	public void updatePaLike(Participation participation) {
		Connection conn = JDBCTemplate.getConnection();
		
		if(participantDao.updatePaLike(conn, participation)>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
	}
	
	@Override
	public void paymentWrite(Payment payment) {
		Connection conn = JDBCTemplate.getConnection();
		if( participantDao.paymentInsert(conn, payment) > 0 ) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
	}
	@Override
	public Map<String, Boolean> getWhethers(HttpServletRequest req) {
		//신고 여부와 좋아요 여부
		Map<String, Boolean> result = new HashMap<>();
		int chNo = (Integer)req.getSession().getAttribute("chNo");
		int uNo = (Integer)req.getSession().getAttribute("u_no");
		
		
		//해당 챌린지의 좋아요 여부를 DB에 조회해서 가져온다(단순 조회)
		boolean paLike =  participantDao.selectByPaLike(JDBCTemplate.getConnection(), chNo, uNo);
		
		//해당 챌린지의 신고여부를 DB에 조회해서 가져온다(신고 테이블에 uNo와 chNo의 값이 존재하면 신고를 한 것)
		boolean paComplaint = participantDao.selectByComplaint(JDBCTemplate.getConnection(), chNo, uNo);
		result.put("paLike", paLike);
		result.put("paComplaint", paComplaint);
		
		return result;
	}
	
	@Override
	public void certificationWrite(HttpServletRequest req) {

		int paNo = (Integer)req.getSession().getAttribute("paNo"); //paNo 가져오기
		//챌린지 정보 저장할 객체
		Certification certification = null;

		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);



		//multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");

			return; //fileupload() 메소드 실행 중지
		}

		//챌린지 정보 저장할 객체 생성
		certification = new Certification();

		//디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//메모리 처리 사이즈 지정
		factory.setSizeThreshold( 1  * 1024 ); //1KB

		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();

		factory.setRepository(repository);

		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax( 10*1024*1024 ); //10MB

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {

			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		//모든 요청 정보 처리하기
		while( iter.hasNext() ) {
			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if( item.getSize() <= 0 )	continue;

			// 3) 파일 처리
			if( !item.isFormField() ) {
				// java.util.Date -> String 변환 - SimpleDateFormat 클래스
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				String rename = sdf.format(new Date());

				//확장자는 원본파일 확장자 얻어오기
				String origin = item.getName(); // 원본파일 이름
				int dotIdx = origin.lastIndexOf("."); //가장 마지막 "."의 인덱스

				//확장자
				String ext = origin.substring(dotIdx + 1);

				//서버에 저장될 파일의 이름
				String stored = rename +"."+ext;

				//						System.out.println("[TEST] 원본 파일명 : "+origin);
				//						System.out.println("[TEST] 저장될 파일명 : "+stored);

				// --- 첨부파일 정보 객체 ---
				certification.setCeOriginName(origin);
				certification.setCeStoredName(stored);
				// --------------------------

				//업로드된 임시파일을 실제로 저장하기
				// -> 1MB이하 파일 : 메모리에 임시파일로 업로드
				// -> 1MB이상 파일 : HDD에 임시파일로 업로드
				File uploadFolder = new File(req.getServletContext().getRealPath("upload")); // 업로드될 폴더 경로

				uploadFolder.mkdir();

				//실제로 저장될 파일
				File up = new File(uploadFolder, stored);

				try {
					item.write(up); //실제 업로드(최종 결과 파일이 생성됨)
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // 파일 처리 end

		} // while( iter.hasNext() ) end - FileItem 반복 처리

		//파일이 선택되지 않았을경우
		if(certification.getCeOriginName()==null || "".equals(certification.getCeOriginName())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
			String rename = sdf.format(new Date());

			File uploadFolder = new File(req.getServletContext().getRealPath("upload")); // 업로드될 폴더 경로
			File defaultImg = new File(req.getServletContext().getRealPath("resources/img/challenge.png")); //이미지 파일 경로

			String origin = defaultImg.getName(); //파일의 본래 이름 challenge.png
			int dotIdx = origin.lastIndexOf("."); //가장 마지막 "."의 인덱스
			String ext = origin.substring(dotIdx + 1); //확장자
			String stored = rename +"."+ext; //저장 파일

			certification.setCeOriginName(origin);
			certification.setCeStoredName(stored);

			uploadFolder.mkdir();

			//실제로 저장될 파일
			File up = new File(uploadFolder.getPath()+"/"+stored);

			try {
				FileInputStream fis = new FileInputStream(defaultImg); //읽을파일
				FileOutputStream fos = new FileOutputStream(up); //복사할파일

				int fileByte = 0; 
				// fis.read()가 -1 이면 파일을 다 읽은것
				while((fileByte = fis.read()) != -1) {
					fos.write(fileByte);
				}

				//자원 사용 종료
				fis.close(); 
				fos.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}



		//DB데이터 입력
		Connection conn = JDBCTemplate.getConnection();
		
		//인증 번호
		int ceNo = participantDao.selectCeNo(conn);
		certification.setCeNo(ceNo);
		
		//참가 챌린지 번호 얻어오기
		certification.setPaNo(paNo);

		//인증 정보가 있을 경우
		if(certification != null) {
			//인증글 삽입
			if( participantDao.certificationInsert(conn, certification) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
		
	} //certificationWrtie End
	
	//수정
	@Override
	public void certificationUpdate(HttpServletRequest req) {
		int paNo = (Integer)req.getSession().getAttribute("paNo"); //paNo 가져오기
		
		//챌린지 정보 저장할 객체
		Certification certification = null;
		
		//파일업로드 형태의 데이터가 맞는지 검사
		boolean isMultipart = false;
		isMultipart = ServletFileUpload.isMultipartContent(req);

		//multipart/form-data 인코딩으로 전송되지 않았을 경우
		if( !isMultipart ) {
			System.out.println("[ERROR] multipart/form-data 형식이 아님");
			return; //fileupload() 메소드 실행 중지
		}
		//인증 정보 저장할 객체 생성
		certification = new Certification();

		//디스트기반 아이템 팩토리
		DiskFileItemFactory factory = new DiskFileItemFactory();

		//메모리 처리 사이즈 지정
		factory.setSizeThreshold( 1  * 1024 ); //1KB

		//임시 저장소 설정
		File repository = new File(req.getServletContext().getRealPath("tmp"));
		repository.mkdir();

		factory.setRepository(repository);

		//파일업로드 객체 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		//업로드 용량 제한
		upload.setFileSizeMax( 10*1024*1024 ); //10MB

		//전달 데이터 파싱
		List<FileItem> items = null;
		try {

			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		//추출된 전달파라미터 처리 반복자
		Iterator<FileItem> iter = items.iterator();

		//모든 요청 정보 처리하기
		while( iter.hasNext() ) {
			FileItem item = iter.next();

			// 1) 빈 파일 처리
			if( item.getSize() <= 0 )	continue;
			
			if( item.isFormField() ) {
				String key = item.getFieldName(); //키 추출
				
				try {
					if( "ceNo".equals(key) ) { //전달파라미터 name이 "ceNo"
						certification.setCeNo(Integer.parseInt(item.getString("UTF-8")));
					} 
				}catch(UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			}
			// 3) 파일 처리
			if( !item.isFormField() ) {
				// java.util.Date -> String 변환 - SimpleDateFormat 클래스
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
				String rename = sdf.format(new Date());

				//확장자는 원본파일 확장자 얻어오기
				String origin = item.getName(); // 원본파일 이름
				int dotIdx = origin.lastIndexOf("."); //가장 마지막 "."의 인덱스
				
				//확장자
				String ext = origin.substring(dotIdx + 1);
				
				//서버에 저장될 파일의 이름
				String stored = rename +"."+ext;

				//						System.out.println("[TEST] 원본 파일명 : "+origin);
				//						System.out.println("[TEST] 저장될 파일명 : "+stored);

				// --- 첨부파일 정보 객체 ---
				certification.setCeOriginName(origin);
				certification.setCeStoredName(stored);
				// --------------------------

				//업로드된 임시파일을 실제로 저장하기
				// -> 1MB이하 파일 : 메모리에 임시파일로 업로드
				// -> 1MB이상 파일 : HDD에 임시파일로 업로드
				File uploadFolder = new File(req.getServletContext().getRealPath("upload")); // 업로드될 폴더 경로

				uploadFolder.mkdir();

				//실제로 저장될 파일
				File up = new File(uploadFolder, stored);

				try {
					item.write(up); //실제 업로드(최종 결과 파일이 생성됨)
					item.delete(); //임시 파일 삭제
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} // 파일 처리 end

		} // while( iter.hasNext() ) end - FileItem 반복 처리

		//파일이 선택되지 않았을경우
		if(certification.getCeOriginName()==null || "".equals(certification.getCeOriginName())) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
			String rename = sdf.format(new Date());

			File uploadFolder = new File(req.getServletContext().getRealPath("upload")); // 업로드될 폴더 경로
			File defaultImg = new File(req.getServletContext().getRealPath("resources/img/challenge.png")); //이미지 파일 경로

			String origin = defaultImg.getName(); //파일의 본래 이름 challenge.png
			int dotIdx = origin.lastIndexOf("."); //가장 마지막 "."의 인덱스
			String ext = origin.substring(dotIdx + 1); //확장자
			String stored = rename +"."+ext; //저장 파일

			certification.setCeOriginName(origin);
			certification.setCeStoredName(stored);

			uploadFolder.mkdir();

			//실제로 저장될 파일
			File up = new File(uploadFolder.getPath()+"/"+stored);
			try {
				FileInputStream fis = new FileInputStream(defaultImg); //읽을파일
				FileOutputStream fos = new FileOutputStream(up); //복사할파일

				int fileByte = 0; 
				// fis.read()가 -1 이면 파일을 다 읽은것
				while((fileByte = fis.read()) != -1) {
					fos.write(fileByte);
				}
				//자원 사용 종료
				fis.close(); 
				fos.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//storardName을 가져온다
		Certification removeCertification=participantDao.selectByStoredName(JDBCTemplate.getConnection(), certification);

		String path=req.getServletContext().getRealPath("upload");
		//기존 파일 삭제 로직
		File file = new File(path+"/"+removeCertification.getCeStoredName());
		fileRemove.setFile(file);

		boolean remove = fileRemove.fileRemove(); //파일 삭제
		if(remove) {
			System.out.println("파일 삭제 성공");
		}else {
			System.out.println("파일 삭제 실패");
		}
		
		//DB데이터 입력
		Connection conn = JDBCTemplate.getConnection();
		
		//참가 챌린지 번호 얻어오기
		certification.setPaNo(paNo);

		//인증 정보가 있을 경우
		if(certification != null) {
			//인증글 수정
			if( participantDao.certificationUpdate(conn, certification) > 0 ) {
				JDBCTemplate.commit(conn);
			} else {
				JDBCTemplate.rollback(conn);
			}
		}
	}
	@Override
	public void certificationDelete(HttpServletRequest req) {
		String param = req.getParameter("ceNo");
		Certification certification = null;
		if(param!=null && !"".equals(param)) {
			certification= new Certification();
			certification.setCeNo(Integer.parseInt(param));
			
		}
		//storardName을 가져온다
		Certification removeCertification=participantDao.selectByStoredName(JDBCTemplate.getConnection(), certification);

		String path=req.getServletContext().getRealPath("upload");
		//기존 파일 삭제 로직
		File file = new File(path+"/"+removeCertification.getCeStoredName());
		fileRemove.setFile(file);

		boolean remove = fileRemove.fileRemove(); //파일 삭제
		if(remove) {
			System.out.println("파일 삭제 성공");
		}else {
			System.out.println("파일 삭제 실패");
		}

		
		//인증 정보가 있을 경우
		if(certification != null) {
			//인증글 삭제
			if( participantDao.certificationDelete(JDBCTemplate.getConnection(), certification) > 0 ) {
				JDBCTemplate.commit(JDBCTemplate.getConnection());
			} else {
				JDBCTemplate.rollback(JDBCTemplate.getConnection());
			}
		}
		
	}
	
}
