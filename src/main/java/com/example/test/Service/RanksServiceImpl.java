package com.example.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.RankingDao;

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

}
