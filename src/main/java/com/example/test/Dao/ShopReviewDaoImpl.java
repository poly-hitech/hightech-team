package com.example.test.Dao;

import com.example.test.Model.ShopReview;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShopReviewDaoImpl implements ShopReviewDao{

    @Autowired
    SqlSession sql;

    @Override
    public void addReview(ShopReview review) {
        sql.insert("shopReview.addReview", review);
    }

    @Override
    public List<ShopReview> getReviewsSorted(Map<String, Object> params) {
        return sql.selectList("shopReview.getReviewsSorted", params);
    }

    @Override
    public void deleteReview(Map<String, Object> params) {
        sql.delete("shopReview.deleteReview", params);
    }

    @Override
    public Integer getReviewCountByItemId(Long itemId) {
        return sql.selectOne("shopReview.getReviewCountByItemId", itemId);
    }
}
