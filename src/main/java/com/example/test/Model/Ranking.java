package com.example.test.Model;

//랭킹 정보 
public class Ranking {
	private Long rankId;
	private Long totalRank;
	private Long dailyRank;
	private Long weeklyRank;
	private Long monthlyRank;
	private Long itemId;
	
	public Long getRankingId() {
		return rankId;
	}
	public void setRankingId(Long rankId) {
		this.rankId = rankId;
	}
	public Long getTotalRank() {
		return totalRank;
	}
	public void setTotalRank(Long totalRank) {
		this.totalRank = totalRank;
	}
	public Long getDailyRank() {
		return dailyRank;
	}
	public void setDailyRank(Long dailyRank) {
		this.dailyRank = dailyRank;
	}
	public Long getWeeklyRank() {
		return weeklyRank;
	}
	public void setWeeklyRank(Long weeklyRank) {
		this.weeklyRank = weeklyRank;
	}
	public Long getMonthlyRank() {
		return monthlyRank;
	}
	public void setMonthlyRank(Long monthlyRank) {
		this.monthlyRank = monthlyRank;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
}
