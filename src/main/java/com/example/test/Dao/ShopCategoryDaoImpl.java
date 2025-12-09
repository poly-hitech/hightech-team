package com.example.test.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.test.Model.MarketCategory;
import com.example.test.Model.ResourceCategory;
import com.example.test.Model.ResourceSubCategory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    @Transactional
    @Override
    public void add(MarketCategory item) {

        ResourceCategory category = sql.selectOne("resourceCategory.findCategoryByName", item.getResourceCategory());
        Long existingCategoryId = (category != null) ? category.getResourceCategoryId() : null;

        if (existingCategoryId != null) {
            //리소스 1차 카테고리가 이미 존재한다면
            log.info("리소스 1차 카테고리가 이미 존재합니다. resourceCategoryId: {}", existingCategoryId);
            //리소스 2차 카테고리의 외래키인 resorceCategoryId를 전부 existingCategoryId로 설정
            List<ResourceSubCategory> list = item.getResourceSubCategory();
            for (ResourceSubCategory resourcesubCategory : list) {
                resourcesubCategory.setResourceCategoryId(existingCategoryId);
            }
            //리소스 2차 카테고리들 생성
            sql.insert("resourceCategory.addSubCate", list);
            return;
        } else {
            log.info("리소스 1차 카테고리가 존재하지 않습니다. 새로 생성합니다.");
            //리소스 1차 카테고리 생성
            sql.insert("resourceCategory.addCate", item.getResourceCategory());
            Long resourceCategoryId = item.getResourceCategory().getResourceCategoryId();

            log.info("resourceCategoryId: {}", resourceCategoryId);
            //리소스 1차 카테고리가 생성이 완료되었다면
            if (resourceCategoryId != null) {
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

    @Override
    public ResourceCategory getResourceCategory(Long resourceSubcategoryId) {
        // TODO Auto-generated method stub
        return sql.selectOne("resourceCategory.getResourceCategoryByResourceSub", resourceSubcategoryId);
    }

}
