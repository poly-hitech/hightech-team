package com.example.test.Model;

import java.time.LocalDate;

//리소스 아이템 정보
public class ResourceShop {
	
	private Long itemId;
	private String itemName;
	private Long itemPrice;
	private LocalDate resourceDate;
	private String resourceFile;
	private Long rankingId;
	private Long resourceSubCategoryId;
	private Long userId;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getResourceFile() {
		return resourceFile;
	}
	public void setResourceFile(String resourceFile) {
		this.resourceFile = resourceFile;
	}
	public Long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Long getRankingId() {
		return rankingId;
	}
	public void setRankingId(Long rankingId) {
		this.rankingId = rankingId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDate getResourceDate() {
		return resourceDate;
	}
	public void setResourceDate(LocalDate resourceDate) {
		this.resourceDate = resourceDate;
	}
	public Long getResourceSubCategoryId() {
		return resourceSubCategoryId;
	}
	public void setResourceSubCategoryId(Long resourceSubCategoryId) {
		this.resourceSubCategoryId = resourceSubCategoryId;
	}

}
