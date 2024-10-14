package com.winter.app.en;

public class Member {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemberService ms = new MemberService();
		boolean result = ms.login("hong", "1234");
		if(result) {
			System.out.println("로그인되었습니다");
			ms.logout();
		}else {
			System.out.println("id또는 password가 올바르지않습니단");
		}
	}

}
