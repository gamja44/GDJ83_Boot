package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.Pager;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String board;
	
	
	@ModelAttribute("board")
	public String getBoard() {
		return this.board;
	}
		
	@GetMapping("list")//return "qna/list"
	public String getList(Pager pager, Model model)throws Exception{
		//Pager pager = new Pager(); 매개변수의 의미
		
		List<QnaVO> ar = qnaService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		log.info("Pager : {} : {}", pager, pager.getKind());
		
		return "qna/list";
	}
	@GetMapping("add")
	public void add(@ModelAttribute QnaVO qnaVO)throws Exception{
		//@ModelAttributed이게 매개변수에 생략되어있다
	}
	
	@PostMapping("add")
	public String add(@Valid QnaVO qnaVO, BindingResult bindingResult, MultipartFile[] attaches) throws Exception{
		//@Valid QnaVO qnaVO 자동으로 qnaVO를 검증해준다
		//검증하고 bindingResult결과값을 여기에 담는다
		if(bindingResult.hasErrors()) {
			log.error("Writer가 비어있음");
			return "qna/add";
		}	
		int result = qnaService.add(qnaVO, attaches);
		return "redirect:./list";
	}
	//detail
	@GetMapping("detail")//return "qna.detail"
	public void getDetail(QnaVO qnaVO, Model model) throws Exception{
		qnaVO = qnaService.getDetail(qnaVO);
		model.addAttribute("vo", qnaVO);
	}	
	
	@GetMapping("fileDown")
	public String fileDown(QnaFileVO qnaFileVO, Model model)throws Exception{
		qnaFileVO = qnaService.getFileDetail(qnaFileVO);
		model.addAttribute("file", qnaFileVO);
		
		//빈의 이름은 클래스명의 소문자로 바꾼것이 빈의 이름이다
		//빈의 이름이 fileDownView를 빈의 객체를 만들어서 보내자
		//view가 객체의 이름이 된다
		//같은 이름이 없으면 그 다음 jsp경로를 찾으러 간다
		return "fileDownView"; //FileDownView클래스로 간다
		
	}
	
	
}
