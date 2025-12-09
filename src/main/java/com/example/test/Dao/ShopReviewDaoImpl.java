package com.example.test.Dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Model.ShopReview;

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

    @Override
    public void updateReview(ShopReview review) {
        sql.update("shopReview.updateReview", review);
    }
}
