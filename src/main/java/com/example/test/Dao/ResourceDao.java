package com.example.test.Dao;

import java.util.List;
import java.util.Map;

import com.example.test.Model.Market;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.ResourceSubCategory;

public interface ResourceDao {

	List<ResourceCategory> list();

	void save(ResourceShop resource);

	List<ResourceCategory> addResourceShop();

	ResourceShop getItem(Long itemId);

	ResourceShop getOnlyOneResource(Long itemId);

	List<ResourceCategory> myResources(Long userId);

	Long countAllItems();

	List<ResourceShop> getTopFromResource();

    void saveResourceFile(ResourceFile resourceFile);
}
