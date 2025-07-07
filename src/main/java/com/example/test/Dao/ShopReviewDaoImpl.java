package com.example.test.Dao;

import com.example.test.Model.ShopReview;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
    public List<ShopReview> getReviewsSorted(Long itemId, String sortType, int startRow, int endRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("sortType", sortType);
        map.put("startRow", startRow);
        map.put("endRow", endRow);
        return sql.selectList("shopReview.getReviewsSorted", map);
    }

    @Override
    public void deleteReview(Long reviewId, Long itemId, Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("reviewId", reviewId);
        map.put("itemId", itemId);
        map.put("userId", userId);
        sql.delete("shopReview.deleteReview", map);
    }

    @Override
    public Integer getReviewCountByItemId(Long itemId) {
        return sql.selectOne("shopReview.getReviewCountByItemId", itemId);
    }
}
