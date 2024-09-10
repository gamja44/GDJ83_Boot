package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//눈에 안보이는 필터에서 작용
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		try {
			memberVO=memberMapper.detail(memberVO);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return memberVO;
	}
	
	
	public int add(MemberVO memberVO)throws Exception{
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberMapper.add(memberVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 1);
		
		result = memberMapper.addRole(map);
		
		return result;
	}
	
	public MemberVO detail(MemberVO memverVO)throws Exception{
		MemberVO result = memberMapper.detail(memverVO);
		if(result != null) {
			if(result.getPassword().equals(memverVO.getPassword())) {
				return result;
			}
		}
			return null;
	}
	 
//	public int addRole(Map<String, Object> map)throws Exception{
//		return 0;
//	}
	
	
	//검증 메서드
	public boolean memberValidate(MemberVO memberVO,BindingResult bindingResult)throws Exception {
		boolean check=false;
		//check==false 검증성공(error없음)
		//check==true 검증실패(error잇음)
		//0. 기본검증값(Annotation검증의 결과값)
		check = bindingResult.hasErrors();
		
		//1. password 일치하는지 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true; 
			//에러메세지
			//bindingResult.rejectValue("멤버변수명(path)", "properties의key(코드)");
			bindingResult.rejectValue("passwordCheck", "memberVO.pw.notEqual");
		}
		
		//2. ID중복 검사
		MemberVO result= memberMapper.detail(memberVO);
		if(result != null) {
			check=true;
			bindingResult.rejectValue("username", "memberVO.username.duplication");
		}
		
		return check;
		
	}
}
	

