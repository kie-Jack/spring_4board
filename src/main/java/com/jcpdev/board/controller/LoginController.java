package com.jcpdev.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jcpdev.board.model.Customer;
import com.jcpdev.board.service.CustomerService;

@Controller
@SessionAttributes("customer")
public class LoginController {
	
	private final CustomerService service;
	
	public LoginController(CustomerService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(String alert, Model model) {
		if(alert !=null && alert.equals("y")) {
			model.addAttribute("message","로그인이 필요합니다.");
			model.addAttribute("url","login");
			return "alertLogin";
		}else {
			
			return "login";    // 로그인 버튼 => login.jsp(뷰) -> 로그인정보입력후 버튼(사용자) -> 
		}
	}
	
	// -> 로그인 정보를 Model 객체로 전달받습니다.
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String loginOk(String email,String password,Model model) {  //customer 가 모델객체입니다.(로그인정보 저장된상태)
		//로그인 정보 일치하는지 확인.
		Customer result = service.login(Customer.builder().email(email).password(password).build());
		if(result != null) {
			//로그인 성공- session에 result 저장합니다.
			model.addAttribute("customer", result);
			return "home";   //정상 로그인 후 -> home.jsp(뷰)
		}else { 
			//로그인 실패
			String message="로그인 정보가 틀립니다.";
			model.addAttribute("message",message );  
			model.addAttribute("url","login");
			return "alertLogin";
		}
	}
	
	@RequestMapping(value = "logout")
//	public String logout(HttpSession session) {   
	public String logout(SessionStatus status) {   //@SessionAttributes로 설정된것은 SessionStatus 로 지운다.
		status.setComplete();   //@SessionAttributes 로 설정된 애트리뷰트를 clear 한다.
//		session.invalidate();
		return "redirect:/";
	}
	
//status.setComplete();   - JSESSIONID 는 변하지 않고 @SessionAttributes 로 설정된 애트리뷰트 값을 clear 한다.
//						  - HttpSession의 removeAttribute() 메소드 동작과 유사
//session.invalidate();   - 서버가 JSESSIONID는 새로 부여해주지만 @SesstionAttributes 로 설정된 값은 남아있다.
}