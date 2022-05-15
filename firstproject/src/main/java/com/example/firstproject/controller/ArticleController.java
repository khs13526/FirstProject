package com.example.firstproject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ArticleController {
	
	@Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결해준다... 
	private ArticleRepository articleRepository;
	
	
	@GetMapping("/articles/new")
	public String newAticleForm() {
		
		return "articles/new";
	}

	@PostMapping("/articles/create")
	public String createArticle(ArticleForm form) {
		log.info("=====");
		log.info(form.toString());
		/*
		System.out.println(form.toString());
		-> 로깅 기능으로 대체 */
		
		// 1. DTO를 Entity로 변환!
		Article article = form.toEntity();
		log.info("------" + article.getContent());
		log.info("------" + article.getTitle());
		//System.out.println(article.toString());
		
	
		// 2. Repository에게 Entity를 DB안에 저장하게 함!
		Article saved = articleRepository.save(article);
		log.info(saved.toString());
		//System.out.println(saved.toString());

		return "redirect:/articles/"+ saved.getId();
	}
	
	@GetMapping("/articles/{id}")
	
	public String show(@PathVariable Long id, Model model) {
		log.info("id =" + id);
		
		// 1: id로 데이터를 가져옴
		Article articleEntity = articleRepository.findById(id).orElse(null);
		
		// 2: 가져온 데이터를 모델에 등록!
		model.addAttribute("article", articleEntity);
		
		// 3: 보여줄 페이지를 설정!
		
		
		return "articles/show";
		
	}
	
	@GetMapping("/articles")
	public String index(Model model) {
		
		// 1: 모든 article을 가져온다
		List<Article> articleEntityList = articleRepository.findAll();
		
		// 2: 가져온 article 묶음을 뷰로 전달!
		
		model.addAttribute("articleList", articleEntityList);
		
		// 3: 뷰페이지를 설정한다
		
	
		return "articles/index";
	}
	
	@GetMapping("/articles/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		// 1. 수정할 데이터를 가져온다
		Article articleEntity = articleRepository.findById(id).orElse(null);
		
		// 2. 모델에 데이터를 등록!
		model.addAttribute("article", articleEntity);
		
		// 3: 뷰페이지를 설정한다!
		return "articles/edit";
	}
	
	@PostMapping("articles/update")
	public String update(ArticleForm form) {
		log.info(form.toString());
		
		// 1. DTO를 Entity로 변환한다
		Article articleEntity = form.toEntity();
		log.info(articleEntity.toString());
		// 2. 엔티티를 DB로 저장한다
		// 2-1. DB에서 기존 데이터를 가져온다.
		Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
		// 2-2. 기존 데이터의 값을 갱신한다
		if (target != null) {
			articleRepository.save(articleEntity);
		}
		// 3. 수정결과 페이지로 리다이렉트!!
		return "redirect:/articles/" + articleEntity.getId();
	}
	
	@GetMapping("articles/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes rttr) {
		log.info("삭제 요청이 들어왔습니다.");
		
		// 삭제 대상을 가져온다.
		
		Article target = articleRepository.findById(id).orElse(null);
		
		log.info(target.toString());
	
		// 대상을 삭제한다.
		if (target != null) {
			
			articleRepository.delete(target);
			rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
		}
		
		// 결과 페이지로 리다이렉트 한다.
		
		return "redirect:/articles";
	}
	
	
}
