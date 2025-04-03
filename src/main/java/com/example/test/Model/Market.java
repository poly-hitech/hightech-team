package com.example.test.Model;

//리소스 마켓 정보
public class Market {
	private ResourceShop resourceShop;
	private ResourceCategory resourceCategory;
	private ResourceSubCategory resourceSubCategory;
	private Ranking ranking;
	private Counting counting;
	
	public ResourceShop getResourceShop() {
		return resourceShop;
	}
	public void setResourceShop(ResourceShop resourceShop) {
		this.resourceShop = resourceShop;
	}
	public ResourceCategory getResourceCategory() {
		return resourceCategory;
	}
	public void setResourceCategory(ResourceCategory resourceCategory) {
		this.resourceCategory = resourceCategory;
	}
	public ResourceSubCategory getResourceSubCategory() {
		return resourceSubCategory;
	}
	public void setResourceSubCategory(ResourceSubCategory resourceSubCategory) {
		this.resourceSubCategory = resourceSubCategory;
	}
	public Ranking getRanking() {
		return ranking;
	}
	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}
	public Counting getCounting() {
		return counting;
	}
	public void setCounting(Counting counting) {
		this.counting = counting;
	}
	
}
