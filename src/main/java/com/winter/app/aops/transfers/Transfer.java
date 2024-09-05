package com.winter.app.aops.transfers;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Transfer {
	
	
	public String takeBus(int num) {
		//공통로직필요
		log.info("===버스타기===");//핵심로직
		return "Bus";
	}
	
	public void takeSubway(Long m, String name) {
		//공통로직필요 카드찍기
		
		log.info("===지하철타기===");//핵심로직
	}
	
	public void walk(){
		//핵심로직이긴하지만 공통로직은 들어가지않는다
		
		log.info("===걸어가기===");
	}
	
	
}
