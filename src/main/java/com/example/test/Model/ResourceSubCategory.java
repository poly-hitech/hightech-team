package com.example.test.Model;

import java.util.List;

//리소스 2차 카테고리
public class ResourceSubCategory {
	private Long resourceSubCategoryId;
	private String resourceSubCategorName;
	private Long resourceCategoryId;
	private List<ResourceShop> resourceShop;
	
	public Long getResourceSubCategoryId() {
		return resourceSubCategoryId;
	}
	public void setResourceSubCategoryId(Long resourceSubCategoryId) {
		this.resourceSubCategoryId = resourceSubCategoryId;
	}
	public String getResourceSubCategorName() {
		return resourceSubCategorName;
	}
	public void setResourceSubCategorName(String resourceSubCategorName) {
		this.resourceSubCategorName = resourceSubCategorName;
	}
	public Long getResourceCategoryId() {
		return resourceCategoryId;
	}
	public void setResourceCategoryId(Long resourceCategoryId) {
		this.resourceCategoryId = resourceCategoryId;
	}
	public List<ResourceShop> getResourceShop() {
		return resourceShop;
	}
	public void setResourceShop(List<ResourceShop> resourceShop) {
		this.resourceShop = resourceShop;
	}

}
