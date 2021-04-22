package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ScheduleChallenge implements Filter {
	
	//인코딩 설정값 변수
	private String charset = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Schedule - init()");
		//web.xml에 설정한 초기화파라미터 얻어오기
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
	
	}
}
