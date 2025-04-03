package com.example.test.Model;

public class Counting {
	private Long countingId;
	private int totalcount;
	private int dailycount;
	private int weekcount;
	private int monthcount;
	
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
	public int getWeekcount() {
		return weekcount;
	}
	public void setWeekcount(int weekcount) {
		this.weekcount = weekcount;
	}
	public int getMonthcount() {
		return monthcount;
	}
	public void setMonthcount(int monthcount) {
		this.monthcount = monthcount;
	}
	public Long getCountingId() {
		return countingId;
	}
	public void setCountingId(Long countingId) {
		this.countingId = countingId;
	}
	

}