package com.example.test.Model;

import java.util.Date;

//상품 리뷰
public class ShopReview {
	private Long reviewId;
	private String reviewContent;
	private Date reviewDate;
	private String reviewWriter;
	private Long ordersDetailId;
	private Long userId;
	
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
	public Long getOrdersDetailId() {
		return ordersDetailId;
	}
	public void setOrdersDetailId(Long ordersDetailId) {
		this.ordersDetailId = ordersDetailId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
