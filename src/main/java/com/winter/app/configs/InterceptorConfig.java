package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.home.interceptors.AdminCheckInterceptor;
import com.winter.app.home.interceptors.Logininterceptor;

@Configuration//설정파일 어노테이션
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private Logininterceptor loginInterceptor;
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 어떤 URL이 왔을 때 어떤 interceptor를 실행 할것인가?
		// /qna/list -> LoginInterceptor을 거치게하자
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*")
				.excludePathPatterns("/qna/list");
		
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/admin/**");
	}
	
	
	
}
