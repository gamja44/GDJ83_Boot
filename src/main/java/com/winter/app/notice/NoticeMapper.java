package com.winter.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	
	//전체리스트보는 메서드
	public List<NoticeVO> getList() throws Exception;
	
	//insert메서드
	//매퍼 어노테이션을 사용해서 sqlSession을 사용하지않아도된다
	public int add(NoticeVO noticeVO) throws Exception;
	
	
}
