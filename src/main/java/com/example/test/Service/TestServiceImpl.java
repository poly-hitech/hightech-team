package com.example.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Dao.TestDao;
import com.example.test.Model.TestDto;

@Service
public class TestServiceImpl implements TestSerivce {

	@Autowired
	TestDao testDao;
	
	@Override
	public void add(TestDto test) {
		
		testDao.add(test);
	}

}
