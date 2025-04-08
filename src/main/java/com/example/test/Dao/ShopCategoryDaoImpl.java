package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.MarketCategory;

@Repository
public class ShopCategoryDaoImpl implements ShopCategoryDao {
	
	@Autowired
	SqlSession sql;

	@Override
	public List<MarketCategory> list() {

		return sql.selectList("resourceCategory.list");
	}

	@Override
	public MarketCategory item(Long resourceCategoryId) {

		return sql.selectOne("resourceCategory.item", resourceCategoryId);
	}

	@Override
	public void update(MarketCategory item) {

		sql.update("resourceCategory.update", item);
	}

	@Override
	public void add(MarketCategory item) {

		sql.insert("resourceCategory.add", item);
	}

}
