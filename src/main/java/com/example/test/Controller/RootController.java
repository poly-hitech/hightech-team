package com.example.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.test.Model.Users;
import com.example.test.Service.ResourceShopService;

@Controller
public class RootController {
	
	@GetMapping("/")
	String index() {
		
		return "index";
	}

}
