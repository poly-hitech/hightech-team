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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Model.Market;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceSubCategory;
import com.example.test.Service.ResourceShopService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shop")
public class MarketController {
	private final static String path = "shop/";

	@Autowired
	ResourceShopService resourceShopService;
	
	//전체 상점 목록
	@GetMapping("public/list")
	String shopList(Model model, HttpSession session) {
		List<ResourceCategory> list = resourceShopService.list();
		
		for(int a =0;a < list.size(); a++){
			ResourceCategory rc = list.get(a);
			log.info("리소스 사이즈 : {}"+ list.size() + "리소스 1차 번호: {}" + rc.getResourceCategoryId());
			List<ResourceSubCategory> sc = rc.getResourceSubCategory();
			for(ResourceSubCategory rsc: sc) {
				Long id = rsc.getResourceSubCategoryId();
				String name = rsc.getResourceSubCategoryName();
				
				log.info("2차 카테고리 번호: {}" + id);
				log.info("2차 카테고리 이름: {}" + name);
			}
		}
		
		model.addAttribute("list", list);
		
		return path + "list";
	}
	
	//유저별 등록한 리소스 목록
	@GetMapping("{roleId}/{userId}/mylist")
	String MyshopList(@PathVariable Long userId, @PathVariable Long roleId, Model model, HttpSession session) {
		
		List<ResourceCategory> list = resourceShopService.list(userId);
		
		model.addAttribute("list", list);
		
		return path + "mylist";
	}
	
	@GetMapping("{roleId}/add/{userId}")
	String addResourcePage(@PathVariable Long roleId, @PathVariable Long userId, Model model, HttpSession session){
		List<ResourceCategory> category = resourceShopService.addResourcePage();
		
		for(int a =0;a < category.size(); a++){
			ResourceCategory rc = category.get(a);
			log.info("리소스 사이즈 : {}"+ category.size() + "리소스 1차 번호: {}" + rc.getResourceCategoryId());
			List<ResourceSubCategory> sc = rc.getResourceSubCategory();
			for(ResourceSubCategory rsc: sc) {
				Long id = rsc.getResourceSubCategoryId();
				String name = rsc.getResourceSubCategoryName();
				
				log.info("2차 카테고리 번호: {}" + id);
				log.info("2차 카테고리 이름: {}" + name);
			}
		}
		
		model.addAttribute("category", category);
		return path + "add";
	}
	
	//리소스 상품 등록
	@PostMapping("{roleId}/add/{userId}")
	String uploadResource(@PathVariable Long roleId, @PathVariable Long userId, Market market, @RequestParam MultipartFile file, Model model) throws Exception {
		
		resourceShopService.addResource(userId, market, file, model);
		
		return path + "add";
	}
	
	/*
	//리소스 상품 수정
	@PostMapping("{roleId}/add/{itemId}")
	String updateResource(@PathVariable Long roleId, @PathVariable Long itemId, Market market, @RequestParam MultipartFile file, Model model) throws Exception {
		
		resourceShopService.Resource(itemId, market, file, model);
		
		return path + roleId + "update";
	}*/
	
}
