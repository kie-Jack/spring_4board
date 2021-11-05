package com.jcpdev.board.service;

import java.util.List;

import com.jcpdev.board.model.Comment;



public interface CommentService {
	int insert(Comment dto); //댓글 입력
	List<Comment> commentList(int mref); //게시글 전체 댓글
	int commentCount(int mref); //게시글 전체 댓글 개수
	int update(int idx, String content); //댓글 수정
	int delete(int idx); //댓글 삭제
	void updateCountAll(int idx);
	//int updateCmtCnt(Map<String,Integer> map); //프리보드 테이블에 댓글개수 컬럼 업데이트
	//int updateCommentCntInc(int mref); //프리보드 테이블에 댓글개수 컬럼 업데이트
	//int updateCommentCntDec(int mref); 
}
