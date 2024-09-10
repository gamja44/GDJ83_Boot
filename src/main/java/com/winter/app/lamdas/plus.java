package com.winter.app.lamdas;

import org.springframework.beans.factory.annotation.Autowired;

public class plus implements Myfunction{
	@Autowired
	public int calc(int num1, int num2)throws Exception{
		int result=num1+num2;		
		return result;
	}
	
}
