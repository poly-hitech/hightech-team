package com.example.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.Model.ForumPost;
import com.example.test.Service.BoardService;

import lombok.extern.slf4j.Slf4j;


//
@Slf4j
@Controller
@RequestMapping("/board")


public class BoardController {
	final static String path = "board/";
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list/{boardId}")
	String getNotice(@PathVariable Long boardId,Model model) {
		List<ForumPost> list = boardService.getboard(boardId);
		
		model.addAttribute("boardList",list);
		return path + "list";
	}
	
	

}
