package com.example.test.Dao;

import com.example.test.Model.ShopReview;

import java.util.List;

public interface ShopReviewDao {
    void addReview(ShopReview review);
    List<ShopReview> getReviewsSorted(Long itemId, String sortType, int startRow, int endRow);
    void deleteReview(Long reviewId, Long itemId, Long userId);
    Integer getReviewCountByItemId(Long itemId);
}
