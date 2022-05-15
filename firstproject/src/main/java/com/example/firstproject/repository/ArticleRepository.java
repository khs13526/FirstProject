package com.example.firstproject.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.example.firstproject.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{

	@Override
	ArrayList<Article> findAll();

}
