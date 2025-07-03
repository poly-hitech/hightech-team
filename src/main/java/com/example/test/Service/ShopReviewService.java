package com.example.test.Service;

import com.example.test.Model.ReviewSort;
import com.example.test.Model.ShopReview;

import java.util.List;

public interface ShopReviewService {
    void addReview(ShopReview review);
    List<ShopReview> getReviewByItemId(Long itemId);
	List<ShopReview> getReviewsSorted(ReviewSort sort);
}
