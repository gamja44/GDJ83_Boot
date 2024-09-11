package com.winter.app.members;

import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.validate.MemberAddGroup;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("check")
	public void check()throws Exception {
		
	}
	
	
	//add
	@GetMapping("add")
	public void add(MemberVO memberVO)throws Exception{
		//model.addAttribute("memberVO",new MemberVO());
	}
	
	@PostMapping("add")
	public String add(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check =memberService.memberValidate(memberVO, bindingResult);
		if(check) {
			return "member/add";
		}
		int result = memberService.add(memberVO);
//		if(bindingResult.hasErrors()) {
//			return "member/add";
//		}	
		
		return "redirect:../";
	}
	
	
	@GetMapping("login")
	public String login(String message, Model model)throws Exception{
		model.addAttribute("message", message);
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}", context);
		
		if(context == null) {
			return "member/login";
		}
		String user = context.getAuthentication().getPrincipal().toString();
		log.info("user : {}", user);
		if(user.equals("anonymousUser")) {
			return "member/login";
		}
		
		return "redirect:/";
	}
//	
//	@PostMapping("login")
//	public String login(MemberVO memberVO, HttpSession session)throws Exception{
//		memberVO = memberService.detail(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//		}
//			
//		return "redirect:../";
//	}
	
	//logout
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception {
		session.invalidate();
		return "redirect:../";
		
	}
	//mypage
	@GetMapping("mypage")
	public void mypage(HttpSession session)throws Exception {
		//session에 있는 내용을 꺼내쓰기
		Enumeration<String> en = session.getAttributeNames();
		while(en.hasMoreElements()) {
			String name = en.nextElement();
			log.info("Name : {}", name); //SPRING_SECURITY_CONTEXT
		}
		SecurityContextImpl sc = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
		//log.info("obj : {}", obj.getClass()); //class org.springframework.security.core.context.SecurityContextImpl
		log.info("SecurityContextImpl : {}", sc);
		
		SecurityContext context = SecurityContextHolder.getContext();
		log.info("context : {}", context);
		
		Authentication authentication= context.getAuthentication();
		log.info("Authentication : {}", authentication);
		
		 MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		 log.info("MemberVO: {}", memberVO);
		 log.info("Name : {}", authentication.getName());
	}
	//update
	@PostMapping("update")
	public String update(@Validated(MemberAddGroup.class) MemberVO memberVO, BindingResult bindingResult)throws Exception {
		if(bindingResult.hasErrors()) {
			return "member/update";
		}
		return "redirect:./mypage";
	}
	
	@GetMapping("update")
	private String update(HttpSession session, Model model)throws Exception{
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		model.addAttribute("memberVO",memberVO);
		return "member/update";
	}
	
}
