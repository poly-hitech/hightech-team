package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceShop;

@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	SqlSession sql;
	
	@Override
	public List<ResourceCategory> list() {
		return sql.selectList("resourceShop.marketList");
	}

	@Override
	public List<ResourceCategory> list(Long userId) {
		return sql.selectList("resourceShop.Mylist", userId);
	}

	@Override
	public void save(ResourceShop resource) {
		
		sql.insert("resourceShop.resourceAdd", resource);
	}

}
