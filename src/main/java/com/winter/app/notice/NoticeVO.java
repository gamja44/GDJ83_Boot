package com.winter.app.notice;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO Data Transfer Object
//VO value Object 읽기 전용, 한 번들어가면 상수처럼 변경이 안됨

//롬복에 있는 게터를 사용하면 밑에 코드는 없지만 아웃라인 생성된것을 알 수 있다
/*@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString*/
@Data
public class NoticeVO {
	
	private Long boardNum;
	private String boardWriter;
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	
	
}
