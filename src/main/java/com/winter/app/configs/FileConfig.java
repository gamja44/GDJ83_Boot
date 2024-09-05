package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//스프링이 이 클래스를 읽어들이는 설정
//스프링이 설정파일이라고 인식하고 시작 .xml이라고 생각
//설정 class 
@Configuration
@Slf4j
//webMVCconfigure 구현
//인터페이스이다

public class FileConfig implements WebMvcConfigurer {
	
	//files가왔을때 D:드라이브 경로가 올수있게 해주는것
	@Value("${app.url.path}")
	private String url;
	@Value("${app.upload.location}")
	private String file;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<resource mapping="/resources/**" location="/resources/" />
		//<resource mapping="/files/**" location="D:/upload/" />
		//파일을 가져올때 쓰는 파일
		log.info("=================================================================");
		log.info(url);
		log.info(file);
		registry.addResourceHandler(url)
				.addResourceLocations(file);
		
		
	}
}
