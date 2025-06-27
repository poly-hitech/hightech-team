package com.example.test.Service;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.Model.Market;
import com.example.test.Model.Orders;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.Users;

public interface ResourceShopService {

	List<ResourceCategory> list();

	void addResource(Long userId, Market market, List<MultipartFile> file, Model model) throws Exception;

	List<ResourceCategory> addResourcePage();

	ResourceShop getItemById(Long itemId);

	List<ResourceCategory> myResources(Long userId);

	void updateMyResource(Long itemId, Market market, MultipartFile file, Model model) throws Exception;

}
