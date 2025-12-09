package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.Ranking;
import com.example.test.Model.ResourceShop;

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
	
	@Override
	public void save(Ranking ranking) {
		sql.insert("ranking.add", ranking);		
	}

	@Override
	public List<ResourceShop> getTotalRank(int limit) {
		// TODO Auto-generated method stub
		return sql.selectList("ranking.getTotalRank", limit);
	}

	@Override
	public List<ResourceShop> getDailyRank(int limit) {
		// TODO Auto-generated method stub
		return sql.selectList("ranking.getDailyRank", limit);
	}

	@Override
	public List<ResourceShop> getWeeklyRank(int limit) {
		// TODO Auto-generated method stub
		return sql.selectList("ranking.getWeeklyRank", limit);
	}

	@Override
	public List<ResourceShop> getMonthlyRank(int limit) {
		// TODO Auto-generated method stub
		return sql.selectList("ranking.getMonthlyRank", limit);
	}

}
