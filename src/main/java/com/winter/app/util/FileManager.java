package com.winter.app.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class FileManager {
	
	public String fileSave(String path, MultipartFile multipartFile)throws Exception {
		//어디에 저장?
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//저장할 파일명 생성(중복x)
		String fileName = UUID.randomUUID().toString()+"_"+multipartFile.getOriginalFilename();
		
		//파일을 HDD에 저장
		file = new File(file, fileName);
		log.info("file", file.getPath());
		multipartFile.transferTo(file);
		
		//저장된 파일명 리턴
		return fileName;
	}
	
}
