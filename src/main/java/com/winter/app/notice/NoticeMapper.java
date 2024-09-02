package com.winter.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper {
	
	//전체 리스트 출력하는 메소드
	public List<NoticeVO> getList() throws Exception;
	
	//insert하는 메소드
	public int add(NoticeVO noticeVO)throws Exception;
	
}
