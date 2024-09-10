package com.winter.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //스프링에서 만든 자체적 security
@Configuration
public class SecurityConfig {
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//security에서 무시할 내용, 접근금지
		//security에서 제외할 항목들
		return web -> web
						.ignoring() 
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/vendor/**")
						.requestMatchers("/favicon/**")
						
						; 
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security)throws Exception {
		security
				.cors() //fetch나 axios를 사용했을때 에러메세지, 같은 서버내에서 발급을 공유
				.and()
				.csrf()
				.disable();
		security
			.authorizeHttpRequests(
				(authorizeRequest)->
					authorizeRequest
					.requestMatchers("/").permitAll()
					.requestMatchers("/qna/list").permitAll()
					.requestMatchers("/qna/*").authenticated()
					.requestMatchers("/notice/list","/notice/detail").permitAll()
					.requestMatchers("/notice/*").hasRole("ADMIN")
					.requestMatchers("/manager/*").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/member/add", "/member/login").permitAll()
					.requestMatchers("/member/*").authenticated()
					.anyRequest().permitAll()
					)//.authorizeHttpRequests끝부분
			//form login관련된 설정
			.formLogin(
					login -> 
						login
							.loginPage("/member/login") //로그인시url주소를 보내줘
							.defaultSuccessUrl("/")
							.failureUrl("/member/login")
							//파라미터이름이 username이 아니라 'id'로 사용한 경우
							//.usernameParameter("id")
							//파라미터이름이 password가 아니라 'pw'로 사용한 경우
							//.passwordParameter("pw")
							.permitAll()
					)
			
			;
		return security.build();
		
	}
	//password를 확인하는 인코더
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	
	
}
