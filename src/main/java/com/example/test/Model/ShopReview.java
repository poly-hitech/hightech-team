package com.example.test.Model;

import java.util.Date;

//상품 리뷰
public class ShopReview {
	private Long reviewId;
	private String reviewContent;
	private Date reviewDate;
	private Double reviewCount; // 별점
	private String reviewWriter;
	private Long ordersDetailsId;
	private Long userId;
	private Long itemId;
	
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewWriter() {
		return reviewWriter;
	}
	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}
	public Long getOrdersDetailsId() {
		return ordersDetailsId;
	}
	public void setOrdersDetailsId(Long ordersDetailsId) {
		this.ordersDetailsId = ordersDetailsId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public Double getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Double reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
