package com.example.test.Model;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

//주문
public class Orders {
	private Long ordersId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ordersDate = new Date();
	private String ordersUser;
	private Long userId;
	private Long ordersPrice;
	private List<OrdersDetails> ordersDetails;
	
	public Long getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Long ordersId) {
		this.ordersId = ordersId;
	}
	public Date getOrdersDate() {
		return ordersDate;
	}
	public void setOrdersDate(Date ordersDate) {
		this.ordersDate = ordersDate;
	}
	public String getOrdersUser() {
		return ordersUser;
	}
	public void setOrdersUser(String ordersUser) {
		this.ordersUser = ordersUser;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getOrdersPrice() {
		return ordersPrice;
	}
	public void setOrdersPrice(Long ordersPrice) {
		this.ordersPrice = ordersPrice;
	}
	public List<OrdersDetails> getOrdersDetails() {
		return ordersDetails;
	}
	public void setOrdersDetails(List<OrdersDetails> ordersDetails) {
		this.ordersDetails = ordersDetails;
	}
}
