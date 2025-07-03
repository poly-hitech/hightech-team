package com.example.test.Dao;

import com.example.test.Model.ReviewSort;
import com.example.test.Model.ShopReview;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public List<ShopReview> getReviewByItemId(Long itemId) {
        return sql.selectList("shopReview.getReviewByItemId", itemId);
    }

    @Override
    public List<ShopReview> getReviewsSorted(ReviewSort sort) {
        return sql.selectList("shopReview.getReviewsSorted", sort);
    }
}
