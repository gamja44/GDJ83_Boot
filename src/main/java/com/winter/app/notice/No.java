package com.winter.app.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.qna.QnaVO;
import com.winter.app.util.Pager;

public class No {
	
	
	@Mapper
	public interface QnaMapper {
		
		//전체리스트를 보는 메서드
		public List<QnaVO> getList(Pager pager) throws Exception;
		
	}

}
