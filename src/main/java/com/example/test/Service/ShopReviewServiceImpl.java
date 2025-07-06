package com.example.test.Service;

import com.example.test.Dao.ShopReviewDao;
import com.example.test.Model.ShopReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopReviewServiceImpl implements ShopReviewService{

    @Autowired
    ShopReviewDao shopReviewDao;

    @Override
    public void addReview(ShopReview review) {
        shopReviewDao.addReview(review);
    }

    @Override
    public List<ShopReview> getReviewByItemId(Long itemId) {
        return shopReviewDao.getReviewByItemId(itemId);
    }

	@Override
	public List<ShopReview> getReviewsSorted(Long itemId, String sortType) {
		return shopReviewDao.getReviewsSorted(itemId, sortType);
	}
}
