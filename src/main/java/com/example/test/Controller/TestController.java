package com.example.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.Model.TestDto;
import com.example.test.Service.TestSerivce;

@Controller
@RequestMapping("/")
public class TestController {

	@Autowired
	TestSerivce testService;
	
	@GetMapping("/test")
	public String getmethod() {
		return "test";
	}
	
	@PostMapping("/test")
	public String getmethod(TestDto test) {
		testService.add(test);
		
		return "test";
	}
}
