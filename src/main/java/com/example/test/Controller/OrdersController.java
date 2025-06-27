package com.example.test.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.test.Model.Market;
import com.example.test.Service.OrdersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrdersController {
	private final static String path = "orders/";
	
	@Autowired
	OrdersService ordersService;

	// 내가 구매한 상품 목록
	@GetMapping("purchasedResources/{userId}")
	String purchasedResources(@PathVariable Long userId, Model model) {
		List<Market> marketList = ordersService.purchasedResources(userId);
		model.addAttribute("marketList", marketList);
		log.info("구매 아이템 번호: {}", marketList);
		return path + "purchasedResources";
	}
}
