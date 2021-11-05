package com.jcpdev.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcpdev.board.model.Comment;
import com.jcpdev.board.service.CommentService;



@Controller
@RequestMapping("/community/comment")
public class CommentController {
	
	@Autowired
	CommentService service;
	
	//리퀘스트핸들러매핑을 파라미터(get)로 한다.
	@RequestMapping(params="action=insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute Comment comt,int page, int func,Model model ) {
		if(func==1) {
			//댓글 idx는 자동증가 컬럼
			service.insert(comt);
			service.updateCountAll(comt.getMref());
		//	service.updateCommentCntInc(comt.getMref());		//댓글갯수 1증가 테이블 컬럼 업데이트
		}else if (func==2){
			service.update(comt.getIdx(),comt.getContent());
		}
		model.addAttribute("idx", comt.getMref());
		model.addAttribute("page", page);
		return "redirect:/community/detail";
	}
	
	//이 메소드는 ?action=delete 파라미터로만 매핑합니다. GET,POST 모두 매핑될수 있습니다.
	@RequestMapping(params="action=delete")
	public String delete(int idx,int page,int mref,Model model) {
		service.delete(idx);    //댓글 번호
		service.updateCountAll(mref);
		model.addAttribute("idx", mref);    //메인글번호
		model.addAttribute("page", page);
		return "redirect:/community/detail";
	}
	
}


/*
http://www.objectaid.com/update/current
*/