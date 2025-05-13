package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.ResourceSubCategory;

public interface ResourceDao {

	List<ResourceCategory> list();

	List<ResourceCategory> list(Long userId);

	void save(ResourceShop resource);

	List<ResourceCategory> addResourceShop();

}
