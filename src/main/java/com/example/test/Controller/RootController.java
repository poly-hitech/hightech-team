package com.example.test.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.test.Model.ForumPost;
import com.example.test.Model.HostAdress;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.Users;
import com.example.test.Service.BoardService;
import com.example.test.Service.ResourceShopService;
import com.example.test.Service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RootController {
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	ResourceShopService resourceService;
	
	@Autowired
	BoardService boardService;
	
	//게시판 작업 끝나면 불러와야 함.
	
	@GetMapping("/")
	String index(Model model) {
		List<ResourceShop> resource = resourceService.getTopFromResource();
		model.addAttribute("popularResources", resource);
		
		//최근 게시물 
		List<ForumPost> notice = boardService.getBoardListOnlyFive(1L);
		List<ForumPost> job = boardService.getBoardListOnlyFive(2L);
		List<ForumPost> board = boardService.getBoardListOnlyFive(3L);
		
		//공지사항
		model.addAttribute("notice", notice);
		//취업 정보 공유
		model.addAttribute("job", job);
		//자유 게시판
		model.addAttribute("board", board);
		
		return "index";
	}
	
	//회원가입
	@GetMapping("/register")
	String register() {
		return "register";
	}
	
	@PostMapping("/register")
	String register(Users item) {
		
		usersService.add(item);
		
		return "redirect:/";
	}
	
	//로그인
	@GetMapping("/logout")
	String logout(HttpSession session, HostAdress hostAddress) {
		session.invalidate();
		
//		return "redirect:" + hostAddress.getHost();
		return "redirect:/";
	}
	
	@GetMapping("/login")
	String login() {
		return "login";
	}
	
	@PostMapping("/login")
	String login(Users item, HttpSession session, Model model) {
		Boolean result = usersService.login(item);
		
		if (result) {
            session.setAttribute("member", item);
            Users member = (Users) session.getAttribute("member");
            log.info("session에 담긴 값을 확인 합니다: " + member.getBirthday());
            
            if(item.getRoleId() == 0L) {
            	log.info("회원탈퇴한 아이디입니다. 로그인에 실패했습니다. 새로 가입해주세요.");
            	return "register";
            }
            model.addAttribute("message", "로그인에 성공했습니다.");
            model.addAttribute("redirect", true);
            return "login";
        } else {
        	log.info("로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요.");
        	model.addAttribute("message", "아이디 또는 비밀번호가 잘못되었습니다.");
            model.addAttribute("redirect", false);
            return "login";
        }
	}

	//상단 메뉴바
	@GetMapping("/menu")
	String menu() {
		return "menu";
	}
	
	@GetMapping("/salesRank")
	String rank() {
		return "rank";
	}
	
}
