package com.winter.app.Notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.notice.NoticeMapper;
import com.winter.app.notice.NoticeVO;

@SpringBootTest
class NoticeMapperTest {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Test
	void getListTest()throws Exception{
		List<NoticeVO> ar = noticeMapper.getList();
		
		for(NoticeVO noticeVO : ar) {
			System.out.println(noticeVO.toString());
		}
		
		/* assertNotEquals(0, ar.size()); */
		
	}

}
