package com.example.test.Dao;

import com.example.test.Model.ShopReview;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShopReviewDaoImpl implements ShopReviewDao{

    @Autowired
    SqlSession sql;

    @Override
    public void addReview(ShopReview review) {
        sql.insert("shopReview.addReview", review);
    }
}
