package com.example.test.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.Ranking;

@Repository
public class RankingDaoImpl implements RankingDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public void update(Long itemId) {
		sql.update("ranking.updateByDailyrankByitemId", itemId);
		sql.update("ranking.updateByWeeklyrankByitemId", itemId);
		sql.update("ranking.updateByMonthlyrankByitemId", itemId);
		sql.update("ranking.updateByTotalrankByitemId", itemId);
	}

	@Override
	public void save(Ranking ranking) {
		sql.insert("ranking.add", ranking);		
	}

}
