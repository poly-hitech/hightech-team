package com.example.test.Dao;

import java.util.List;

import com.example.test.Model.Ranking;
import com.example.test.Model.ResourceShop;

public interface RankingDao {

	void update(Long itemId);
	
	void save(Ranking ranking);

	void resetDailyRank();

	void resetWeeklyRank();

	void resetMonthlyRank();

	List<ResourceShop> getTotalRank(int limit);
	List<ResourceShop> getDailyRank(int limit);
	List<ResourceShop> getWeeklyRank(int limit);
	List<ResourceShop> getMonthlyRank(int limit);

}
