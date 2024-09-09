package com.winter.app.qna;

import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class QnaVO {
	//비어있으면 안된다
	
	private Long boardNum;
	@NotBlank//(message = "필수입니다")
	private String boardWriter;
	
	private String boardTitle;
	private String boardContents;
	private Date createDate;
	private Long ref;
	private Long step;
	private Long depth;
	private List<QnaFileVO> ar;
	
	
}
