package com.winter.app.configs.security;


import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.winter.app.members.MemberMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SecurityLoginFailHandler implements AuthenticationFailureHandler{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		String message = "로그인실패";
		
		log.error("Exception : {}", exception);
		
		if(exception instanceof BadCredentialsException) {
			//비번이 틀린경우
			message = "비밀번호 확인";
		}
		if(exception instanceof InternalAuthenticationServiceException) {
			//아이디가 틀린경우
			message = "없는 ID 확인";
		}
		if(exception instanceof AccountExpiredException){
			//isAccountNonExpired false
			//계정의 유효기간 만료
			message = "만료된 계정 관리자에게 문의바람";
		}
		if(exception instanceof LockedException) {
			//멤버vo에 메서드 false, 계정이 잠긴경우
			//isAccountNonLocked() false
			message = "계정 잠김 관리자에게 문의바람";
		}
		
		if(exception instanceof CredentialsExpiredException) {
			//isCredentialsExpiredException false
			//비번 유효기간 만료
			message = "비번의 유효기간 만료 다시 설정하세요";
		}
		if(exception instanceof DisabledException) {
			//휴면계정일때 사용
			//isEnabled false
			message = "휴면계정 관리자에게 문의 바람";
		}
		
		message = URLEncoder.encode(message,"UTF-8");
		
		
		response.sendRedirect("/member/login?message="+message);
		
		
	}
	
}
