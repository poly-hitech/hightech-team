package com.example.test.Model;

import java.util.List;

//리소스 카테고리
public class ResourceCategory {
	private Long resourceCategoryId;
	private String resourceCategoryName;
	private List<ResourceSubCategory> resourceSubCategory;
	
	public Long getResourceCategoryId() {
		return resourceCategoryId;
	}
	public void setResourceCategoryId(Long resourceCategoryId) {
		this.resourceCategoryId = resourceCategoryId;
	}
	public String getResourceCategoryName() {
		return resourceCategoryName;
	}
	public void setResourceCategoryName(String resourceCategoryName) {
		this.resourceCategoryName = resourceCategoryName;
	}
	public List<ResourceSubCategory> getResourceSubCategory() {
		return resourceSubCategory;
	}
	public void setResourceSubCategory(List<ResourceSubCategory> resourceSubCategory) {
		this.resourceSubCategory = resourceSubCategory;
	}
	

}
