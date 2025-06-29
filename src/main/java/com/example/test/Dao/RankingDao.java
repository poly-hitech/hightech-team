package com.example.test.Dao;

import com.example.test.Model.Ranking;

public interface RankingDao {

	void update(Long itemId);
	
	void save(Ranking ranking);

}
