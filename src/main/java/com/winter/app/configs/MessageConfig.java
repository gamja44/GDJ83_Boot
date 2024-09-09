package com.winter.app.configs;

import java.util.Locale;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//xml설정
@Configuration
public class MessageConfig implements WebMvcConfigurer{
	
	@Bean
	public LocaleResolver localeResolver() {
		//1. session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//return resolver;
		
		//2. Cookie 차이점 
		//세션은 연결하는 동안에 계속사용 로그아웃시 정보가 사라짐
		//쿠키는 설정되어있는 시간까지 계속 살아있다
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
//		cookieLocaleResolver.setCookieName("lang");//deprecated
		
		return cookieLocaleResolver;
	}
	
	@Bean
	 LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		//parameter를 받아서 언어를 구분
		//url?lang=jp 인터셉터로 받아서 활용하겠다
		
		return changeInterceptor;
	}
}
