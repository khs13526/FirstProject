package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString
public class ArticleForm {
	
	private Long id; // id 필드 추가!!
	private String title;
	private String content;

	public Article toEntity() {
		return new Article(id, title, content);
		
	}
	
	
	
}
