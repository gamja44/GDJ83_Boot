package com.winter.app.members;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	public int add(MemberVO memberVO)throws Exception{
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
}
	

