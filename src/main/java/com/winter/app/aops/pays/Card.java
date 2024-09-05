package com.winter.app.aops.pays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@Aspect
public class Card {
	
	//레거시면 이 설정을 xml에 다 했어야했다
	@Around("execution(* com.winter.app.aops.transfers.Transfer.take*(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint)throws Throwable {
		log.info("===Before Card Check===");//공통로직
		log.info("===args : {}",joinPoint.getArgs());
		
		Object obj = joinPoint.proceed(); //point-cut, exception보다 부모라서 에러 Throwable 예외로 준다
		log.info("===Return : {} ", obj);
		log.info("===after Card Check===");
		
		return obj;
	}
	
}
