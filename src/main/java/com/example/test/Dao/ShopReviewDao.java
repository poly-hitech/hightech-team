package com.example.test.Dao;

import com.example.test.Model.ShopReview;

import java.util.List;
import java.util.Map;

public interface ShopReviewDao {
    void addReview(ShopReview review);
    List<ShopReview> getReviewsSorted(Map<String, Object> params);
    void deleteReview(Map<String, Object> params);
    Integer getReviewCountByItemId(Long itemId);
    void updateReview(ShopReview review);
}
