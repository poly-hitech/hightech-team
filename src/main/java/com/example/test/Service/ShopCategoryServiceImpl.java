package com.example.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.ShopCategoryDao;
import com.example.test.Model.MarketCategory;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceSubCategory;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
	
	@Autowired
	ShopCategoryDao dao;

	@Override
	public List<MarketCategory> list() {

		return dao.list();
	}

	@Override
	public MarketCategory item(Long resourceCategoryId) {

		return dao.item(resourceCategoryId);
	}

	@Override
	public void updateAdminCategory(ResourceSubCategory item) {
		
		dao.update(item);
	}

	@Override
	public void addAdminCategory(MarketCategory item) {

		dao.add(item);
	}

	@Override
	public ResourceCategory getResourceCategory(Long resourceSubcategoryId) {
		// TODO Auto-generated method stub
		return dao.getResourceCategory(resourceSubcategoryId);
	}

}
