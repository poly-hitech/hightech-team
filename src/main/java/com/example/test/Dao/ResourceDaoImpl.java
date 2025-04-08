package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.MarketList;
import com.example.test.Model.ResourceShop;
import com.example.test.Model.ResourceSubCategory;

@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public List<MarketList> list() {
		return sql.selectList("resourcesshop.marketList");
	}

	@Override
	public List<MarketList> list(Long userId) {
		return sql.selectList("resourcesshop.Mylist", userId);
	}

	@Override
	public void save(ResourceShop resource) {
		
		sql.insert("resourcesshop.resourceAdd", resource);
	}

}
