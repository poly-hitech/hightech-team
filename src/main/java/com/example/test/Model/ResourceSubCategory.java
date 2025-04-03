package com.example.test.Model;

//리소스 2차 카테고리
public class ResourceSubCategory {
	private Long resourceSubCategoryId;
	private String resourceSubCategorName;
	private Long resourceCategoryId;
	
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

}
