package com.example.test.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.test.Model.MyOrderList;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
import com.example.test.Model.Users;

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
		List<MyOrderList> purchasedList = ordersService.purchasedResources(userId);
		model.addAttribute("purchasedList", purchasedList);
		for(MyOrderList market : purchasedList) {
			log.info("구매한 상품: {}", market);
			for(ResourceFile file : market.getResourceFile()) {
				log.info("파일 정보: {}", file.getResourceFileName());
			}
		}
		return path + "purchasedResources";
	}

	// 내가 판매한 상품 목록
	@GetMapping("mySalesList/{userId}")
		String mySalesList(@PathVariable Long userId, Model model, HttpSession session) {
		//판매자 닉네임 조회
		Users users = (Users)session.getAttribute("member");
		String nickname = users.getNickname();

		//넘어온 userId와 세션의 userId가 같은지 확인
		if(userId == null || !userId.equals(users.getUserId())) {
			List<MyOrderList> purchasedList = ordersService.mySalesList(nickname);
			model.addAttribute("purchasedList", purchasedList);
			
			for(MyOrderList market : purchasedList) {
				log.info("구매한 상품: {}", market);
				for(ResourceFile file : market.getResourceFile()) {
					log.info("파일 정보: {}", file.getResourceFileName());
				}
			}
		}

		return path + "mySalesList";
	}

	
}
