package com.example.test.Model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

//리소스 아이템 정보
public class ResourceShop {
	
	private Long itemId;
	private String itemName;
	private Long itemPrice;
	private String itemWriter;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date resourceDate = new Date();
	
	private List<ResourceFile> resourceFile;
	private String resourceContent;
	private Long rankId;
	private Long resourceSubCategoryId;
	private Long userId;
	private Ranking ranking;
	private List<OrdersDetails> ordersDetails;
	private List<ShopReview> shopReview;
	private Counting counting;
	
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
	public Long getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Long itemPrice) {
		this.itemPrice = itemPrice;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getResourceDate() {
		return resourceDate;
	}
	public void setResourceDate(Date resourceDate) {
		this.resourceDate = resourceDate;
	}
	public Long getResourceSubCategoryId() {
		return resourceSubCategoryId;
	}
	public void setResourceSubCategoryId(Long resourceSubCategoryId) {
		this.resourceSubCategoryId = resourceSubCategoryId;
	}
	public String getItemWriter() {
		return itemWriter;
	}
	public void setItemWriter(String itemWriter) {
		this.itemWriter = itemWriter;
	}
	public Ranking getRanking() {
		return ranking;
	}
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public List<OrdersDetails> getOrdersDetails() {
		return ordersDetails;
	}
	public void setOrdersDetails(List<OrdersDetails> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}
	public List<ResourceFile> getResourceFile() {
		return resourceFile;
	}
	public void setResourceFile(List<ResourceFile> resourceFile) {
		this.resourceFile = resourceFile;
	}
	public Long getRankId() {
		return rankId;
	}
	public void setRankId(Long rankId) {
		this.rankId = rankId;
	}
	public String getResourceContent() {
		return resourceContent;
	}
	public void setResourceContent(String resourceContent) {
		this.resourceContent = resourceContent;
	}
	public List<ShopReview> getShopReview() {
		return shopReview;
	}
	public void setShopReview(List<ShopReview> shopReview) {
		this.shopReview = shopReview;
	}
	public Counting getCounting() {
		return counting;
	}
	public void setCounting(Counting counting) {
		this.counting = counting;
	}

}
