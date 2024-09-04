package com.winter.app.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

//매핑해주는 어노테이션
@Mapper
public interface QnaMapper {
	
	//전체리스트를 보는 메서드
	public List<QnaVO> getList(Pager pager) throws Exception;
	
	public int add(QnaVO qnaVO) throws Exception;
	
	public int refUpdate(QnaVO qnaVO) throws Exception;
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception;
	
	public int addFile(QnaFileVO qnaFileVO)throws Exception;
	
}
