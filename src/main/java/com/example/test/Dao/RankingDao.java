package com.example.test.Dao;

public interface RankingDao {

	void update(Long itemId);

	void resetDailyRank();

	void resetWeeklyRank();

	void resetMonthlyRank();

}
