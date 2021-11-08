package com.jcpdev.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jcpdev.board.model.Customer;
import com.jcpdev.board.service.CustomerService;

//@Controller
public class LoginController2 {
	
	private final CustomerService service;
	
	public LoginController2(CustomerService service) {
		this.service = service;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
	
		return "login";    // 로그인 버튼 => login.jsp(뷰) -> 로그인정보입력후 버튼(사용자) -> 
	}
	
	// -> 로그인 정보를 Model 객체로 전달받습니다.
	@RequestMapping(value = "login", method = RequestMethod.POST)
	//메소드 매개변수 Customer customer 일 때, @ModelAttribute("customer") Customer customer 에서 생략된 상태
	public String loginOk(String email,String password,Model model,HttpSession session) {  //customer 가 모델객체입니다.(로그인정보 저장된상태)
		//로그인 정보 일치하는지 확인.
		Customer result = service.login(Customer.builder().email(email).password(password).build());
		if(result != null) {
			//로그인 성공- session에 result 저장합니다.
			session.setAttribute("customer", result);
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
	public String logout(HttpSession session) {
		session.invalidate();
		return "home";
	}
	

}