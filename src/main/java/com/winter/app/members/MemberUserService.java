package com.winter.app.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberUserService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;

	
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
	
}
