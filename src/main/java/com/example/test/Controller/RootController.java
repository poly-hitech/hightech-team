package com.example.test.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.test.Model.Users;
import com.example.test.Service.ResourceShopService;
import com.example.test.Service.UsersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RootController {
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/")
	String index() {
		
		return "index";
	}
	
	@GetMapping("/logout")
	String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	String login() {
		return "login";
	}
	
	@PostMapping("/login")
	String login(Users item, HttpSession session) {
		Boolean result = usersService.login(item);
		
		if (result) {
            session.setAttribute("member", item);
            log.info("session에 담긴 값을 확인 합니다.", session.toString());
           
            return "redirect: /";
        } else {
        	log.info("로그인에 실패했습니다. 아이디와 비밀번호를 확인하세요.");
        		
            return "login";
        }
	}

}
