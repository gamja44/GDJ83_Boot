package com.winter.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//매개변수가 있는 어노테이션 생성
@AllArgsConstructor
//매개변수가 없는 어노테이션 생성
@NoArgsConstructor
@Data
public class NoticeVO {
	private Long board_num;
	private String board_writer;
	private String board_title;
	private String board_contents;
	private Date create_Date;
}
