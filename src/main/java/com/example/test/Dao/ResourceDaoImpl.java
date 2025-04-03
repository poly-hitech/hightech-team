package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.Market;
import com.example.test.Model.ResourceShop;

@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public List<Market> list() {
		// TODO Auto-generated method stub
		return sql.selectList("market.list");
	}

	@Override
	public List<Market> list(Long userId) {
		// TODO Auto-generated method stub
		return sql.selectList("market.list", userId);
	}

	@Override
	public void save(ResourceShop resource) {
		
		sql.insert("market.add", resource);
	}

}
