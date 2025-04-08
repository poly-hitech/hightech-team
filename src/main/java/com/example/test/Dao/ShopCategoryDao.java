package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.MarketCategory;

public interface ShopCategoryDao {

	List<MarketCategory> list();

	MarketCategory item(Long resourceCategoryId);

	void update(MarketCategory item);

	void add(MarketCategory item);

}
