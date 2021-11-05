package  com.jcpdev.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jcpdev.board.model.Board;
import com.jcpdev.board.model.PageDto;
import com.jcpdev.board.service.CommentService;
import com.jcpdev.board.service.FreeboardService;

@Controller
@RequestMapping("/community")		//-> view 폴더명 동일할때 생략, Redirect community 생략 가능
public class FreeboardController {
	private static final Logger logger = LoggerFactory.getLogger(FreeboardController.class);
	
	@Autowired
	FreeboardService service;
	
	@Autowired
	CommentService  cmtservice;
//   http://localhost:8087/board/community 또는
//   http://localhost:8087/board/community/list 와 매핑이 됩니다.	
//   request mapping을 여러 url 요청으로 할수 있습니다.value 가 배열.
	//게시판 리스트 보기(검색기능 포함)     	
	@RequestMapping(value={"/","/list"})   
	public String pageList(@RequestParam Map<String, Object> param,Model model) { //String page,String field,String findText,Model model) {
		logger.info("**freeboard list 출력합니다.");
		
		int currentPage;//현재 페이지
		List<Board> list;
		int totalCount; int pageSize=10;
		String page=(String) param.get("page");
		if(page==null || page.trim().length()==0) currentPage = 1;
		else currentPage = Integer.parseInt(page);   //page파라미터가 숫자로 넘어온경우만 실행. 
		
		//page를 사용자 맘대로 문자 대입하면 NumberFormatExceptrion 발생 -> ExceptionHandler 로 처리합니다.
		
		PageDto pageDto;
		//검색 기능사용할 때 검색필드와 검색키워드 뷰에 전달한다.

		String findText = (String) param.get("findText");
		String field=(String) param.get("field");
		
		totalCount=service.searchCount(param);   //서비스 메소드 타입 변경예정
		pageDto=new PageDto(currentPage, pageSize, totalCount, field, findText);
		list=service.searchList(pageDto);  //주석처리 예정
		Map<String,Object> map = new HashMap<String,Object>();    
		map.put("field",field);
		map.put("findText",findText);
		map.put("page", pageDto);			//view에게 전달할 모델객체 설정
		map.put("list",list);				//          "
		model.addAllAttributes(map);	//위에 4개의 put 실행한 map객체를 애트리뷰트에 저장한다.
		
		return "community/list";
		
	}  //view 이름은? list.jsp
	
	//이 코드는 페이지 리스트 만드는 부분을 서비스로 이동시키는 예제 입니다.- 테스트용으로만 확인.
	// 나머지 다른 jsp파일들의 목록 링크를 list2로 수정하는 부분은 생략하겠습니다.
	@RequestMapping(value="/list2")
	public String list2(@RequestParam Map<String, Object> param,Model model) {
		//컨트롤러는 요청에 대한 데이터 처리를 서비스로 전달하고 결과를 리턴받으면
		//모델에 저장해서 view로 전달합니다.
		param=service.searchList2(param);
		model.addAllAttributes(param);
		return "community/list";
	}
	
	//상세보기 : 미구현
	@RequestMapping("/detail")     
	public void detail(int idx, int page,String field, String findText, Model model) {
		
		model.addAttribute("bean",service.getBoardOne(idx) );
		model.addAttribute("page",page);
		model.addAttribute("cmtlist",cmtservice.commentList(idx) );
		model.addAttribute("cr","\n");
		model.addAttribute("field", field);
		model.addAttribute("findText", findText);
		
		
		//view는 community/detail
		
	}
	
	//글쓰기 - view  : insert() 메소드 
	@RequestMapping(value="/insert")
	public void insert(int page,Model model) {
		model.addAttribute("page", page);
	}  //view이름은 insert
	
	
	
	
	
	//글쓰기 - 저장   : save()메소드  리다이렉트 list로.
	@RequestMapping(value="/save")
	public String save(@ModelAttribute Board board) {
//@ModelAttribute 생략 가능 : form 입력  ->  @ModelAttribute  -> 컨트롤러  ->  @ModelAttribute  -> view
		service.insert(board);
		
		return "redirect:list";
		// redirect 할 때, /가 없으면 현재 경로에서 접근
		// /가 잇으면 contextPath /board 에서 시작
		// "redirect:community/list" 는 오류
		// "redirect:/community/list" 는 정상
	}
	
	//수정 화면
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(@RequestParam Map<String, String> param,Model model) {		//@RequestParam Map<String, String> param
		model.addAttribute("bean", service.getBoardOne(Integer.parseInt(param.get("idx"))));
		
		model.addAllAttributes(param);
		logger.info(param.toString());
		//model.addAttribute("page", param.get("page"));
	}
	
	
	//수정 내용 저장
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String save2(Board board, int idx, int page,String field,String findText,Model model) {
//@ModelAttribute Board board 입니다. update.jsp의 form 요소 값 -> 컨트롤러
	
		service.update(board);
		
		
		//model.addAttribute("page", page);			//1)
		//return "redirect:list";
		model.addAttribute("idx", board.getIdx());
		model.addAttribute("page", page);
		model.addAttribute("field",field);
		model.addAttribute("findText", findText);
		return "redirect:detail";
	}
	

	
	//삭제
	@RequestMapping(value="delete")
	public String delete(@RequestParam Map<String,Object> param,Model model) {
		
		service.delete(Integer.parseInt((String)param.get("idx")));
		//model.addAttribute("page", page);
		model.addAllAttributes(param);
		
		return "redirect:list";
	}
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleErr(HttpServletRequest request ) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", request.getRequestURL());   //애트리뷰트 저장
		mav.setViewName("/error/error");
		return mav;
	}
	
	
	
}