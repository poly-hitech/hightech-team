package com.example.test.Dao;

import com.example.test.Model.ShopReview;

import java.util.List;

public interface ShopReviewDao {
    void addReview(ShopReview review);
    List<ShopReview> getReviewByItemId(Long itemId);
}
