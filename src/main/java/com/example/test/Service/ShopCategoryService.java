package com.example.test.Service;

import java.util.List;

import com.example.test.Model.MarketCategory;

public interface ShopCategoryService {

	List<MarketCategory> list();

	MarketCategory item(Long resourceCategoryId);

	void updateAdminCategory(MarketCategory item);

	void addAdminCategory(MarketCategory item);

}
