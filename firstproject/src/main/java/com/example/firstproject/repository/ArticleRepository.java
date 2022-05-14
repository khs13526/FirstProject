package com.example.firstproject.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.firstproject.entity.Article;

public interface ArticleRepository extends CrudRepository<Article, Long>{

}
