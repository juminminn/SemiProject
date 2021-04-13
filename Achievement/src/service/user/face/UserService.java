package service.user.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Users;
import util.Paging;

public interface UserService {

	/**
	 * 회원목록 가져오기
	 * 검색한 단어가 없을 경우 전체 목록 출력
	 * 검색한 단어가 있을 경우 단어를 포함한 목록 출력
	 * 
	 * 
	 * @param req 요청 정보 객체
	 * @return - 회원목록
	 */
	public List<Users> searchUser(HttpServletRequest req);
	
	
	

}
