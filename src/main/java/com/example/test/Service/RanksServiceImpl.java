package com.example.test.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.RankingDao;
import com.example.test.Model.ResourceShop;

@Service
public class RanksServiceImpl implements RanksService {

	@Autowired
	RankingDao rankingDao;
	
	@Override
	public void resetDailyRank() {
		// TODO Auto-generated m
		rankingDao.resetDailyRank();
	}

	@Override
	public void resetWeeklyRank() {
		// TODO Auto-generated method stub
		rankingDao.resetWeeklyRank();
	}

	@Override
	public void resetMonthlyRank() {
		// TODO Auto-generated method stub
		rankingDao.resetMonthlyRank();
	}

	@Override
	public List<ResourceShop> getTotalTop(int limit) {
		// TODO Auto-generated method stub
		return rankingDao.getTotalRank(limit);
	}

	@Override
	public List<ResourceShop> getdailyTop(int limit) {
		// TODO Auto-generated method stub
		return rankingDao.getDailyRank(limit);
	}

	@Override
	public List<ResourceShop> getweeklyTop(int limit) {
		// TODO Auto-generated method stub
		return rankingDao.getWeeklyRank(limit);
	}

	@Override
	public List<ResourceShop> getmonthlyTop(int limit) {
		// TODO Auto-generated method stub
		return rankingDao.getMonthlyRank(limit);
	}

}
