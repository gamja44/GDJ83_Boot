package com.winter.app.lamdas;

@FunctionalInterface //한개만 있다고 보장한다
public interface Myfunction {
	
	public int calc(int num1, int num2)throws Exception;
	
	//애로우함수사용시에는 함수가 1개있어야한다
	//2개를 선언하는 순간 에러를 발생
	//public void ca2()throws Exception;
	
}
