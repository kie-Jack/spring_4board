package com.jcpdev.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcpdev.board.dao.CommentMapper;
import com.jcpdev.board.model.Comment;



@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentMapper cdao;
	
	@Override
	public int insert(Comment dto) {
		return cdao.insert(dto);
	}

	@Override
	public List<Comment> commentList(int mref) {
		return cdao.commentList(mref);
	}

	@Override
	public int commentCount(int mref) {
		return cdao.commentCount(mref);
	}

	@Override
	public int update(int idx,String content) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("idx", idx);
		map.put("content", content);
		return cdao.update(map);
	}

	@Override
	public int delete(int idx) {
		return cdao.delete(idx);
	}
	
	@Override
	public void updateCountAll(int idx) {
		cdao.updateCountAll(idx);
	}
	
	
}
