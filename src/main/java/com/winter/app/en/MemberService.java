package com.winter.app.en;

public class MemberService {
	//필드선언
	public String id;
	public String password;
	
	public boolean login(String id, String password) {
		if(id=="hong" & password=="1234") {
			this.id=id;
			this.password=password;
			return true;
		}
		return false;
	}
	
	public void logout() {
		System.out.println(id + "님이 로그아웃되었습니다");
	}
	
}
