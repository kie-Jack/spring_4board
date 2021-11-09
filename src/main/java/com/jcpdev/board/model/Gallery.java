package com.jcpdev.board.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gallery {
	private int pno;
	private String title;
	private String filename;

	//파일 첨부 입력 값 저장을을 위한 MultipartFile 타입
	//db저장 X
	private List<MultipartFile> files;



}