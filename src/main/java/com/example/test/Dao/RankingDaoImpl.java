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

	//------------------랭킹 초기화-----------------
	@Override
	public void resetDailyRank() {
		// TODO Auto-generated method stub
		sql.update("ranking.resetDailyRank");
	}

	@Override
	public void resetWeeklyRank() {
		// TODO Auto-generated method stub
		sql.update("ranking.resetWeeklyRank");
	}

	@Override
	public void resetMonthlyRank() {
		// TODO Auto-generated method stub
		sql.update("ranking.resetMonthlyRank");
	}
	//--------------------------------------------
	
}
