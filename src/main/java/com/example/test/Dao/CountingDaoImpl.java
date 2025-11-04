package com.example.test.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.Counting;

@Repository
public class CountingDaoImpl implements CountingDao {

	@Autowired
	SqlSession sql;
	
	//상점 정보에 해당하는 카운트 조회
	@Override
	public Counting searchByItemId(Long itemId) {
		// TODO Auto-generated method stub
		return sql.selectOne("counting.searchByItemId", itemId);
	}

	//상점 정보에 해당하는 카운트 업데이트
	@Override
	public void countingUpdateByItemId(Counting counting) {
		sql.update("counting.updateByItemId", counting);
	}

	
	//------------------각 카운트 초기화------------------------
	@Override
	public void resetDailyCount() {
		// TODO Auto-generated method stub
		sql.update("counting.resetDailyCount");
	}

	@Override
	public void resetWeeklyCount() {
		// TODO Auto-generated method stub
		sql.update("counting.resetWeeklyCount");
	}

	@Override
	public void resetMonthlyCount() {
		// TODO Auto-generated method stub
		sql.update("counting.resetMonthlyCount");
	}
	//------------------------------------------------------

	@Override
	public void save(Long itemId) {
		sql.insert("counting.add", itemId);		
	}

}
