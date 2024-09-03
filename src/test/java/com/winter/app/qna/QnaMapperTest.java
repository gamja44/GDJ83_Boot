package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.util.Pager;

@SpringBootTest
class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	void getDetailTest() throws Exception{
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(110L);
		qnaVO = qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
	}
	
	//test개발주도방식을 사용해보기위한 메서드
//	@Test
//	void addTest() throws Exception{
//		for(int i=4;i<110;i++) {
//		
//			QnaVO qnaVO = new QnaVO();
//			qnaVO.setBoardContents("c3"+i);
//			qnaVO.setBoardTitle("t3"+i);
//			qnaVO.setBoardWriter("w3"+i);
//			qnaVO.setRef((long)i);
//			qnaVO.setStep(0L);
//			qnaVO.setDepth(0L);
//			int result = qnaMapper.add(qnaVO);	
//			if(i%10==0) {
//				Thread.sleep(500);
//			}
//		
//		}
//	}
	
	@Test
	void getListTest()throws Exception {
		Pager pager = new Pager();
		pager.setPage(1L);
		pager.setKind("k1");
		pager.setSearch("2");
		pager.makeRow();
		
		List<QnaVO> arr = qnaMapper.getList(pager);
		
		assertNotEquals(0, arr.size());
	}


}
