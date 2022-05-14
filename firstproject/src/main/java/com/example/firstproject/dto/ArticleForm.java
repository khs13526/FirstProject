package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
	
	private String title;
	private String content;

	
	public ArticleForm(String title, String content) {
		this.title = title;
		this.content = content;   // 생성자 -> 객체가 객체화 될때 호출되는 특수 함수. 새 키워드를 사용할때 초기화 하기위하여 사용함 //
	}


	@Override
	public String toString() {
		return "ArticleForm [title=" + title + ", content=" + content + "]";
	}


	public Article toEntity() {
		
		return new Article(null, title, content);
		
	}
	
	
	
}
