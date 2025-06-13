package com.example.test.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceFile;
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

	//리소스 정보 등록
	@Override
	public void save(ResourceShop resource) {
		
		sql.insert("resourceShop.resourceAdd", resource);
	}

	@Transactional
	@Override
	public List<ResourceCategory> addResourceShop() {
		// TODO Auto-generated method stub
		return sql.selectList("resourceCategory.addResourcePage");
	}

	//파일 경로 등록
	@Override
	public void save(Map<String, Object> params) {
		// TODO Auto-generated method stub
		sql.selectList("resourceShop.addResourceFile", params);
	}

	@Override
	public ResourceShop getItem(Long itemId) {
		// TODO Auto-generated method stub
		return sql.selectOne("resourceShop.getItemInfo", itemId);
	}

	@Override
	public ResourceShop getOnlyOneResource(Long itemId) {

		return sql.selectOne("resourceShop.getOnlyOneItem", itemId);
	}

}
