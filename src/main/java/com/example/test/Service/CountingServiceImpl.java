package com.example.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.CountingDao;

@Service
public class CountingServiceImpl implements CountingService {

	@Autowired
	CountingDao countingDao;
	
	//-----------------------각 count초기화------------------------------------
	@Override
	public void resetDailyCount() {
		
		countingDao.resetDailyCount();
	}

	@Override
	public void resetWeeklyCount() {
		// TODO Auto-generated method stub
		countingDao.resetWeeklyCount();
	}

	@Override
	public void resetMonthlyCount() {
		// TODO Auto-generated method stub
		countingDao.resetMonthlyCount();
	}
	//-----------------------------------------------------------------------

}
