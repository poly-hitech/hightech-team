package com.example.test.Service;

import com.example.test.Model.ShopReview;

import java.util.List;

public interface ShopReviewService {
    void addReview(ShopReview review);
	List<ShopReview> getReviewsSorted(Long itemId, String sortType, int startRow, int endRow);
    void deleteReview(Long reviewId, Long userId, Long id);
    Integer getReviewCountByItemId(Long itemId);
}
