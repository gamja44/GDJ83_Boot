package com.winter.app.configs.security;

import java.net.URLEncoder;

import org.apache.catalina.valves.CrawlerSessionManagerValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.authentication.MaximumSessionsContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.winter.app.members.MemberUserService;

@EnableWebSecurity //스프링에서 만든 자체적 security
@Configuration
public class SecurityConfig {
	
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	
	@Autowired
	private SecurityLogoutSuccessHandler logoutSuccessHandler;
	
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
		
		String message = URLEncoder.encode("로그인실패", "UTF-8");
		
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
					.requestMatchers("/member/add", "/member/login","/member/check").permitAll()
					.requestMatchers("/member/*").authenticated()
					.anyRequest().permitAll()
					)//.authorizeHttpRequests끝부분
			//form login관련된 설정
			.formLogin(
					login -> 
						login
						//개발자가 만든 로그인 페이지 사용
							.loginPage("/member/login") //로그인시url주소를 보내줘
							//.defaultSuccessUrl("/")
							.successHandler(handler)
							//.failureUrl("/member/login?message="+message)
							.failureHandler(failHandler)
							//파라미터이름이 username이 아니라 'id'로 사용한 경우
							//.usernameParameter("id")
							//파라미터이름이 password가 아니라 'pw'로 사용한 경우
							//.passwordParameter("pw")
							.permitAll()
					)
			//logout 설정
			.logout(
					logout -> 
					    //requestMather("url"), 로그아웃 url 지정
						logout
							  .logoutUrl("/member/logout")//로그아웃 url 지정, 둘 중 하나만 사용가능
							  //.logoutSuccessHandler(null)
							  //.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
							 .logoutSuccessHandler(logoutSuccessHandler)	
							  //.logoutSuccessUrl("/")
							  .invalidateHttpSession(true) //true면 session만료, false session 세션 만료x
							  //.deleteCookies("쿠키삭제하고싶을때 사용") "JSESSIONID"
					)
			//remember me
			.rememberMe(
					remember ->
					    //Parameter 이름
						remember.rememberMeParameter("rememberMe")
						        .tokenValiditySeconds(60) //토큰의 유효시간
						        //token에 생성시 값, 필수 값, 개발자가 값 설정, 키는 노출되면 안됨
						        //토큰에 사용되는 값이고 이름은 개발자 마음대로 
						        .key("rememberme") 
						        //인증절차(로그인)진행할 userdetailService
						        .userDetailsService(memberUserService)
						        //로그인이 성공했을 경우 진행할 Handler
						        .authenticationSuccessHandler(handler)
						        .useSecureCookie(false)
					)
			//동시 세션
			.sessionManagement(
					sessionManager ->
						sessionManager
							//최대 허용 갯수, -1이면 무한대
							.maximumSessions(1)
							//false기존사용자만료, true 새로운사용자 인증 실패
							.maxSessionsPreventsLogin(false)
							//session이 만료되었을 경우 리다이렉트할 url
							.expiredUrl("/member/check")
							
			
					)
			// Social Login
			//안에 null을 이따가 작성
			.oauth2Login(
					oauth2 ->
						oauth2.userInfoEndpoint(
								user -> user.userService(memberUserService)
						)
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
