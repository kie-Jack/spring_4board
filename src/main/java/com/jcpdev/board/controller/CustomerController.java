package com.jcpdev.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.jcpdev.board.dao.CustomerMapper;
import com.jcpdev.board.model.Customer;
import com.jcpdev.board.service.CustomerService;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/customer")
@SessionAttributes(names={"join","customer"})
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	
	@Autowired
	CustomerService service;
	
	@ModelAttribute(name="join")
	public Customer setJoin() {
		return new Customer();
	}
	
	
	
	@RequestMapping(value={"/","/list"})   //(value={"/","/list"})
	public String list(Model model) {
		logger.info("고객 리스트 요청입니다.");
		List<Customer> list = service.selectAll();
		model.addAttribute("list", list);
		return "admin/list";
	}
	

	
	//회원가입 join?start -> auth.jsp(이메일인증) -> join?step=1 -> terms.jsp(이용약관) -> join?step=2 -> join.jsp(개인정보입력)
	//   join?step=3
	
	@RequestMapping(value="/join",params="start=1")
	public String join() {
			return "/customer/auth";
	}
	
	@RequestMapping(value="/join",params = "step=1")
	public String join1(@ModelAttribute("join") Customer customer) {
		logger.info("--" + customer.getEmail());
		return "/customer/service_terms";
	}
	
	@RequestMapping(value="/join",params = "step=2")
	public String join2(@ModelAttribute("join") Customer customer) {
		logger.info("--" + customer.getEmail());

		if(customer.getEmail()== null)
			return "redirect:join?start=1";
		return "/customer/join";
	}
	
	@RequestMapping(value="/join",params="save",method=RequestMethod.POST)
	public String registration(@ModelAttribute("join")Customer join,SessionStatus status,Model model) {
		
		service.insert(join);
		logger.info("고객등록 완료 idx =" + join.getIdx());
		status.setComplete();
		return "/customer/welcome";
	}
	
	@RequestMapping(value="detail")
	public String detail(@SessionAttribute("customer") Customer customer) {
			return "/customer/detail";
	}
	
	@RequestMapping(value="update")
	public void update(@SessionAttribute("customer") Customer customer){

	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String save(@ModelAttribute Customer mod_cus,Model model){
		
		service.update(mod_cus);
		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("alert", "");
//		mv.addObject("customer", mod_cus);
//		mv.setViewName("customer/update");		//경로 안하면 오류. default 전달할 때와 다르니 주의
//		return mv;
		
		model.addAttribute("alert","");
		model.addAttribute("customer",mod_cus);
		return  "redirect:detail";
	}
	
	@RequestMapping(value="delete")
	public String delete(int idx,SessionStatus status) {
		service.delete(idx);
		status.setComplete();
		return "redirect:list";
	}
	
}
