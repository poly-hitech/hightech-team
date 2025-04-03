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
import com.example.test.Model.ResourceShop;
import com.example.test.Model.Users;
import com.example.test.Service.ResourceShopService;
import com.example.test.Util.FileUploadUtil;

@Controller
@RequestMapping("/shop")
public class MarketController {
	private final static String path = "shop";

	@Autowired
	ResourceShopService resourceShopService;
	
	//전체 상점 목록
	@GetMapping("/list")
	public String shopList(Model model) {
		List<Market> list = resourceShopService.list();
		
		model.addAttribute("list", list);
		
		return path + "/list";
	}
	
	//유저별 등록한 리소스 목록
	@GetMapping("{userId}/list")
	public String MyshopList(@PathVariable Long userId, Model model, HttpSession session) {
		
		List<Market> list = resourceShopService.list(userId);
		
		model.addAttribute("list", list);
		
		return path + "/list";
	}
	
	@PostMapping("{roleId}/add/{userId}")
	public String uploadResource(@PathVariable Long roleId, @PathVariable Long userId, ResourceShop resource, @RequestParam MultipartFile file, Model model) throws Exception {
		
		resourceShopService.addResource(resource, file, model);
		
		return path + roleId + "/add";
	}
	
}
