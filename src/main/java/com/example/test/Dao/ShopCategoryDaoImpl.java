package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.MarketCategory;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceSubCategory;

@Repository
public class ShopCategoryDaoImpl implements ShopCategoryDao {
	
	@Autowired
	SqlSession sql;

	//모든 리소스 1차, 2차 카테고리 호출 
	@Override
	public List<MarketCategory> list() {

		return sql.selectList("resourceCategory.list");
	}

	//선택된 리소스 1차 카테고리 호출
	@Override
	public MarketCategory item(Long resourceCategoryId) {

		return sql.selectOne("resourceCategory.item", resourceCategoryId);
	}

	//리소스 2차 카테고리 내용 변경
	@Override
	public void update(ResourceSubCategory item) {

		sql.update("resourceSubCategory.update", item);
	}

	//리소스 1차, 2차 카테고리 추가
	@Override
	public void add(MarketCategory item) {
		
		ResourceCategory resourceCategory = item.getResourceCategory();

		//리소스 1차 카테고리 생성
		sql.insert("resourceCategory.addCate", resourceCategory);
		
		//생성한 리소스 1차 카테고리 호출
		resourceCategory = sql.selectOne("resourceCategory.selectCategory", item.getResourceCategory().getResourceCategoryId());
		
		//리소스 1차 카테고리가 생성이 완료되었다면 
		if(resourceCategory != null) {
			//생성된 리소스 1차 카테고리의 기본키 추출
			Long resourceCategoryId = resourceCategory.getResourceCategoryId();

			//클라이언트에서 등록된 리소스 2차 카테고리 정보들 호출
			List<ResourceSubCategory> list = item.getResourceSubCategory();
			
			//리소스 2차 카테고리의 외래키인 resorceCategoryId를 전부 resourceCategoryId로 설정
			for (ResourceSubCategory resourcesubCategory : list) {
				resourcesubCategory.setResourceCategoryId(resourceCategoryId);
			}
			
			//리소스 2차 카테고리들 생성
			sql.insert("resourceCategory.addSubCate", list);
		}

	}

}
