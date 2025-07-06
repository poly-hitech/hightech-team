package com.example.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.test.Model.Users;
import com.example.test.Service.UsersService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {
	final String path = "users/";
	
	@Autowired
	UsersService userService;
	

	@GetMapping("/update/{userId:.+}")	
	String update(@PathVariable Long userId, Model model){

		Users item = userService.item(userId);
		log.info("닉네임 넘어오는거 확인: {}", item.getNickname());
		
		model.addAttribute("item", item);
		
		return path + "update" ;
	}

	@PostMapping("/update/{userId:.+}")
	String update(@PathVariable Long userId, @RequestParam(value = "image", required = false) MultipartFile profileImage, 
			Users item, Model model) throws Exception {
		item.setUserId(userId);
		
		userService.update(item, profileImage, model);
		
		return "redirect:{userId}";
	}
	
	@GetMapping("/delete/{userId:.+}")
	String delete(@PathVariable Long userId) {
		userService.delete(userId);
		
		return "redirect:../";
	}
}
