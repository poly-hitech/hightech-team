package com.example.test.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.Model.ForumPost;
import com.example.test.Model.ForumPostReview;
import com.example.test.Service.BoardService;

import lombok.extern.slf4j.Slf4j;


//로그 출력을 위해 Slf4j 어노테이션 사용
@Slf4j
@Controller
@RequestMapping("/board")


public class BoardController {
	final static String path = "board/";
	
	@Autowired
	BoardService boardService;
	
	//
	@GetMapping("/list/{boardId}")
	String getNotice(@PathVariable Long boardId,Model model) {
		//boardId를 넘겨주는 getboard를 실행 시킨 값을 list에 저장, boardService.getboard(boardId)를 실행하여 해당 boardId의 게시글 목록(List<ForumPost>)을 조회하여 list에 저장
		List<ForumPost> list = boardService.getboard(boardId);
		
		//list를 "boardList"란 이름으로 복사본 만듦, 조회된 게시글 목록(list)을 "boardList"라는 이름으로 모델에 추가하여 뷰에서 접근 가능하게 함
		model.addAttribute("boardList",list);
		//boardId를 "boardId"란 이름으로 복사본 만듦, 현재 게시판 ID(boardId)를 "boardId"라는 이름으로 모델에 추가하여 뷰에 전달
		model.addAttribute("boardId", boardId);
		return path + "list";
	}
	
	@GetMapping("/add/{boardId}")
	String getAdd(@PathVariable Long boardId){
		return path+"add";
	}
	
	@PostMapping("/add/{boardId}")
	String addPost(@PathVariable Long boardId, ForumPost forumPost, HttpSession session) {
		log.info("재목: " + forumPost.getTitle());
		log.info("노용: " + forumPost.getContent());
		// BoardService를 호출하여 게시글을 저장 (forumPost 객체, boardId, 세션 정보를 전달)
		boardService.addPost(forumPost, boardId, session); 
		
		return "redirect:/";
		
	}
	
	@GetMapping("/modify/{postId}")
	//pathVariable이란 주소값에 들어온postId(58번줄에 있는 postId)의 값을 사용하기 위한 어노테이션
	String getPostId(@PathVariable Long postId, Model model) {
		// boardService.getpost(postId)를 실행하여 해당 postId의 게시글 정보(ForumPost)를 조회
		ForumPost forumPost = boardService.getpost(postId);
		
		//forumPost를 "forumPost"란 이름으로 복사본 만듦, 조회된 게시글 정보(forumPost)를 "forumPost"란 이름으로 모델에 추가하여 수정 폼에 기존 내용을 채울 수 있도록 함
		model.addAttribute("forumPost",forumPost);
		
		return path+"modify";
	}
	
	@PostMapping("/modify/{postId}")
	String modiPost(@PathVariable Long postId, ForumPost forumPost, HttpSession session) {
		log.info("재재목: " + forumPost.getTitle());
		log.info("노노용: " + forumPost.getContent());
		// BoardService를 호출하여 게시글을 저장 (forumPost 객체, postId, 세션 정보를 전달)
		boardService.modiPost(forumPost, postId, session); 
		
		return "redirect:/";
	}
	
	@GetMapping("/list/{boardId}/{postId}")
	String delPost(@PathVariable Long postId, @PathVariable Long boardId) {
		boardService.delPost(postId);
		
		return "redirect:../" + boardId;
	}

	@GetMapping("/detail/{postId}")
	String getPostDetail(@PathVariable Long postId, Model model) {
		ForumPost forumPost = boardService.getPostDetail(postId);
		
		List<ForumPostReview> comments = boardService.getComments(postId);
		
		//forumPost를 "forumPost"란 이름으로 복사본 만듦, 조회된 게시글 정보(forumPost)를 "forumPost"란 이름으로 모델에 추가
		model.addAttribute("forumPost",forumPost);
		model.addAttribute("comments", comments);
		
		log.info("재재목: " + forumPost.getTitle());
		log.info("노노용: " + forumPost.getContent());
		
		return path+"detail";
	}
	
	@PostMapping("/detail/{postId}")
	String addComment(@PathVariable Long postId, ForumPostReview forumPostReview, HttpSession session) {
		// BoardService를 호출하여 게시글을 저장 (forumPost 객체, boardId, 세션 정보를 전달)
		boardService.addComment(forumPostReview, postId, session); 
		
		log.info("유저스: " + session);
		
		return "redirect:/";
		
	}
	
}