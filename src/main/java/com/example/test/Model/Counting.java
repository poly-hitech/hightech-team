package com.example.test.Model;

public class Counting {
	private Long countingId;
	private int totalcount;
	private int dailycount;
	private int weeklycount;
	private int monthlycount;
	private Long itemId;
	
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getDailycount() {
		return dailycount;
	}
	public void setDailycount(int dailycount) {
		this.dailycount = dailycount;
	}
	public int getWeeklycount() {
		return weeklycount;
	}
	public void setWeeklycount(int weeklycount) {
		this.weeklycount = weeklycount;
	}
	public int getMonthlycount() {
		return monthlycount;
	}
	public void setMonthlycount(int monthlycount) {
		this.monthlycount = monthlycount;
	}
	public Long getCountingId() {
		return countingId;
	}
	public void setCountingId(Long countingId) {
		this.countingId = countingId;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	

}