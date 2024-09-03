package com.winter.app.util;

import lombok.Data;

@Data
public class Pager {
	
	//몇페이지씩 볼건지 perpager
	private Long perPage=10L;
	private Long startRow;
	private Long page;
	
	private String kind;
	private String search;
	
	public void makeRow() {
		this.startRow = (page-1)*perPage;			 
	}
	
	//getter
	public String getKind() {
		if(this.kind==null) {
			this.kind="k1";
		}
		return this.kind;
	}
	
	public String getSearch() {
		if(this.search==null) {
			this.search="";
		}
		return this.search;
	}
	
	
}
