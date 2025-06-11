package com.example.test.Service;

import java.util.List;

import com.example.test.Model.MarketCategory;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceSubCategory;

public interface ShopCategoryService {

	List<MarketCategory> list();

	MarketCategory item(Long resourceCategoryId);

	void updateAdminCategory(ResourceSubCategory item);

	void addAdminCategory(MarketCategory item);

	ResourceCategory getResourceCategory(Long resourceSubcategoryId);

}
