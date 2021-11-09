package com.jcpdev.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jcpdev.board.dao.GalleryMapper;
import com.jcpdev.board.model.Gallery;

@Service
public class GalleryService {

	private final GalleryMapper dao;

	public GalleryService(GalleryMapper dao) {
		this.dao = dao;
	}

	public List<Gallery> getAll() {
		return dao.getAll();
	}

	public int save(Gallery vo) throws IllegalStateException, IOException {
		List<MultipartFile> files = vo.getFiles(); // 첨부된 파일의 타입은 MultipartFile
		/*
		 * log.info("name: " + file.getOriginalFilename()); //MultipartFile 타입의 메소드
		 * getOriginalFilename() : 파일첨부시 파일명 log.info("size: "+ file.getSize()); //
		 * getSize() : 파일의 크기
		 */
		StringBuilder sb = new StringBuilder();
		String path = "c:\\upload";

		if (files != null && files.size() > 0) { // 첨부된 파일이 있을 때
			for (MultipartFile f : files) { // 첨부된 파일리스트에 하나씩

				String newpath = "";
//	               String fileName = "board_" + f.getOriginalFilename();   //board_원래 파일명.확장자 ,파일명 변경하여 db에 저장합니다.
				String fileName = "board_" + randomString(f.getOriginalFilename()); // board_원래 파일명.확장자 ,파일명 변경하여 db에
																					// 저장합니다.
				if (!fileName.equals("")) {
					newpath = path + "\\" + fileName; // 업로드경로+파일명
					sb.append(fileName).append(",");
					// 선택한 파일을 서버로 전송
					File upfile = new File(newpath); // newpath에 java.io.File 객체를 생성하고
					f.transferTo(upfile); // 업로드 폴더로 전송합니다.transferTo 메소드 apache 파일 업로드 라이브러리 메소드입니다.
				}
			}
			vo.setFilename(sb.toString()); // 파일명 저장 컬럼 1개 , 저장할 파일명을 컴마로 구분하여 하나의 문자열 생성
		}

		return dao.insert(vo);
	}

	public String randomString(String oldfile) { // 랜덤문자열(숫자10개, 알파벳대소문자 사용) 원하는 length로 생성
		int leftLimit = 48; // 숫자 '0' 아스키코드값
		int rightLimit = 122; // 알파벳 'z' 아스키코드값
		int targetStringLength = 10; // length를 10으로 합니다.
		Random random = new Random();
		String ext = oldfile.substring(oldfile.indexOf("."), oldfile.length()); // 확장자 추출. oldfile이 xxxxx.png 이면
																				// xxxxx변경합니다.

		String rString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || (i >= 65 && i <= 90) || i >= 97)) // 사용하지 않을 기호 제외
				.limit(targetStringLength) // length 적용
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		// 또는 UUID.randomUUID().toString(); //-> 생성문자열이 길어요.
		return rString + ext; // 랜덤문자열.확장자를 리턴
	}

}