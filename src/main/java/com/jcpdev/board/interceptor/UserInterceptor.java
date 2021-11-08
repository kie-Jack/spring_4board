package com.jcpdev.board.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


//가로 채기 : 
//Interceptor : 컨트롤러 핸들러메소드 실행 전후에 필요한 작업들 수행, HandlerInterceptor 인터페이스 구현이 필요하다.
public class UserInterceptor implements HandlerInterceptor {  
	private static final Logger log = LoggerFactory.getLogger(UserInterceptor.class);

	//아래 3개의 메소드가 동작하는 시점은 다릅니다.

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		log.info("3.view 생성까지 완료 후에");   //후처리
		log.info("-" + arg2);
		log.info("-" + arg3);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		log.info("2.hadler method 실행 완료, view를 생성하기 전에.");   //전처리2
		log.info("-" + arg2);
		log.info("-" + arg3);
	}

	// 특정 url 은 로그인이 되었는지 검사하여 로그인 되었을 때는 return true;
	// 								  로그인 안되있으면 return false; 그리고 로그인url
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		  log.info("1.handler method 매핑 이전에 실행.");   //전처리1
		  log.info("-" + arg2);

		  String reqUrl = request.getServletPath();    //클라이언트요청 url 확인을 위한 출력
		  log.info("///////" + reqUrl); 
		  reqUrl = request.getRequestURL().toString();
		  log.info("///////" + reqUrl); 
		  return true;
	}	

}