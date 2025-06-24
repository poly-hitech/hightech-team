package com.example.test.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RankingDaoImpl implements RankingDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public void update(Long itemId) {
		sql.update("ranking.updateByDailyrankByitemId", itemId);
		sql.update("ranking.updateByWeeklylyrankByitemId", itemId);
		sql.update("ranking.updateByMonthlyrankByitemId", itemId);
		sql.update("ranking.updateByTotalrankByitemId", itemId);
	}

}
