package com.winter.app.aops.pays;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
public class Transcation {
	
	@AfterThrowing("execution(* com.winter.app.*.*.set*(..))")
	public void rollBack() {
		
	}
	
	
}
