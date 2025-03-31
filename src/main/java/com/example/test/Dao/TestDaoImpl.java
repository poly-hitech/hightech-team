package com.example.test.Dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.TestDto;


@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public void add(TestDto test) {

		sql.insert("test.add", test);
	}

}
