package com.winter.app.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.util.Pager;

@SpringBootTest
@Transactional //모든 테스트 메서드 실행 후 전부 rollback처리, 예외가 없어도 롤백시키겠다
class QnaMapperTest {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Test
	@Rollback(false) //메서드 실행 후 rollback하지 않음
	void getDetailTest() throws Exception{
		QnaVO qnaVO = new QnaVO();
		qnaVO.setBoardNum(110L);
		qnaVO = qnaMapper.getDetail(qnaVO);
		
		assertNotNull(qnaVO);
	}
	
	//test개발주도방식을 사용해보기위한 메서드
	@Test
	void addTest() throws Exception{
		for(int i=4;i<110;i++) {
		
			QnaVO qnaVO = new QnaVO();
			qnaVO.setBoardContents("c3"+i);
			qnaVO.setBoardTitle("t3"+i);
			qnaVO.setBoardWriter("w3"+i);
			qnaVO.setRef((long)i);
			qnaVO.setStep(0L);
			qnaVO.setDepth(0L);
			int result = qnaMapper.add(qnaVO);	
			if(i%10==0) {
				Thread.sleep(500);
			}	
		}
	}
	
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
