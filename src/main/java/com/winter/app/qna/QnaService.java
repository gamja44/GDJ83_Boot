package com.winter.app.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.FileManager;
import com.winter.app.util.Pager;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String name;	
	
	@Autowired
	private FileManager fileManager;
		
	public List<QnaVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		log.info("Upload Path : {}", upload);
		return qnaMapper.getList(pager);
	}
	
	//문제발생시 롤백을 해주겠다 
	//메서드마다 transactional을 주던가
	//클래스에 선언하는 방식도 있다
    //	@Transactional(rollbackFor = Exception.class)
	public int add(QnaVO qnaVO, MultipartFile [] attaches)throws Exception{
		//log.info("================insert before boardNum: {} ", qnaVO.getBoardNum());
		int result=qnaMapper.add(qnaVO);
		//log.info("================insert after boardNum: {} ", qnaVO.getBoardNum());
		result = qnaMapper.refUpdate(qnaVO);
		
		if(result==0) {
			throw new Exception();
		}
		
		//파일을 HDD에 저장 후 DB에 정보를 추가
		for(MultipartFile mf : attaches) {
			if(mf==null || mf.isEmpty()) {
				continue;
			}
			 String fileName= fileManager.fileSave(upload+name, mf); //D:/upload/qna
			 //log.info("저장된 파일명 : {}",fileName);
			 QnaFileVO qnaFileVO = new QnaFileVO();
			 qnaFileVO.setFileName(fileName);
			 qnaFileVO.setOriName(mf.getOriginalFilename());
			 qnaFileVO.setBoardNum(qnaVO.getBoardNum());
			 
			 result = qnaMapper.addFile(qnaFileVO);
		}
		
		
		return 0;
	}
	
	public QnaVO getDetail(QnaVO qnaVO) throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	public QnaFileVO getFileDetail(QnaFileVO qnaFileVO)throws Exception{
		return qnaMapper.getFileDetail(qnaFileVO);
	}
	
	
}
