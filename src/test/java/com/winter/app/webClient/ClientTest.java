package com.winter.app.webClient;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.comments.PostVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@SpringBootTest
class ClientTest {
	
	@Test
	void webClientListTest() {
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://jsonplaceholder.typicode.com/")
									   .build();
		Flux<PostVO> res = webClient.get()
				 					.uri("posts")
				 					.retrieve()
				 					.bodyToFlux(PostVO.class);
		
		res.subscribe(v->{
			PostVO p = v;
			log.info("V : {}", v);
			
		});
		
		
				
	}
	
	//@Test
	void webClientTest() {
		WebClient webClient = WebClient.builder()
									   .baseUrl("https://jsonplaceholder.typicode.com/")
									   .build();
		Mono<PostVO> res = webClient.get()
				 					.uri("posts/1")
				 					.retrieve()
				 					.bodyToMono(PostVO.class);
		PostVO postVO = res.block();
		
		log.info("webClientResult : {}", postVO); 
		
		
				 
		
	}

//	@Test
//	void test1() {
//		//RestTemplate
//		RestTemplate restTemplate = new RestTemplate();
//		
//		//parameter 생성
//		//파라미터하나로 파일을 여러개 보낼수있다
//		//이름은 하나고 안에는 여러개가 가능하다
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("postId", "1");
//		
//		//요청객체 생성
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String,String>>(params, null);
//		
//		//어느 메서드로 이 객체를 보낼꺼냐/?
//		//요청 전송 응답 처리
//		PostVO res = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts/1", PostVO.class);
//		//PostVO [] result = res.getBody();
//		
//		log.info("result : {} ", res);
//		
//		
//	}

}
