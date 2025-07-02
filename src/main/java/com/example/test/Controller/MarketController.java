package com.example.test.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Model.Market;
import com.example.test.Model.Ranking;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.ResourceSubCategory;
import com.example.test.Service.OrdersService;
import com.example.test.Service.ResourceShopService;
import com.example.test.Service.ShopCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shop")
public class MarketController {
	private final static String path = "shop/";

	@Autowired
	ResourceShopService resourceShopService;

	@Autowired
	ShopCategoryService resourceCategoryService;

	@Autowired
	OrdersService ordersService;

	// 전체 상점 목록
	@GetMapping("public/list")
	String shopList(Model model, HttpSession session) {
		List<ResourceCategory> list = resourceShopService.list();

		log.info("1차 카테고리 객체 확인: {}" + list);

		// 넘어오는 값을 확인하기 위한 코드
		for (int a = 0; a < list.size(); a++) {
			ResourceCategory rc = list.get(a);
			log.info("리소스 사이즈 : {}" + list.size() + "리소스 1차 번호: {}" + rc.getResourceCategoryId());
			// 서브 카테고리 리스트를 호출
			List<ResourceSubCategory> sc = rc.getResourceSubCategory();
			// 호출한 서브카테고리 리스트 만큼 반복시켜 서브 카테고리 각 객체 불러옴
			for (ResourceSubCategory rsc : sc) {
				Long id = rsc.getResourceSubCategoryId();
				String name = rsc.getResourceSubCategoryName();

				log.info("2차 카테고리 번호: {}" + id);
				log.info("2차 카테고리 이름: {}" + name);
				// 불러온 서브카테고리속에 속한 상점 정보 리스트를 불러옴
				List<ResourceShop> rsList = rsc.getResourceShop();
				// 불러온 상점정보리스트에 담긴 상점정보 단일 객체 호출
				for (ResourceShop resourceShop : rsList) {
					Long shopId = resourceShop.getItemId();
					String itemName = resourceShop.getItemName();
					log.info("상점 아이템 번호: {}" + shopId);
					log.info("상점 아이템 이름: {}" + itemName);
					log.info("상점 아이템 배경이미지: {}", resourceShop.getResourceImage());

					// 해당 상점정보의 랭킹 호출
					Ranking rank = resourceShop.getRanking();
					Long rankId = rank.getRankingId();
					log.info("상점 아이템 랭킹번호: {}" + rankId);

					// 해당 상품의 판매량 호출

					// 상점 정보 속에 속한 파일 호출
					List<ResourceFile> rfList = resourceShop.getResourceFile();
					// 위와 동일한 방식
					for (ResourceFile resourceFile : rfList) {
						Long fileId = resourceFile.getItemId();
						String fileName = resourceFile.getResourceFileName();
						log.info("상점 파일경로: {}" + fileName);
						// 가져온 파일명에서 uuid를 제외한 진짜 파일명을 불러오기위함.
						if (fileName != null) {
							int pos = fileName.lastIndexOf("_");

							if (pos > 0) {
								fileName = fileName.substring(pos + 1);
								log.info("상점 파일 번호: {}" + fileId);
								log.info("상점 파일명: {}" + fileName);
								// 보여줄 파일명 설정
							}
						}
					}
				}
			}
		}

		model.addAttribute("list", list);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String userAsString;
			userAsString = objectMapper.writeValueAsString(list);
			model.addAttribute("list2", userAsString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path + "list";
	}

	@GetMapping("{roleId}/add/{userId}")
	String addResourcePage(@PathVariable Long roleId, @PathVariable Long userId, Model model, HttpSession session) {
		List<ResourceCategory> category = resourceShopService.addResourcePage();

		// 단순 값넘어오는거 확인용 -------------------------------------------------------
		for (int a = 0; a < category.size(); a++) {
			ResourceCategory rc = category.get(a);
			log.info("리소스 사이즈 : {}" + category.size() + "리소스 1차 번호: {}" + rc.getResourceCategoryId());
			List<ResourceSubCategory> sc = rc.getResourceSubCategory();
			for (ResourceSubCategory rsc : sc) {
				Long id = rsc.getResourceSubCategoryId();
				String name = rsc.getResourceSubCategoryName();

				log.info("2차 카테고리 번호: {}" + id);
				log.info("2차 카테고리 이름: {}" + name);
			}
		}
		// --------------------------------------------------------------------------

		model.addAttribute("category", category);
		return path + "add";
	}

	// 리소스 상품 등록
	@PostMapping("{roleId}/add/{userId}")
	String uploadResource(@PathVariable Long roleId, @PathVariable Long userId, Market market,
			@RequestParam(value = "resourceFile", required = false) List<MultipartFile> file,
			@RequestParam(value = "resourceImage", required = false) MultipartFile resourceImage,
						  Model model) throws Exception {

		resourceShopService.addResource(userId, market, file, resourceImage, model);

		return path + "add";
	}

	// 리소스 상품 상세
	@GetMapping("/detail")
	public String showDetail(@RequestParam("itemId") Long itemId, Model model) {
		ResourceShop resourceShop = resourceShopService.getItemById(itemId);
		Long resourceSubcategoryId = resourceShop.getResourceSubCategoryId();
		ResourceCategory resourceCategory = resourceCategoryService.getResourceCategory(resourceSubcategoryId);

		log.info("아이템명 확인: {}", resourceShop.getItemName());
		log.info("아이템 번호 확인: {}", resourceShop.getItemId());

		// itemId로 상세정보 조회 로직
		model.addAttribute("shop", resourceShop);

		try {
			model.addAttribute("shop2", new ObjectMapper().writeValueAsString(resourceShop));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return path + "detail";
	}

	// 리소스 상품 구매
	@PostMapping("/detail/{userId}/{itemId}")
	@ResponseBody // responseBody를 사용하면 문자열을 반환하게됨
	public String buyResource(@PathVariable Long userId, @PathVariable Long itemId,
			@RequestBody List<ResourceShop> shop)
			throws Exception {

		log.info("구매자 이름: {}", userId);
		log.info("구매 아이템 번호: {}", itemId);
		ordersService.buyResource(userId, itemId, shop);
		log.info("구매 완료");
		return "success";
	}

	// 판매 물품 및 내역
	@GetMapping("/salesDetail/{roleId}")
	public String salesResources() {

		return path + "salesDetail"; 
	}

	//로그인 한 유저가 등록한 상품
	@GetMapping("myResources/{userId}")
	public String myResources(@PathVariable Long userId, Model model) {
		List<ResourceCategory> category = resourceShopService.myResources(userId);
		log.info("내가 등록한 상품 리스트: {}", category);
		model.addAttribute("list", category);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String userAsString;
			userAsString = objectMapper.writeValueAsString(category);
			model.addAttribute("list2", userAsString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path + "myResources";
	}
	
	
	  //리소스 상품 수정  
	  @PostMapping("/updateMyResource/{itemId}")
	  String updateResource(@PathVariable Long itemId,
	  Market market, @RequestParam MultipartFile file, Model model) throws
	  Exception {
	  
	  resourceShopService.updateMyResource(itemId, market, file, model);
	  
	  return path + "updateMyResource";
	  }
	 

	/*
	 * @ResponseBody //responseBody를 사용하면 문자열을 반환하게됨(spring입장에서는 view가 아닌 데이터로 인식하고
	 * 클라이언트로 전송함)
	 * 
	 * @PutMapping("/update") public String updateResource(@RequestBody Cart cart )
	 * throws Exception { resourceShopService.updateResource(cart); return "OK"; }
	 */



}
