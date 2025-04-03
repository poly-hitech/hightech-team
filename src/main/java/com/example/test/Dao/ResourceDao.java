package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.Market;
import com.example.test.Model.ResourceShop;

public interface ResourceDao {

	List<Market> list();

	List<Market> list(Long userId);

	void save(ResourceShop resource);

}
