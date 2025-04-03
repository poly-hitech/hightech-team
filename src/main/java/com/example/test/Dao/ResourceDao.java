package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.MarketList;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.ResourceSubCategory;

public interface ResourceDao {

	List<MarketList> list();

	List<MarketList> list(Long userId);

	void save(ResourceShop resource);

}
