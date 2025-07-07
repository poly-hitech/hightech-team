package com.example.test.Service;

import com.example.test.Dao.ShopReviewDao;
import com.example.test.Model.ShopReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShopReviewServiceImpl implements ShopReviewService{

    @Autowired
    ShopReviewDao shopReviewDao;

    @Override
    public void addReview(ShopReview review) {
        shopReviewDao.addReview(review);
    }

	@Override
	public List<ShopReview> getReviewsSorted(Map<String, Object> params) {
		return shopReviewDao.getReviewsSorted(params);
	}

    @Override
    public void deleteReview(Map<String, Object> params) {
        shopReviewDao.deleteReview(params);
    }

    @Override
    public Integer getReviewCountByItemId(Long itemId) {
        return shopReviewDao.getReviewCountByItemId(itemId);
    }

    @Override
    public void updateReview(ShopReview review) {
        shopReviewDao.updateReview(review);
    }
}
