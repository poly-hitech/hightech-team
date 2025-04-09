package com.example.test.Model;

import java.util.List;

public class MarketCategory {

	private List<ResourceSubCategory> resourceSubCategory;
	private ResourceCategory resourceCategory;
	
	public List<ResourceSubCategory> getResourceSubCategory() {
		return resourceSubCategory;
	}
	public void setResourceSubCategory(List<ResourceSubCategory> resourceSubCategory) {
		this.resourceSubCategory = resourceSubCategory;
	}
	public ResourceCategory getResourceCategory() {
		return resourceCategory;
	}
	public void setResourceCategory(ResourceCategory resourceCategory) {
		this.resourceCategory = resourceCategory;
	}
}
